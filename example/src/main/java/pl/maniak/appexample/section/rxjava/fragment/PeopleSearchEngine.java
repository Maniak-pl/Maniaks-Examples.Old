package pl.maniak.appexample.section.rxjava.fragment;

import java.util.LinkedList;
import java.util.List;

public class PeopleSearchEngine {

    private final List<String> people;
    private final int peopleCount;

    public PeopleSearchEngine(List<String> people) {
        this.people = people;
        peopleCount = this.people.size();
    }

    public List<String> search(String query) {
        query = query.toLowerCase();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> result = new LinkedList<>();

        for (int i = 0; i < peopleCount; i++) {
            if (people.get(i).toLowerCase().contains(query)) {
                result.add(people.get(i));
            }
        }

        return result;
    }

}
