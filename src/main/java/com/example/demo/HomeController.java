package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    public String getHomepage(){
        return "index";
    }

    @GetMapping("/addDept")
    public String getCategoryForm(Model model){
        model.addAttribute("department", new Department());
        return "deptform";
    }

    @PostMapping("/addDept")
    public String addCategory(@ModelAttribute("department") Department department){
       departmentRepository.save(department);
       return "redirect:/deptlist";
    }

    @RequestMapping("/deptlist")
    public String getCategoryList(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "Deptlist";
    }

    @GetMapping("/addEmp")
    public String getCarForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "empform";
    }

    @PostMapping("/addEmp")
    public String addCar(@ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/emplist";
    }

    @RequestMapping("/emplist")
    public String getCarsList(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "emplist";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "empform";
    }

    @RequestMapping("/detail/{id}")
    public String getCarDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "empdetail";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/emplist";
    }


}
