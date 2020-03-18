import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // create array r on residents
        Resident[] r = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i))
                .toArray(Resident[]::new);

        // create array h of hospitals
        final int[] hCapacity = {1, 2, 2};
        Hospital[] h = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i, hCapacity[i]))
                .toArray(Hospital[]::new);
//
//        // create a list of residents
//        List<Resident> residentList = new ArrayList<>(Arrays.asList(r));
//        residentList.sort(Comparator.comparing(Resident::getName));
//
//        // create a set of hospitals
//        Set<Hospital> hospitalSet = new TreeSet<>(Arrays.asList(h));
//
//        // map residents to their preferences
//        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
//        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
//        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
//        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
//        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));
//
//        // map hospitals to their preferences
//        Map<Hospital, List<Resident>> hosPrefMap = new TreeMap<>();
//        hosPrefMap.put(h[0], Arrays.asList(r[3], r[0], r[1], r[2]));
//        hosPrefMap.put(h[1], Arrays.asList(r[0], r[2], r[1]));
//        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));
//
//        System.out.println("Residents who find acceptable H0 and H2:");
//        residentList.stream()
//                .filter(res -> resPrefMap.get(res).contains(h[0]))
//                .filter(res -> resPrefMap.get(res).contains(h[2]))
//                .forEach(System.out::println);
//
//        System.out.println();
//
//        System.out.println("Hospitals that have R0 as their top preference:");
//        hospitalSet.stream()
//                .filter(hos -> hosPrefMap.get(hos).get(0).equals(r[0]))
//                .forEach(System.out::println);
//
//        System.out.println(resPrefMap.get(r[0]));

        List<Element<Resident>> residents = new ArrayList<>();
        for (Resident resident : r) residents.add(new Element<>(resident));

        List<Element<Hospital>> hospitals = new ArrayList<>();
        for (Hospital hospital : h) hospitals.add(new Element<>(hospital));

        Map<Element<Resident>, List<Element<Hospital>>> resPrefMap = new HashMap<>();
        resPrefMap.put(residents.get(0), List.of(hospitals.get(0), hospitals.get(1), hospitals.get(2)));
        resPrefMap.put(residents.get(1), List.of(hospitals.get(0), hospitals.get(1), hospitals.get(2)));
        resPrefMap.put(residents.get(2), List.of(hospitals.get(0), hospitals.get(1)));
        resPrefMap.put(residents.get(3), List.of(hospitals.get(0), hospitals.get(2)));

        Map<Element<Hospital>, List<Element<Resident>>> hosPrefMap = new HashMap<>();
        hosPrefMap.put(hospitals.get(0), List.of(residents.get(3), residents.get(0), residents.get(1), residents.get(2)));
        hosPrefMap.put(hospitals.get(1), List.of(residents.get(0), residents.get(2), residents.get(1)));
        hosPrefMap.put(hospitals.get(2), List.of(residents.get(0), residents.get(1), residents.get(3)));

        Problem<Resident, Hospital> problem = new Problem<>(residents, hospitals, resPrefMap, hosPrefMap);
        Matching<Element<Resident>, Element<Hospital>> matching = problem.getMatching();
        System.out.println(matching);
        boolean checkStableMatching = problem.checkStableMatching(matching);
        if (checkStableMatching) System.out.println("Matching is stable");
        else System.out.println("Matching is not stable");

        RandomInstance randomInstance = new RandomInstance(2, 10);
        Problem<Resident, Hospital> randProblem = new Problem<>(randomInstance.residents, randomInstance.hospitals, randomInstance.resPrefMap, randomInstance.hosPrefMap);
        matching = randProblem.getMatching();
        System.out.println(matching);
        checkStableMatching = randProblem.checkStableMatching(matching);
        if (checkStableMatching) System.out.println("Matching is stable");
        else System.out.println("Matching is not stable");

        Matching<Element<Resident>, Element<Hospital>> stableMatching = problem.getStableMatching();
        System.out.println("Stable matching: ");
        System.out.println(stableMatching);
    }
}
