package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String getHomepage(){
        return "index";
    }

    @GetMapping("/addCategory")
    public String getCategoryForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Category category){
       categoryRepository.save(category);
       return "redirect:/categorylist";
    }

    @RequestMapping("/categorylist")
    public String getCategoryList(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    @GetMapping("/addCar")
    public String getCarForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute("car") Car car){
        carRepository.save(car);
        return "redirect:/carlist";
    }

    @RequestMapping("/carlist")
    public String getCarsList(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "carslist";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/detail/{id}")
    public String getCarDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "cardetail";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/carlist";
    }


}
