package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.entity.AppUser;
import com.example.medicalrecordsmgmt.domain.request.DepartmentCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.DepartmentUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponse;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponseAsPage;
import com.example.medicalrecordsmgmt.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/departments/{id}")
    public DepartmentResponse getOne(@PathVariable int id) {
        return departmentService.getOne(id);
    }

    @GetMapping("/departments")
    public DepartmentResponseAsPage getAll(
//            @AuthenticationPrincipal AppUser appUser,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
//        System.out.println(String.format("User %s want to get all Departments.", appUser.getEmail()));
//        log.info("User `{}` want to get all departments.", appUser.getEmail());
        return departmentService.getAll(page, size, search);
    }

    @PostMapping("/departments")
    public void createDepartment(@RequestBody DepartmentCreateRequest request) {
        departmentService.createDepartment(request);
    }

    @PutMapping("/departments")
    public void updateDepartment(@RequestBody DepartmentUpdateRequest request){
        departmentService.updateDepartment(request);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartment(id);
    }
}
