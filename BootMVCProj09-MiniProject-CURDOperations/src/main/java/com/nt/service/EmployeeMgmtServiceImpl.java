package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.repository.IEmployeeRepository;
@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	@Autowired
IEmployeeRepository empRepo;
	@Override
	public Iterable<Employee> showAllEmployees() {
		
		return empRepo.findAll();
	}
	@Override
	public String registerEmployee(Employee emp) {
	//use repo
		int idVal=empRepo.save(emp).getEmpno();
		return "Employee is saved with id value::"+idVal;
	}
	@Override
	public Employee fetchEmployeeByNo(int no) {
		
		return empRepo.findById(no).orElseThrow(()-> new IllegalArgumentException("Invalid Employee no"));
	}
	@Override
	public String updateEmployee(Employee emp) {
		//load object
		Optional<Employee>opt=empRepo.findById(emp.getEmpno());
		if(opt.isPresent()) {
			//update the object
			empRepo.save(emp);
			return emp.getEmpno()+"employees details are updated";
		}
		return emp.getEmpno()+"Employee is not found";
	}
	@Override
	public String deleteEmployeeById(int eno) {
		//load object
		boolean flag=empRepo.existsById(eno);
		if(flag) {
			empRepo.deleteById(eno);
			return eno+"Employee is found and deleted";
		}
		return eno+"Employee is not found and deleted";
	}
	
	
	

}














