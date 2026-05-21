package com.example.projekt3_gruppe_7.controller;

import com.example.projekt3_gruppe_7.model.Customer;
import com.example.projekt3_gruppe_7.model.DamageReport;
import com.example.projekt3_gruppe_7.model.Employee;
import com.example.projekt3_gruppe_7.model.EmployeeRole;
import com.example.projekt3_gruppe_7.service.DamageReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class DamageReportController {

    private final DamageReportService damageReportService;

    public DamageReportController(DamageReportService damageReportService) {
        this.damageReportService = damageReportService;
    }

    @GetMapping("/damage-registration-overview")
    public String viewDamageRegistrations(Model model, HttpSession session){

        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null){
            return "redirect:/login";
        }
        if(employee.getRole() != EmployeeRole.DATA_REGISTRATION && employee.getRole() != EmployeeRole.ADMIN){
            return "redirect:/login";
        }
        List<DamageReport> reports = damageReportService.findAllDamageReports();
        Map<Long, Customer> customers = damageReportService.matchCustomerByDamageReport(reports);
        model.addAttribute("reports",reports);
        model.addAttribute("customers",customers);

        return "damage-registration-overview";
    }
}
