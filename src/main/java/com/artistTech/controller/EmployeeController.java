package com.artistTech.controller;

import com.artistTech.dto.EmployeeResponseDto;
import com.artistTech.dto.request.EmpListOfIdDto;
import com.artistTech.dto.request.EmployeeIdRequestDto;
import com.artistTech.dto.request.EmployeeRequestDto;
import com.artistTech.dto.request.UpdateListOfemployeeDto;
import com.artistTech.dto.response.EmployeeResponseDtoWithMsg;
import com.artistTech.dto.response.EmployeeResponseStringDto;
import com.artistTech.service.EmployeeServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/saveEmp")
    public String saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        if (ObjectUtils.isEmpty(employeeRequestDto)) {
            return "data are not available";
        }
        return employeeService.saveAllEmployee(employeeRequestDto);

    }

    @GetMapping("/empDetails")
    public List<EmployeeResponseDto> getAllEmployeeDetails() {
        return employeeService.getAllEmployeeDetails();
    }

    @GetMapping("/get-emp-by-id")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@RequestBody EmployeeIdRequestDto employeeIdRequestDto) {
        EmployeeResponseDto employeeById = employeeService.getEmployeeById(employeeIdRequestDto);
        return ResponseEntity.ok(employeeById);
    }

    @GetMapping("/get-emp-by-name-email")
    public EmployeeResponseDto getEmployeeByNameAndEmailId(@RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto employeeByNameAndEmailId = employeeService.getEmployeeByNameAndEmailId(employeeRequestDto);

        return employeeByNameAndEmailId;
    }

    @GetMapping("/get-emp-list-by-deprt")
    public List<EmployeeResponseDto> getEmployeeListByDepartment(@RequestBody EmployeeRequestDto employeeRequestDto) {
        List<EmployeeResponseDto> employeeListByDepartment = employeeService.getEmployeeListByDepartment(employeeRequestDto.getDepartment());
        return employeeListByDepartment;
    }

    @DeleteMapping("/delete-emp-by-ids")
    public EmployeeResponseStringDto deleteEmpByListOfIds(@RequestBody EmpListOfIdDto empListOfIdDto) {
        return employeeService.deleteEmpByListOfIds(empListOfIdDto);

    }

    @PutMapping("/update-emp-by-ids/{id}")
    public EmployeeResponseDtoWithMsg updateEmployeeDetails(@PathVariable Integer id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeService.updateEmployeeDetails(id, employeeRequestDto);

    }

    @PutMapping("/update-emp-by-ids")
    public List<EmployeeResponseDtoWithMsg> updateListOfEmployeeDetails( @RequestBody UpdateListOfemployeeDto updateListOfemployeeDto) {
        return employeeService.updateListOfEmployeeDetails(updateListOfemployeeDto);
    }

}