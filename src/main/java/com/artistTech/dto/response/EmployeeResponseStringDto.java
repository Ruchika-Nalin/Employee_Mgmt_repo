package com.artistTech.dto.response;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseStringDto {
private List<EmployeeIdAndStringMassageDto> employeeIdAndStringMassageDtos;
}
