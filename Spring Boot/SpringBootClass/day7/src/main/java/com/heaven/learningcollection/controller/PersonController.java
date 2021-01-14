package com.heaven.learningcollection.controller;

import com.heaven.learningcollection.model.Person;
import com.heaven.learningcollection.repository.PersonRepositoryCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PersonController {
    @Autowired
    PersonRepositoryCSV personRepository;

    @GetMapping()
    public String getAll(Model model){
        List<Person> people = personRepository.getAll();
        model.addAttribute("people",people);
        return "home";
    }

    @GetMapping("/fullName")
    public String sortedFullName(Model model){
        List<Person> people = personRepository.sortPeopleByFullName();
        model.addAttribute("people",people);
        return "fullName";
    }

    @GetMapping("/allJob")
    public String getAllJob(Model model){
        model.addAttribute("jobs",personRepository.getSortedJobs());
        return "job";
    }

    @GetMapping("/cities")
    public String getAllCity(Model model){
        model.addAttribute("cities",personRepository.getSortedCities());
        return "cities";
    }

    @GetMapping("/cityPeople")
    public String groupByCity(Model model){
        model.addAttribute("groups",personRepository.groupPeopleByCity());
        return "people";
    }

    @GetMapping("/jobCounting")
    public String groupJobByCount(Model model){
        model.addAttribute("jobCount",personRepository.groupJobByCount());
        return "jobCounting";
    }

    @GetMapping("/statistic")
    public String get5Job(Model model){
        model.addAttribute("top5jobs",personRepository.findTop5Jobs());

        model.addAttribute("top5cities",personRepository.findTop5Cities());
        return "statistic";
    }

    @GetMapping("/topJob")
    public String getTopJob(Model model){
        model.addAttribute("topJobs",personRepository.findTopJobInCity());
        return "topJob";
    }

    @GetMapping("/allJob/{job}")
    public String getCities(@PathVariable String job, Model model){
        model.addAttribute("topCities",personRepository.find5CitiesHaveMostSpecificJob(job));
        return "topCity";
    }
}
