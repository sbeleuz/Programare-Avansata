import com.github.javafaker.Faker;

import java.util.*;

public class RandomInstance {
    public List<Element<Resident>> residents;
    public List<Element<Hospital>> hospitals;
    public Map<Element<Resident>, List<Element<Hospital>>> resPrefMap;
    public Map<Element<Hospital>, List<Element<Resident>>> hosPrefMap;

    public RandomInstance(int lowBound, int highBound) {
        Random rand = new Random();
        Faker faker = new Faker();

        int noOfResidents = rand.nextInt(highBound - lowBound) + lowBound;
        residents = new ArrayList<>();
        for (int i = 0; i < noOfResidents; i++)
            residents.add(new Element<>(new Resident(faker.name().fullName())));

        int noOfHospitals = rand.nextInt(highBound - lowBound) + lowBound;
        hospitals = new ArrayList<>();
        for (int i = 0; i < noOfHospitals; i++)
            hospitals.add(new Element<>(new Hospital(faker.name().lastName(), rand.nextInt(noOfResidents - 1) + 1)));

        resPrefMap = new HashMap<>();
        for (Element<Resident> resident : residents) {
            List<Element<Hospital>> hos = new ArrayList<>();
            int noOfPreferences = rand.nextInt(noOfHospitals);
            for (int i = 0; i < noOfPreferences; i++) {
                int poz = rand.nextInt(noOfHospitals);
                hos.add(hospitals.get(poz));
            }
            resPrefMap.put(resident, hos);
        }

        hosPrefMap = new HashMap<>();
        for (Element<Hospital> hospital : hospitals) {
            List<Element<Resident>> res = new ArrayList<>();
            int noOfPreferences = rand.nextInt(noOfResidents);
            for (int i = 0; i < noOfPreferences; i++) {
                int poz = rand.nextInt(noOfResidents);
                res.add(residents.get(poz));
            }
            hosPrefMap.put(hospital, res);
        }
    }
}
