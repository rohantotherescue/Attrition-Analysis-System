package com.rohan.AttritionAnalysisSystem.service;

import com.rohan.AttritionAnalysisSystem.entity.Employee;
import com.rohan.AttritionAnalysisSystem.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepo repo;

    public EmployeeServiceImpl(EmployeeRepo repo) {
        this.repo = repo;
    }

    public double calcAttritionRate(Date date){
        long employeesExited= repo.countByTerminationDateBefore(date);
        long employeePresent= repo.countByJoiningDateBefore(date);
        double attritionRate= (double) (employeesExited/ employeePresent)* 100;
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
        employee.setJoiningDate(new Date());
        return repo.save(employee);
    }

    public String offBoardEmployee(String empId){
        Employee employee= repo.findById(empId).get();
        employee.setTerminationDate(new Date());
        repo.save(employee);
        return MessageFormat.format("Employee {0} has been off-boarded successfully!", empId);
        // add the current date to termination date
    }


}