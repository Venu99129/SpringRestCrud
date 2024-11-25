package com.week2.SpringRestCrud.controllers;

import com.week2.SpringRestCrud.dto.EmployeeDto;
import com.week2.SpringRestCrud.entities.EmployeeEntity;
import com.week2.SpringRestCrud.repositorys.EmployeeRepository;
import com.week2.SpringRestCrud.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/getSecretMessage")
    public String getSecretMessage(){
        return "secrete message :#@&&()&&@";
    }

    @GetMapping(path = "/{employeeId}" )
    public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDto> employeeDto =  employeeService.findById(id);
        return employeeDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false , name = "inputAge") Integer age , @RequestParam(required = false) String filter){
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto) , HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable(name = "employeeId") Long id){
        boolean gotDeleted = employeeService.deleteEmployee(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId , @RequestBody @Valid EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId , employeeDto));
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployee(@PathVariable Long employeeId ,
                                             @RequestBody Map<String , Object> updates){
        EmployeeDto employeeDto = employeeService.updatePartialEmployee(employeeId, updates);
        if (employeeDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }
}
