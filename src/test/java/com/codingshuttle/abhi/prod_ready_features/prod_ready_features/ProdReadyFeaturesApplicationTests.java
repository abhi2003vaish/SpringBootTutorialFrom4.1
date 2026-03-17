package com.codingshuttle.abhi.prod_ready_features.prod_ready_features;

import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.clients.impl.EmployeeClientImpl;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

    @Autowired
    private EmployeeClient employeeClient; // it can be seen that we are instantiating
//    OR
//    private EmployeeClientImpl employeeClient;

    @Test
    @Order(3)
    void getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
        System.out.println(employeeDTOList);
    }

    @Test
    @Order(2)
    void getEmployeeByIdTest() {
        EmployeeDTO employeeDTO=employeeClient.getEmployeeById(1L);
        System.out.println(employeeDTO);
    }

    @Test
    @Order(1)
    void createNewEmployeeTest() {
        EmployeeDTO employeeDTO=new EmployeeDTO(null,"abhishek","abhi@Gmail.com",20,"USER",
                50000.0, LocalDate.of(2026,1,2),true);
        EmployeeDTO savedEmployeeDTO=employeeClient.createNewEmployee(employeeDTO);
        System.out.println(savedEmployeeDTO);
    }



}
