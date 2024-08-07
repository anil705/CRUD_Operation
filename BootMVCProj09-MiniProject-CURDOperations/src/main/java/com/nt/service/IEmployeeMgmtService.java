package com.nt.service;

import java.util.List;

import com.nt.entity.Employee;

public interface IEmployeeMgmtService {
public Iterable<Employee> showAllEmployees();
public String registerEmployee(Employee emp);
public Employee	 fetchEmployeeByNo(int no);
public String updateEmployee(Employee emp);
public String deleteEmployeeById(int eno);
}
