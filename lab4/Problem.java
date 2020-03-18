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

    /**
     * First come, first served algorithm
     *
     * @return A matching
     */
    Matching<Element<R>, Element<H>> getMatching() {
        Matching<Element<R>, Element<H>> matching = new Matching<>();

        /* map each hospital to its capacity*/
        Map<Element<H>, Integer> capacity = new HashMap<>();
        for (Element<H> hospital : hospitals) capacity.put(hospital, hospital.getCapacity());

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

    /**
     * Gale–Shapley algorithm
     *
     * @return A stable matching
     */
    Matching<Element<R>, Element<H>> getStableMatching() {
        Matching<Element<R>, Element<H>> matching = new Matching<>();

        /* map each hospital to its capacity*/
        Map<Element<H>, Integer> capacity = new HashMap<>();
        for (Element<H> hospital : hospitals) capacity.put(hospital, hospital.getCapacity());

        /* initially, all residents are free (not matched to any hospital */
        Map<Element<R>, Boolean> free = new HashMap<>();
        for (Element<R> resident : residents) free.put(resident, true);

        /* accepted residents for each hospital */
        Map<Element<H>, List<Element<R>>> accResidents = new HashMap<>();

        while (free.containsValue(true)) {
            for (Element<R> resident : residents) {
                /* if current resident is not matched */
                if (free.get(resident)) {
                    /* get best preference for current resident */
                    for (Element<H> hospital : resPrefMap.get(resident)) {
                        /* if current hospital is not full */
                        if (capacity.get(hospital) != 0) {
                            /* if current hospital accepts current resident */
                            if (hosPrefMap.get(hospital).contains(resident)) {
                                /* add current resident to current hospital */
                                capacity.put(hospital, capacity.get(hospital) - 1);
                                matching.add(new Partition<>(resident, hospital));
                                free.put(resident, false);
                                List<Element<R>> auxList = accResidents.get(hospital);
                                if (auxList == null) auxList = new ArrayList<>();
                                auxList.add(resident);
                                accResidents.put(hospital, auxList);
                                break;
                            }
                        }
                        /* if current hospital is full */
                        else {
                            boolean ok = false;
                            /* get current hospital's worst preference */
                            for (int i = hosPrefMap.get(hospital).size() - 1; i >= 0; i--) {
                                if (hosPrefMap.get(hospital).get(i) == resident) break;
                                else {
                                    for (Element<R> accRes : accResidents.get(hospital)) {
                                        /* if current resident is better than the already accepted one */
                                        if (hosPrefMap.get(hospital).get(i) == accRes) {
                                            /* remove already accepted (worse) resident */
                                            matching.remove(new Partition<>(accRes, hospital));
                                            free.put(accRes, true);
                                            List<Element<R>> auxList = accResidents.get(hospital);
                                            auxList.remove(accRes);
                                            accResidents.put(hospital, auxList);

                                            /* add current resident (better) */
                                            matching.add(new Partition<>(resident, hospital));
                                            free.put(resident, false);
                                            auxList = accResidents.get(hospital);
                                            if (auxList == null) auxList = new ArrayList<>();
                                            auxList.add(resident);
                                            accResidents.put(hospital, auxList);
                                            ok = true;
                                            break;
                                        }
                                    }
                                    if (ok) break;
                                }
                            }
                            if (ok) break;
                        }
                    }
                }
            }
        }

        return matching;
    }
}
