package com.heaven.learningcollection.config;

import com.heaven.learningcollection.repository.PersonRepositoryCSV;
import com.heaven.learningcollection.repository.PersonRepositoryInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    PersonRepositoryInterface personRepository(){
        return new PersonRepositoryCSV();
    }
}
