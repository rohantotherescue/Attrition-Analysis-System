package com.rohan.AttritionAnalysisSystem.service;

import com.rohan.AttritionAnalysisSystem.entity.Employee;
import com.rohan.AttritionAnalysisSystem.repo.EmployeeRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepo repo;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder= passwordEncoder;
    }

    public double calcAttritionRate(Date date){
        double employeesExited= (double) repo.countByTerminationDateBefore(date);
        double employeePresent= (double) repo.countByJoiningDateBefore(date);
        System.out.println(employeesExited+" "+employeePresent);
        double attritionRate= ((employeesExited/ employeePresent)* 100);
        return attritionRate;
    }

    public List<Employee> findAllEmployees(){
        return repo.findAll();
    }

    public Employee getEmployee(String empId){
        return repo.findById(empId).get();
    }

    public Employee onBoardEmployee(Employee employee){
        // check if joining date is greater than termination date
        employee.setSecurityClearanceNumber(passwordEncoder.encode(employee.getSecurityClearanceNumber()));
        employee.setJoiningDate(new Date());
        return repo.save(employee);
    }

    public String offBoardEmployee(String empId){
        Employee employee= repo.findById(empId).get();
        employee.setTerminationDate(new Date());
        repo.save(employee);
        return MessageFormat.format("Employee {0} has been off-boarded successfully!", empId);
    }


}