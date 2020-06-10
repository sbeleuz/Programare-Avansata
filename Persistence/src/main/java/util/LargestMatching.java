package util;

import entity.AlbumsEntity;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.jupiter.api.Test;
import repo.AlbumRepository;
import repo.GenreRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LargestMatching {
    SimpleDirectedGraph<String, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

    // parts of the graph
    Set<String> U = new HashSet<>(); // artists
    Set<String> V = new HashSet<>(); // genres

    static final String SOURCE = "source";
    static final String SINK = "sink";

    Map<String, String> parent; // map a node to its parent, used for DFS


    /**
     * Function to build a flow network (from a bipartite graph) from database(MusicAlbums)
     *
     * @param persistenceUtil used by jpa
     */
    public void buildGraph(PersistenceUtil persistenceUtil) {
        GenreRepository genreRepository = new GenreRepository(persistenceUtil);
        AlbumRepository albumRepository = new AlbumRepository(persistenceUtil);

        // add source and sink to build a flow network
        graph.addVertex(SOURCE);
        graph.addVertex(SINK);

        List<AlbumsEntity> albumsEntities = albumRepository.findAll();
        for (AlbumsEntity albumsEntity : albumsEntities) {
            // build a bipartite graph, where an edge from note u to node v is an album with artist u and genre v
            String u = "a" + albumsEntity.getArtistId();
            String v = "g" + genreRepository.findByAlbum(albumsEntity);

            U.add(u);
            V.add(v);

            graph.addVertex(u);
            graph.addVertex(v);
            graph.addEdge(u, v);

            // add edges from source to each node u and from each node v to sink
            graph.addEdge(SOURCE, u);
            graph.addEdge(v, SINK);
        }
    }

    /**
     * Function for finding a path from SOURCE to SINK
     */
    public List<String> DFS() {
        List<String> path = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        String v = SOURCE; // start node
        stack.push(v);
        while (!stack.empty()) {
            v = stack.pop();
            if (v.equals(SINK)) {
                break;
            }

            if (!parent.containsValue(v)) { // not visited node
                if (v.equals(SOURCE)) parent.put(v, SOURCE);
                for (DefaultEdge edge : graph.outgoingEdgesOf(v)) {
                    String w = graph.getEdgeTarget(edge);
                    if (!parent.containsKey(w)) {
                        parent.put(w, v);
                        stack.push(w);
                    }
                }
            }
        }

        // get the path from SINK to SOURCE
        if (!parent.containsKey(SINK))
            return Collections.emptyList();
        else {
            v = SINK;
            while (true) {
                path.add(v);
                v = parent.get(v);
                if (v.equals(SOURCE)) {
                    path.add(v);
                    break;
                }
            }
        }

        return path;
    }

    public int getLargestMatching() {
        int largestMatching = 0;

        boolean repeat = true;
        while (repeat) {
            repeat = false;
            // find a path from the source to the sink
            parent = new HashMap<>();
            List<String> path = DFS();
            // reverse each edge on found path
            for (String node : path) {
                if (!node.equals(SOURCE)) {
                    graph.removeEdge(parent.get(node), node);
                    graph.addEdge(node, parent.get(node));
                    repeat = true;
                }
            }
        }
        // matching solution is the set of edges between U and V that are reversed
        graph.removeVertex(SOURCE);
        graph.removeVertex(SINK);
        for (String u : U)
            if (!graph.incomingEdgesOf(u).isEmpty())
                largestMatching++;

        return largestMatching;
    }

    /**
     * Test function for getting largest matching in a bipartite graph
     */
    @Test
    public void test(PersistenceUtil persistenceUtil) {
        buildGraph(persistenceUtil);

        int matchingSize = getLargestMatching();
        System.out.println("Largest matching = " + matchingSize);

        AsUndirectedGraph<String, DefaultEdge> graphTest = new AsUndirectedGraph<>(graph);
        HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> alg =
                new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graphTest, U, V);

        try {
            assertEquals(alg.getMatching().getEdges().size(), matchingSize);
            System.out.println("Test passed");
        } catch (AssertionError e) {
            System.out.println("Test failed");
        }
    }
}
