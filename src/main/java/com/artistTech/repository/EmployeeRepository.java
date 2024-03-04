package com.artistTech.repository;

import com.artistTech.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
    Employee findEmployeeByFirstNameAndEmail(String firstName, String email);

   public List<Employee> findAllEmployeeByDepartment(String department);
}
