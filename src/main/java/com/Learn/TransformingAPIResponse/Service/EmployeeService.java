package com.Learn.TransformingAPIResponse.Service;

import com.Learn.TransformingAPIResponse.Dto.EmployeeDto;
import com.Learn.TransformingAPIResponse.Entity.EmployeeEntity;
import com.Learn.TransformingAPIResponse.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public  EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper)
    {
        this.employeeRepository=employeeRepository;
        this.modelMapper=modelMapper;
    }

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employee= employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employee.forEach((emp)->
        {
            employeeDtos.add(modelMapper.map(emp, EmployeeDto.class));
        });

        return  employeeDtos;
    }

    public  EmployeeDto addEmployee(EmployeeDto empoyeeDto)
    {
        EmployeeEntity employee = modelMapper.map(empoyeeDto,EmployeeEntity.class);
        employee= employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
