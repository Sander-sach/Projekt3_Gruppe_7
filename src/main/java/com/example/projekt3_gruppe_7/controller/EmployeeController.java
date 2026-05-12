package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.Employee;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import com.example.projekt3_gruppe_7.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //mapping til login som homepage
    @GetMapping("/")
    public String startPage(){
        return "login";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String loginSuccess(Model model, HttpSession session, @RequestParam String username, @RequestParam String password){
        Employee employee = employeeService.login(username,password);
        if(employee != null){
            session.setAttribute("employee",employee);
            EmployeeRole role = employee.getRole();

            switch(role){
                case DATA_REGISTRATION: return "redirect:/data-registration";
                case DAMAGE_AND_REPAIR: return "redirect:/damage-registration";
                case BUSINESS_DEVELOPER: return "redirect:/business-development";
                case ADMIN: return "redirect:/admin-overview";
            }
        }
        model.addAttribute("loginError",true);
        return "login";
    }

    @GetMapping("/register-user")
    public String registerUser(){
        return "register-user";
    }

    @PostMapping("/register-user")
    public String registerUserForm(Model model, @RequestParam String name, @RequestParam EmployeeRole role, @RequestParam String username, @RequestParam String password,@RequestParam String confirmPassword){
        Employee employee = new Employee(name,role,username,password);

        if (!password.equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            return "register-user";
        }
        if(employeeService.createEmployeeUser(employee)){
            return "redirect:/login?registered=true";

        }
        //registration error message
        model.addAttribute("registrationError", true);

        return "register-user";
    }
}
