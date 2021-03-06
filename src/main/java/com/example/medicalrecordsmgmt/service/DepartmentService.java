package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.Department;
import com.example.medicalrecordsmgmt.domain.request.DepartmentCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.DepartmentUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponse;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponseAsPage;
import com.example.medicalrecordsmgmt.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentResponse getOne(int id){
        return departmentRepository.findById(id)
                .map(department -> {
                    var response = new DepartmentResponse();
                    response.setId(department.getId());
                    response.setName(department.getDepartmentName());
                    return response;
                })
                .orElseGet(DepartmentResponse::new);
    }

    public void createDepartment(DepartmentCreateRequest request) {
        var department = new Department();
        department.setDepartmentName(request.getName());
        departmentRepository.save(department);
    }

    public DepartmentResponseAsPage getAll(int page, int size, String search) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"createdAt"));
        if (StringUtils.hasText(search)){
            var departmentPage = departmentRepository.searchDepartment(search,pageable);
            return DepartmentResponseAsPage.of(departmentPage);
        }
        var departmentPage = departmentRepository.findAll(pageable);
        return DepartmentResponseAsPage.of(departmentPage);
    }

    public void updateDepartment(DepartmentUpdateRequest request) {
        departmentRepository.findById(request.getId())
                .ifPresent(department -> {
                    department.setDepartmentName(request.getName());
                    departmentRepository.save(department);
                });
    }

    public void deleteDepartment(int id) {
        departmentRepository.findById(id)
                .ifPresent(departmentRepository::delete);
    }
}
