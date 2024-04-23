package com.rohan.AttritionAnalysisSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String empId; // Primary key
    private String name;
    private String role;
    private Date joiningDate;
    private Date terminationDate; // Nullable

}