package com.heaven.learningcollection.repository;

import com.heaven.learningcollection.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PersonRepositoryInterface {
    List<Person> getAll();
    List<Person> sortPeopleByFullName();
    List<String> getSortedJobs();
    List<String> getSortedCities();
    HashMap<String,List<Person>> groupPeopleByCity();
    HashMap<String,Integer> groupJobByCount();
    Map<String,Integer> findTop5Jobs();
    Map<String,Integer> findTop5Cities();
    HashMap<String,String> findTopJobInCity();
    List<String> find5CitiesHaveMostSpecificJob(String job);
}
