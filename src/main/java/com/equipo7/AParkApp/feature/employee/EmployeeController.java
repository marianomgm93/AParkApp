package com.equipo7.AParkApp.feature.employee;


import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeRequest;
import com.equipo7.AParkApp.feature.employee.domain.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Employee")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request){
        EmployeeResponse employeeResponse = employeeService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeResponse);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> upgrade(@PathVariable UUID id, @RequestBody EmployeeRequest request){
        return ResponseEntity.ok(employeeService.update(id,request));
    }



}
