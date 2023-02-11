package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository eRepo;

    @GetMapping({"/showEmployee","/","/list"})
    public ModelAndView showEmployees(){
        //mav constructor takjes the temp-late name
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> list = eRepo.findAll();
        mav.addObject("employees",list);
        return mav;

    }
//Add record
    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee newemployee = new Employee();
        mav.addObject("employee",newemployee);
        return mav;

    }
    @PostMapping("saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        eRepo.save(employee);
        return "redirect:/list";

    }
//Add record
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee = eRepo.findById(employeeId).get();
        mav.addObject("employee",employee);
        return mav;
    }
    @GetMapping("/deleteEmployee")
  public  String deleteEmployee(@RequestParam Long employeeId){
        eRepo.deleteById(employeeId);
        return "redirect:/list";

  }



}
