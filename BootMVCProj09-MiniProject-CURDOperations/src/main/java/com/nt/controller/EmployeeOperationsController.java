package com.nt.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeMgmtService;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtService empService;
@GetMapping("/")
	public String showHomePage() {
		//return LVN
		return "welcome";
	}
@GetMapping("/report")
public String showEmployeeReportData(Map<String,Object>map) {
	System.out.println("EmployeeOperationsController.showEmployeeReportData()");

	//use Service
	Iterable<Employee> empList=empService.showAllEmployees();
	//keep the resullt in shared Memory
	map.put("empsData", empList);
	
	//return LVN
	return "show_report";
}
@GetMapping("/register")//to launch the add employee form page
public String showAddEmployeeFormPage(@ModelAttribute("emp") Employee emp) {
	System.out.println("EmployeeOperationsController.showAddEmployeeFormPage()");
	//return LVN
	return "employee_register_form";
}

/*@PostMapping("/register")  // porblem in this code
public String registerEmployee(Map<String,Object>map,@ModelAttribute("emp") Employee emp) {
	//use service
	String msg=empService.registerEmployee(emp);
	Iterable<Employee> empList=empService.showAllEmployees();
	//keep the result  in shared memory as the model attribute
	map.put("resultMsg", msg);
	map.put("empsData", empList);
	//return LVN
	return "show_report";
}*/

/*@PostMapping("/register")  //solution 1 with limitation
public String registerEmployee(Map<String,Object>map,@ModelAttribute("emp") Employee emp) {
	System.out.println("EmployeeOperationsController.registerEmployee()");
	//use service
	String msg=empService.registerEmployee(emp);
	
	//keep the result  in shared memory as the model attribute
	map.put("resultMsg", msg);
	
	//return LVN
	return "redirect:report";

}*/


@PostMapping("/register")  
public String registerEmployee(RedirectAttributes attrs,@ModelAttribute("emp") Employee emp) {
	System.out.println("EmployeeOperationsController.registerEmployee()");
	//use service
	String msg=empService.registerEmployee(emp);
	
	//keep the result  in shared memory as the model attribute
	attrs.addFlashAttribute("resultMsg", msg);
	
	//return LVN
	return "redirect:report";
	
}
@GetMapping("/edit")
public String showEditFormPage(@RequestParam("no") int no,@ModelAttribute("emp")Employee emp) {
	
	//use service
	Employee emp1=empService.fetchEmployeeByNo(no);
	//copy the data to model class obj
	BeanUtils.copyProperties(emp1, emp);
	//return LVN of edit form page
	return "employee_edit_form";
	
}
@PostMapping("/edit")
public String  updateEmployee(@ModelAttribute("emp") Employee emp,RedirectAttributes attrs) {
	//use service
	String msg=empService.updateEmployee(emp);
	//keep the result in flash attribute
	attrs.addFlashAttribute("resultMsg",msg);
	//return LVN
	return "redirect:report";
	
}
@GetMapping("/delete")
public String deleteEmployeeById(@RequestParam("no")int no,RedirectAttributes attrs) {
	//use service
	String msg=empService.deleteEmployeeById(no);
	//keep the result in flash attribute
	attrs.addFlashAttribute("resultMsg", msg);
	//perform redirection
	return "redirect:report";
}
 
}






















