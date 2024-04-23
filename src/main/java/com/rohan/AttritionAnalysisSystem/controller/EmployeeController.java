package com.rohan.AttritionAnalysisSystem.controller;

import com.rohan.AttritionAnalysisSystem.entity.Employee;
import com.rohan.AttritionAnalysisSystem.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService= employeeService;
    }


    /**
     * Allow only HR and manager
     * @return
     */
    @GetMapping("/getEmployeeList")
    public List<Employee> getEmployeeList(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/Employee")
    public Employee getEmployee(@RequestParam String empId){
        return employeeService.getEmployee(empId);
    }

    @GetMapping("/getAttritionRate")
    public String getAttritionRate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return MessageFormat.format( "The Attrition Rate is {0} %", employeeService.calcAttritionRate(date));
    }

    @PostMapping("/onBoard")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee onBoardEmployee(@RequestBody Employee employee){
        return employeeService.onBoardEmployee(employee);
    }

    /**
     * remove the employee and return the removed employee
     * @return
     */
    @PutMapping("/offBoard")
    public String offBoardEmployee(@RequestParam String empId){
        return employeeService.offBoardEmployee(empId);
    }

}
