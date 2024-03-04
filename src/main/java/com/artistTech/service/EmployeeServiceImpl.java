package com.artistTech.service;

import com.artistTech.dto.EmployeeResponseDto;
import com.artistTech.dto.request.EmpListOfIdDto;
import com.artistTech.dto.request.EmployeeIdRequestDto;
import com.artistTech.dto.request.EmployeeRequestDto;
import com.artistTech.dto.request.UpdateListOfemployeeDto;
import com.artistTech.dto.response.EmployeeIdAndStringMassageDto;
import com.artistTech.dto.response.EmployeeResponseDtoWithMsg;
import com.artistTech.dto.response.EmployeeResponseStringDto;
import com.artistTech.entity.Employee;
import com.artistTech.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {
    @Autowired
    private EmployeeRepository employeeRepository;



    public String saveAllEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = Employee.builder()
                .firstName(employeeRequestDto.getFirstName())
                .lastName(employeeRequestDto.getLastName())
                .department(employeeRequestDto.getDepartment())
                .phone(employeeRequestDto.getPhone())
                .birthDate(employeeRequestDto.getBirthDate())
                .title(employeeRequestDto.getTitle())
                .email(employeeRequestDto.getEmail())
                .build();

        Employee save = employeeRepository.save(employee);

        return "data saved successfully";


    }

    public List<EmployeeResponseDto> getAllEmployeeDetails() {

        List<Employee> employeeList = employeeRepository.findAll();

        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();

        employeeList.forEach(e -> employeeResponseDtoList.add(EmployeeResponseDto.builder().id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .department(e.getDepartment())
                .phone(e.getPhone())
                .birthDate(e.getBirthDate())
                .title(e.getTitle())
                .email(e.getEmail())
                .build()));


        return employeeResponseDtoList;
    }

    public EmployeeResponseDto getEmployeeById(EmployeeIdRequestDto employeeIdRequestDto) {
        Optional<Employee> employeeById = employeeRepository.findById(employeeIdRequestDto.getId());
        if (employeeById.isPresent()) {
            Employee employee = employeeById.get();

            EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .department(employee.getDepartment())
                    .phone(employee.getPhone())
                    .birthDate(employee.getBirthDate())
                    .title(employee.getTitle())
                    .email(employee.getEmail())
                    .build();

            return employeeResponseDto;
        }

        return new EmployeeResponseDto();
        //EmployeeResponseDto.builder().build();
    }

    public EmployeeResponseDto getEmployeeByNameAndEmailId(EmployeeRequestDto employeeRequestDto) {
        Employee employeeByNameAndEmail = employeeRepository.findEmployeeByFirstNameAndEmail(employeeRequestDto.getFirstName(),
                employeeRequestDto.getEmail());
        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .id(employeeByNameAndEmail.getId())
                .firstName(employeeByNameAndEmail.getFirstName())
                .lastName(employeeByNameAndEmail.getLastName())
                .department(employeeByNameAndEmail.getDepartment())
                .phone(employeeByNameAndEmail.getPhone())
                .birthDate(employeeByNameAndEmail.getBirthDate())
                .title(employeeByNameAndEmail.getTitle())
                .email(employeeByNameAndEmail.getEmail())
                .build();


        return employeeResponseDto;
    }


    public List<EmployeeResponseDto> getEmployeeListByDepartment(String department) {
        List<Employee> allEmployeeByDepartment = employeeRepository.findAllEmployeeByDepartment(department);
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        for (Employee emp : allEmployeeByDepartment) {
            EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                    .id(emp.getId())
                    .firstName(emp.getFirstName())
                    .lastName(emp.getLastName())
                    .department(emp.getDepartment())
                    .phone(emp.getPhone())
                    .birthDate(emp.getBirthDate())
                    .title(emp.getTitle())
                    .email(emp.getEmail())
                    .build();
            employeeResponseDtoList.add(employeeResponseDto);

        }
        return employeeResponseDtoList;
    }

    public EmployeeResponseStringDto deleteEmpByListOfIds(EmpListOfIdDto empListOfIdDto) {
        List<EmployeeIdAndStringMassageDto> idAndStringMassageDtoList = new ArrayList<>();

        for (EmployeeIdRequestDto empId : empListOfIdDto.getIds()) {
            Optional<Employee> employee = employeeRepository.findById(empId.getId());

            if (employee.isPresent()) {
                Employee employee1 = employee.get();
                employeeRepository.deleteById(employee1.getId());

                EmployeeIdAndStringMassageDto idAndStringMassageDto = EmployeeIdAndStringMassageDto.builder()
                        .message("employee deleted successfully with id " + employee1.getId())
                        .id(employee1.getId())
                        .build();
                idAndStringMassageDtoList.add(idAndStringMassageDto);
            } else {
                EmployeeIdAndStringMassageDto idAndStringMassageDto = EmployeeIdAndStringMassageDto.builder()
                        .message("Employee detail not present for the given = " + empId.getId())
                        .id(empId.getId())
                        .build();
                idAndStringMassageDtoList.add(idAndStringMassageDto);
            }
        }
        return EmployeeResponseStringDto.builder()
                .employeeIdAndStringMassageDtos(idAndStringMassageDtoList)
                .build();
    }

    public EmployeeResponseDtoWithMsg updateEmployeeDetails(Integer id, EmployeeRequestDto employeeRequestDto) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            employee.setFirstName(employeeRequestDto.getFirstName());
            employee.setLastName(employeeRequestDto.getLastName());
            employee.setBirthDate(employeeRequestDto.getBirthDate());
            employee.setDepartment(employeeRequestDto.getDepartment());
            employee.setEmail(employeeRequestDto.getEmail());
            employee.setPhone(employeeRequestDto.getPhone());
            employee.setTitle(employeeRequestDto.getTitle());
            Employee save = employeeRepository.save(employee);

            return EmployeeResponseDtoWithMsg.builder()
                    .msg("employee updated sucessfully")
                    .employee(save)
                    .build();
        } else {
            return EmployeeResponseDtoWithMsg.builder()
                    .msg("employee not present with this id")
                    .employee(new Employee())
                    .build();
        }
    }

    public List<EmployeeResponseDtoWithMsg> updateListOfEmployeeDetails( UpdateListOfemployeeDto updateListOfemployeeDto) {

        List<EmployeeResponseDtoWithMsg> employeeResponseDtoWithMsgList = new ArrayList<>();
        for (EmployeeRequestDto emp : updateListOfemployeeDto.getEmployees()) {
            Optional<Employee> employeeFromDb = employeeRepository.findById(emp.getId());
            if (employeeFromDb.isPresent()) {
                Employee employee = employeeFromDb.get();
                employee.setFirstName(emp.getFirstName());
                employee.setLastName(emp.getLastName());
                employee.setBirthDate(emp.getBirthDate());
                employee.setDepartment(emp.getDepartment());
                employee.setEmail(emp.getEmail());
                employee.setPhone(emp.getPhone());
                employee.setTitle(emp.getTitle());
                Employee save = employeeRepository.save(employee);

                employeeResponseDtoWithMsgList.add( EmployeeResponseDtoWithMsg.builder()
                        .msg("employee updated sucessfully")
                        .employee(save)
                        .build());
            } else {
                employeeResponseDtoWithMsgList.add(EmployeeResponseDtoWithMsg.builder()
                        .msg("employee not present with this id")
                        .employee(new Employee())
                        .build());
            }
        }
        return employeeResponseDtoWithMsgList;
    }
}









