package com.artistTech.dto.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeRequestDto {

    @NonNull
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String birthDate;
    private String title;
    private String department;
}
