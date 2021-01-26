package com.example.userservice.service.impl;

import com.example.userservice.dto.EmployeeDTO;
import com.example.userservice.entity.Company;
import com.example.userservice.entity.Department;
import com.example.userservice.entity.Employee;
import com.example.userservice.mapper.EmployeeMapper;
import com.example.userservice.repository.EmployeeRepository;
import com.example.userservice.service.CompanyService;
import com.example.userservice.service.DepartmentService;
import com.example.userservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private CompanyService companyService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, CompanyServiceImpl companyService, DepartmentServiceImpl departmentService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.companyService = companyService;
        this.departmentService = departmentService;
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employeeMapper::mapToDTO)
                .collect(Collectors.toList());
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.mapToEntity(employeeDTO);

        String departmentId = employee.getDepartment().getId();
        Department department = departmentService.findDepartmentById(departmentId);
        employee.setDepartment(department);

        String companyId = employee.getCompany().getId();
        Company company = companyService.findCompanyById(companyId);
        employee.setCompany(company);

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToDTO(savedEmployee);
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Employee.class + "with id " + id));
    }

    @Override
    public EmployeeDTO findByLogin(String login) {
        EmployeeDTO employeeDTO = null;
        Employee employee = employeeRepository.findByLogin(login);
        if (employee == null) {
            throw new EntityNotFoundException(Employee.class + "with login " + login);
        } else {
            employeeDTO = employeeMapper.mapToDTO(employee);
        }
        return employeeDTO;
    }

}
