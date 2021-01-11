package com.heaven.learningcollection;


import com.heaven.learningcollection.model.Person;
import com.heaven.learningcollection.repository.PersonRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepositoryTesting {
    @Qualifier("personRepository")
    @Autowired
    PersonRepositoryInterface repo;

    @Test
    public void testListOfPeople(){
        List<Person> people = repo.getAll();
        for(var person:people)
            System.out.println(person.getFullName());
        assertThat(people.size()).isEqualTo(300);
    }

    @Test
    public void testSortPeopleByFullName(){
        List<Person> people = repo.getAll();
        var clonePeople = people.stream().collect(Collectors.toList());
        Collections.sort(clonePeople, Comparator.comparing(Person::getFullName));
        for(var person:clonePeople)
            System.out.println(person.getFullName());
        assertThat(clonePeople.size()).isEqualTo(300);
    }

    @Test
    public void testSortedJob(){
        List<String> jobs = repo.getSortedJobs();
        for(var job:jobs)
            System.out.println(job);
        assertThat(jobs.get(0)).isEqualTo("Accountant");
    }

    @Test
    public void testGetSortedCity(){
        List<String> cities = repo.getSortedCities();
        for(var city:cities)
            System.out.println(city);
        assertThat(cities.get(4)).isEqualTo("Hai phong");
    }

    @Test
    public void testGroupPeopleByCity(){
        HashMap<String, List<Person>> map = repo.groupPeopleByCity();
        List<Person> people = map.get("New York");
        for(var person:people){
            System.out.println(person.getFullName());
        }
        assertThat(map.containsKey("Seoul"));
    }

    @Test
    public void testGroupJobByCount(){
        HashMap<String,Integer> map = repo.groupJobByCount();
        assertThat(map.get("Banker")).isEqualTo(24);
    }

    @Test
    public void testFindTop5Jobs(){
        Map<String,Integer> map = repo.findTop5Jobs();
        for (Map.Entry<String,Integer> entry : map.entrySet())
            System.out.println("Job = " + entry.getKey() +
                    ", Number = " + entry.getValue());

        assertThat(map.get("Developer")).isEqualTo(19);
    }

    @Test
    public void testFindTop5Cities(){
        Map<String,Integer> map = repo.findTop5Cities();
        for (Map.Entry<String,Integer> entry : map.entrySet())
            System.out.println("Cities = " + entry.getKey() +
                    ", population = " + entry.getValue());

        assertThat(map.get("Barcelona")).isEqualTo(25);
    }

    @Test
    public void testTopJobInCity(){
        Map<String,String> map = repo.findTopJobInCity();
        for (Map.Entry<String,String> entry : map.entrySet())
            System.out.println("Cities = " + entry.getKey() +
                    ", Job = " + entry.getValue());
        assertThat(map.get("Hanoi")).isEqualTo("Soldier");
    }

    @Test
    public void testFind5CitiesHaveMostSpecificJob(){
        List<String> list = repo.find5CitiesHaveMostSpecificJob("Developer");
        for(var l:list)
            System.out.println(l);
        assertThat(list.get(1)).isEqualTo("Barcelona");
    }
}
