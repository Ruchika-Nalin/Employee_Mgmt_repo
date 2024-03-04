package com.artistTech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeResponseDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String birthDate;
    private String title;
    private String department;
}
