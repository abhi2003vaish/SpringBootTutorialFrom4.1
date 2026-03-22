package com.codingshuttle.abhi.prod_ready_features.prod_ready_features.clients.impl;

import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log= LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("trying to retrieve all employees in getAllEmployees");
        try{
            log.info("attempting to call the restclient method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList=restClient.get()
                    .uri("EmployeeControllerServiceLayer")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved the employee in getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {} {}", employeeDTOList.getData(),"hello");
            return employeeDTOList.getData();
        }catch(Exception e){
            log.error("Exception occurred in getAllEmployees:",e);
            throw new RuntimeException(e);
        }



    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("trying to get employee by Id getEmployeeById with id:{}",employeeId);
        try{
            log.info("attempting to call the restclient method in getEmployeeById");
            ApiResponse<EmployeeDTO> employeeResponse=restClient.get()
                    .uri("EmployeeControllerServiceLayer/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.info("successfully executed");
            return employeeResponse.getData();
        }catch(Exception e){
            log.error("now .........");
            log.error("Exception occurred in getEmployeeById:",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            log.trace("trying to create Employees with information {}",employeeDTO);
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse=restClient.post()
                    .uri("EmployeeControllerServiceLayer")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->{
                        log.debug("4xxClient error occurred during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("successfully created a new employee : {}", employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
        }catch(Exception e){
            log.error("Exception occurred in createNewEmployee:",e);
            throw new RuntimeException(e);
        }
    }
}
