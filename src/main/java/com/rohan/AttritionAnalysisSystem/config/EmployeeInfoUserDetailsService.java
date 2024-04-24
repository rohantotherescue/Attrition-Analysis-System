package com.rohan.AttritionAnalysisSystem.config;

import com.rohan.AttritionAnalysisSystem.entity.Employee;
import com.rohan.AttritionAnalysisSystem.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> userInfo = repository.findByName(username);
        return userInfo.map(EmployeeInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}