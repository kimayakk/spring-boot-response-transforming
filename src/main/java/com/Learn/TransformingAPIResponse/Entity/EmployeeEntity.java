package com.Learn.TransformingAPIResponse.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Max(value = 100000)
    @Min(value = 1000)
    private BigDecimal salary;

    @PastOrPresent
    private LocalDate dateOfJoining;

    private  Boolean active;

    @Max(value = 75, message="Age should be less than 75 years")
    @Min(value=20 ,message="Age should be greater than 20 years")
    private  int age;
}