package com.rohan.AttritionAnalysisSystem.controller;

import com.rohan.AttritionAnalysisSystem.dto.AuthRequest;
import com.rohan.AttritionAnalysisSystem.entity.Employee;
import com.rohan.AttritionAnalysisSystem.service.EmployeeService;
import com.rohan.AttritionAnalysisSystem.service.JwtService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public EmployeeController(EmployeeService employeeService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    /**
     * Allow only HR and manager
     *
     * @return
     */
    @GetMapping("/getEmployeeList")
    public List<Employee> getEmployeeList() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/Employee")
    public Employee getEmployee(@RequestParam String empId) {
        return employeeService.getEmployee(empId);
    }

    @GetMapping("/getAttritionRate")
    public String getAttritionRate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return MessageFormat.format("The Attrition Rate is {0} %", employeeService.calcAttritionRate(date));
    }

    @PostMapping("/onBoard")
    @PreAuthorize("hasAuthority('HR')")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee onBoardEmployee(@RequestBody Employee employee) {
        return employeeService.onBoardEmployee(employee);
    }

    /**
     * remove the employee and return the removed employee
     *
     * @return
     */
    @PutMapping("/offBoard")
    @PreAuthorize("hasAuthority('HR')")
    public String offBoardEmployee(@RequestParam String empId) {
        return employeeService.offBoardEmployee(empId);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getScn()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }

    }
}
