package com.artistTech.controller;

import com.artistTech.dto.request.HealthCheckRequestDto;
import com.artistTech.dto.response.HealthCheckResponseDto;
import com.artistTech.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

@RestController
public class HealthcheckController {
    @Autowired
    private HealthCheckService healthCheckService;

@GetMapping("/healthCheck")
    public HealthCheckResponseDto getHealthStatus( @RequestBody HealthCheckRequestDto healthCheckRequestDto){

     return healthCheckService.getHealthStatus(healthCheckRequestDto);

}


}
