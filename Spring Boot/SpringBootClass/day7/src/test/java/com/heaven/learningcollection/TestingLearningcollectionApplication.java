package com.heaven.learningcollection;
import com.heaven.learningcollection.controller.PersonController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestingLearningcollectionApplication {
    @Autowired
    private PersonController controller;
    @Test
    public void contextLoads() {

    }
}
