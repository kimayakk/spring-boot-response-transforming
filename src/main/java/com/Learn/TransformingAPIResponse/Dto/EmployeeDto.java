package com.Learn.TransformingAPIResponse.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDto {
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

