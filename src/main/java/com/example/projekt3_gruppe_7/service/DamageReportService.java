package com.example.projekt3_gruppe_7.service;

import com.example.projekt3_gruppe_7.model.Customer;
import com.example.projekt3_gruppe_7.model.DamageReport;
import com.example.projekt3_gruppe_7.repository.CarRepository;
import com.example.projekt3_gruppe_7.repository.CustomerRepository;
import com.example.projekt3_gruppe_7.repository.DamageReportRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DamageReportService {

    private final DamageReportRepository damageReportRepository;
    private final CustomerRepository customerRepository;

    public DamageReportService(DamageReportRepository damageReportRepository, CustomerRepository customerRepository) {
        this.damageReportRepository = damageReportRepository;
        this.customerRepository = customerRepository;
    }

    public List<DamageReport> findAllDamageReports(){
        return damageReportRepository.findAll();
    }

    public Map<Long,Customer> matchCustomerByDamageReport(List<DamageReport> damageReportList){
        Map<Long, Customer> customerByReportId = new HashMap<>();

        for(DamageReport damageReport: damageReportList){
            //sætter Hashkey som reportId og samt bruger det til at finde customer data.
            Long reportId = damageReport.getDamageReportId();
            customerByReportId.put(reportId, customerRepository.findCustomerByReportId(reportId));
        }
        return customerByReportId;
    }
}
