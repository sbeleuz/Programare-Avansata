import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem<R, H> {
    List<Element<R>> residents;
    List<Element<H>> hospitals;
    Map<Element<R>, List<Element<H>>> resPrefMap;
    Map<Element<H>, List<Element<R>>> hosPrefMap;

    public Problem(List<Element<R>> residents, List<Element<H>> hospitals, Map<Element<R>, List<Element<H>>> resPrefMap, Map<Element<H>, List<Element<R>>> hosPrefMap) {
        this.residents = residents;
        this.hospitals = hospitals;
        this.resPrefMap = resPrefMap;
        this.hosPrefMap = hosPrefMap;
    }

    Matching<Element<R>, Element<H>> getMatching() {
        Matching<Element<R>, Element<H>> matching = new Matching<>();

        /* map each hospital to its capacity*/
        Map<Element<H>, Integer> capacity = new HashMap<>();
        for (Element<H> hospital : hospitals) capacity.put(hospital, hospital.getCapacity());

        /* “First come, first served" matching */
        for (Element<R> resident : residents) {
            /* get current resident's preferences*/
            List<Element<H>> hospitals = resPrefMap.get(resident);
            for (Element<H> hospital : hospitals) {
                /* check if current hospital's capacity is not exceeded
                     and if current hospital prefers current resident */
                if (capacity.get(hospital) != 0 && hosPrefMap.get(hospital).contains(resident)) {
                        capacity.put(hospital, capacity.get(hospital) - 1);
                        matching.add(new Partition<>(resident, hospital));
                        break;
                    }
            }
        }
        return matching;
    }

    Element<H> getResCurrMatch(Matching<Element<R>, Element<H>> matching, Element<R> resident) {
        List<Partition<Element<R>, Element<H>>> partitionList = matching.getPartitionList();

        for (Partition<Element<R>, Element<H>> partition : partitionList)
            if (partition.getFirst() == resident) return partition.getSecond();

        return null;
    }

    List<Element<R>> getHosCurrMatch(Matching<Element<R>, Element<H>> matching, Element<H> hospital) {
        List<Element<R>> resList = new ArrayList<>();

        List<Partition<Element<R>, Element<H>>> partitionList = matching.getPartitionList();

        for (Partition<Element<R>, Element<H>> partition : partitionList)
            if (partition.getSecond() == hospital) resList.add(partition.getFirst());

        return resList;
    }

    boolean checkStableMatching(Matching<Element<R>, Element<H>> matching) {
        for (Element<R> resident : residents) {
            Element<H> resCurrMatch = getResCurrMatch(matching, resident);
            /* for hospitals current resident would prefer over current matching */
            for (Element<H> hospital : resPrefMap.get(resident)) {
                if (hospital.equals(resCurrMatch)) break;
                    /* check if current hospital prefers current resident over current matching */
                else {
                    List<Element<R>> hosCurrMatch = getHosCurrMatch(matching, hospital);
                    for (Element<R> res : hosPrefMap.get(hospital)) {
                        boolean ok = true;
                        for (Element<R> currRes : hosCurrMatch) {
                            if (res != currRes) ok = false;
                            else {
                                ok = true;
                                break;
                            }
                        }
                        if (!ok) return false;
                        else break;
                    }
                }
            }
        }
        return true;
    }
}
