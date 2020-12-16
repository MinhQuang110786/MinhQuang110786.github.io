package Quang.example.demo.controller;

import Quang.example.demo.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
    private String appName;
    static final String APP_NAME = "appName";
    static final String CARS = "cars";
    @GetMapping(value="")
    public String getHome(Model model){
        model.addAttribute(APP_NAME,appName);
        return "home";
    }

    @GetMapping(value = "/car")
    public String getCar(Model model){
        Car[] carCollection = {
                new Car("Triton 4x4 Premium","Misubishi",869,1),
                new Car("Ranger","Ford",1205,2),
                new Car("XL7 Premium","Suzuki",620,3)
        };
        model.addAttribute(CARS,carCollection);
        model.addAttribute(APP_NAME,appName);
        return "car";
    }

}
