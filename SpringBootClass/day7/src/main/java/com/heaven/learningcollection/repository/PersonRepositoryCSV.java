package com.heaven.learningcollection.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.*;
import com.heaven.learningcollection.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryCSV implements PersonRepositoryInterface {
    private ArrayList<Person> people;



    public PersonRepositoryCSV() {
        people = new ArrayList<>();
        loadData("person.csv");
    }

    private void loadData(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            ObjectReader oReader = mapper.readerFor(Person.class).with(schema);
            Reader reader = new FileReader(file);
            MappingIterator<Person> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                people.add(mi.next());
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Person> getAll() {
        return people;
    }

    @Override
    public List<Person> sortPeopleByFullName() {
        var clonePeople = people.stream().collect(Collectors.toList());
        Collections.sort(clonePeople, Comparator.comparing(Person::getFullName));
        return clonePeople;
    }

    @Override
    public List<String> getSortedJobs() {
        var clonePeople = people.stream().collect(Collectors.toList());
        Collections.sort(clonePeople, Comparator.comparing(Person::getJob));
        Set<String> str = new HashSet<>();
        for (var clone : clonePeople) {
            str.add(clone.getJob());
        }
        var list = str.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list;
    }

    @Override
    public List<String> getSortedCities() {
        var clonePeople = people.stream().collect(Collectors.toList());
        Collections.sort(clonePeople, Comparator.comparing(Person::getCity));
        Set<String> str = new HashSet<>();
        for (var clone : clonePeople) {
            str.add(clone.getCity());
        }
        var list = str.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list;
    }

    @Override
    public HashMap<String, List<Person>> groupPeopleByCity() {
        ListMultimap<String, Person> treeList = ArrayListMultimap.create();
        var clonePeople = people.stream().collect(Collectors.toList());
        for (var person : clonePeople) {
            treeList.put(person.getCity(), person);
        }
        var map = new HashMap<String, List<Person>>();
        for (String key : treeList.keySet()) {
            List<Person> people = treeList.get(key);
            map.put(key, people);
        }
        return map;
    }

    @Override
    public HashMap<String, Integer> groupJobByCount() {
        var map = new HashMap<String, Integer>();
        var clonePeople = people.stream().collect(Collectors.toList());
        for (var person : clonePeople) {
            var job = person.getJob();
            int ct = (int) people.stream().filter(p -> job.equals(p.getJob())).count();
            map.put(job, ct);
        }
        return map;
    }

    @Override
    public Map<String, Integer> findTop5Jobs() {
        HashMap<String, Integer> jobList = groupJobByCount();
        return jobList.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Integer> findTop5Cities() {
        Map<String, Integer> cityList = new TreeMap<>();
        for (var person : people) {
            String city = person.getCity();
            int ct = (int) people.stream().filter(p -> city.equals(p.getCity())).count();
            cityList.put(city, ct);
        }
        var map = cityList.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return map.entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public HashMap<String, String> findTopJobInCity() {
        HashMap<String,String> map = new HashMap<>();
        for(var person:people){
            map.put(person.getCity(),maxJob(person.getCity()));
        }
        System.out.println(map);
        return map;
    }

    @Override
    public List<String> find5CitiesHaveMostSpecificJob(String job) {
        List<String> cities = new ArrayList<>();
        for(var person:people){
            if(job.equals(person.getJob())){
                cities.add(person.getCity());
            }
        }
        int n = cities.size();
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(cities.get(i), mp.getOrDefault(cities.get(i), 0) + 1);
        }
        System.out.println(mp);
        List<List<String> > freq = new ArrayList<>();
        List<String> sortedList = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            freq.add(new ArrayList<>());

        for (Map.Entry<String, Integer> x : mp.entrySet())
            freq.get(x.getValue()).add(x.getKey());
        int count = 0;
        for (int i = n; i >= 0; i--) {
            for (String x : freq.get(i)) {
                sortedList.add(x);
            }
        }
        List<String> top5 = new ArrayList<>();
        for(int i=0;i<5;i++){
            if(sortedList.get(i)!=null)
                top5.add(sortedList.get(i));
            else
                top5.add(null);
        }
        System.out.println(top5);
        return top5;
    }


    private String maxJob(String city) {
        List<String> jobs = new ArrayList<>();
        for (var person : people) {
            if (city.equals(person.getCity())) {
                jobs.add(person.getJob());
            }
        }
        HashMultiset<String> namesCounts = HashMultiset.create(jobs);
        Set<Multiset.Entry<String>> namesAndCounts = namesCounts.entrySet();
        Multiset.Entry<String> maxNameByCount = Collections.max(namesAndCounts, Comparator.comparing(Multiset.Entry::getCount));
        List<String> mostCommonNames = new ArrayList<>();
        for (Multiset.Entry<String> nameAndCount : namesAndCounts) {
            if (nameAndCount.getCount() == maxNameByCount.getCount()) {
                mostCommonNames.add(nameAndCount.getElement());
            }
        }
        return mostCommonNames.get(0);
    }

    public ArrayList<Person> getPeople() {
        return people;
    }
}
