package com.artistTech.dto.response;

import com.artistTech.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDtoWithMsg {
    private Employee employee;
    private String msg;
}
