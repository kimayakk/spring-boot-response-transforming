package com.Learn.TransformingAPIResponse.Controller;

import com.Learn.TransformingAPIResponse.Dto.EmployeeDto;
import com.Learn.TransformingAPIResponse.Exceptions.ResourceNotFoundException;
import com.Learn.TransformingAPIResponse.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private  EmployeeService employeeService;

    public  EmployeeController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @GetMapping("/getAllEmployees")
    public  ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
     List<EmployeeDto> list= employeeService.getAllEmployees();
     if(list==null)
     {
        throw new ResourceNotFoundException("Employee list is blank");
     }
     return  ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @PostMapping("/addEmployee")
    public  ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid EmployeeDto employeeDto)
    {
        EmployeeDto employeeDto1 = employeeService.addEmployee(employeeDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(employeeDto1);
    }
}
