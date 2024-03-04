package com.artistTech.service;

import com.artistTech.dto.request.HealthCheckRequestDto;
import com.artistTech.dto.response.HealthCheckResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
@Service
public class HealthCheckService {

    public HealthCheckResponseDto getHealthStatus(HealthCheckRequestDto healthCheckRequestDto) {
        if (!ObjectUtils.isEmpty(healthCheckRequestDto.getApplicationName())) {
           return HealthCheckResponseDto.builder()
                    .appDescription("The Application is up & running")
                    .statusCode("200 OK")
                    .build();
        } else {
            return HealthCheckResponseDto.builder()
                    .appDescription("The Application is down & not running")
                    .statusCode("500 INTERNAL SERVER ERROR")
                    .build();
        }
    }
}
