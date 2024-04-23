package com.rohan.AttritionAnalysisSystem.repo;

import com.rohan.AttritionAnalysisSystem.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface EmployeeRepo extends MongoRepository<Employee, String> {

    long countByTerminationDateBefore(Date date);

    long countByJoiningDateBefore(Date date);

}
