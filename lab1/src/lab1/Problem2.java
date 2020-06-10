package lab1;

import java.util.Random;

/**
 * @author Stefan Beleuz
 */

/* 5 3 A B C D E F G H I J K L M N O P Q R S T U V W X Y Z */

public class Problem2 {
    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();

        if (args.length < 3) {
            System.out.println("Usage: number, number, one or more characters");
            System.exit(-1);
        }

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int m = args.length - 2;
        char[] alphabet = new char[m];
        for (int i = 0; i < m; i++) {
            alphabet[i] = args[i + 2].charAt(0);
        }

        long startTime = System.nanoTime();

        /* create array of n String of k characters */
        String[] words = problem2.generateWords(n, k, alphabet);

        /* print words */
        if (n < 30_000) {
            for (int i = 0; i < n; i++) {
                System.out.println(words[i]);
            }
        }

        /* build boolean n x n matrix, representing the adjacency relation of the words */
        boolean[][] matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = problem2.adjacent(words[i], words[j]);
            }
        }

        /* print matrix */
        if (n < 30_000) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        /* get number of neighbors for each word */
        int[] noOfNeighbours = problem2.countNeighbors(words);
        int max, min;
        max = min = noOfNeighbours[0];

        for (int i = 1; i < n; i++) {
            if (max < noOfNeighbours[i])
                max = noOfNeighbours[i];
            if (min > noOfNeighbours[i])
                min = noOfNeighbours[i];
        }

        if (max == min) {
            System.out.println("All words have the same number of neighbors");
        } else {
            /* get the words with the most and least number of neighbors */
            String[] maxWords = new String[n];
            String[] minWords = new String[n];
            int maxPoz = 0, minPoz = 0;

            for (int i = 0; i < n; i++) {
                if (noOfNeighbours[i] == max) {
                    maxWords[maxPoz++] = words[i];
                }
                if (noOfNeighbours[i] == min) {
                    minWords[minPoz++] = words[i];
                }
            }

            if (n < 30_000) {
                System.out.println("Words that  have the most number of neighbors:");
                for (int i = 0; i < maxPoz; i++)
                    System.out.println(maxWords[i]);
            }

            if (n < 30_000) {
                System.out.println("Words that  have the least number of neighbors:");
                for (int i = 0; i < minPoz; i++)
                    System.out.println(minWords[i]);
            }
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Running time (in nanoseconds): " + totalTime);


        /*Bonus*/
        boolean[] visited = new boolean[n];

        int component = 0;
        System.out.println("Connected components:");
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                component++;
                System.out.print(component + ": ");
                problem2.DFS(i, visited, matrix, words);
                System.out.print('\n');
            }
        }

        if (component == 1)
            System.out.println("Graph is connected!");
        else
            System.out.println("Graph is not connected!");
    }


    /**
     * This method generates n words using only k characters from alphabet
     *
     * @param n        Number of words to generate
     * @param k        Length of each word
     * @param alphabet Input alphabet
     * @return String[] Generated words
     */
    private String[] generateWords(int n, int k, char[] alphabet) {
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder word = new StringBuilder(k);

            for (int j = 0; j < k; j++) {
                Random rand = new Random();
                int poz = rand.nextInt(alphabet.length);
                word.append(alphabet[poz]);
            }

            words[i] = word.toString();
        }

        return words;
    }


    /**
     * This method determines if two words are adjacent
     * (have at least one letter in common)
     *
     * @param str1 First word
     * @param str2 Second word
     * @return boolean Returns true if str1 and str2 are adjacent, else false
     */
    private boolean adjacent(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * This method counts the neighbors for each word
     *
     * @param words Input words
     * @return int[] Array of neighbors
     */
    private int[] countNeighbors(String[] words) {
        int[] noOfNeighbors = new int[words.length];
        int poz = 0;

        for (String word1 : words) {
            int count = 0;
            for (String word2 : words) {
                if (adjacent(word1, word2)) {
                    count++;
                }
            }
            noOfNeighbors[poz++] = count;
        }

        return noOfNeighbors;
    }


    /**
     * This method proceed a DFS, starting with node i
     *
     * @param i       Starting node
     * @param visited Mark visited nodes
     * @param graph   Graph represented as an adjacency matrix
     * @param words   Array of words (nodes)
     * @return void
     */
    void DFS(int i, boolean[] visited, boolean[][] graph, String[] words) {
        if (!visited[i]) {
            visited[i] = true;
            System.out.print(words[i] + " ");

            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] && !visited[j]) {
                    DFS(j, visited, graph, words);
                }
            }
        }
    }
}