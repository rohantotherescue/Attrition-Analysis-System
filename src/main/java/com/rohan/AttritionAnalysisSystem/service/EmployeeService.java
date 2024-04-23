package com.rohan.AttritionAnalysisSystem.service;

import com.rohan.AttritionAnalysisSystem.entity.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    double calcAttritionRate(Date date);
    String offBoardEmployee(String empId);
    Employee onBoardEmployee(Employee employee);
    Employee getEmployee(String empId);
    List<Employee> findAllEmployees();

}