package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.AdmissionFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.AdmissionFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.request.DepartmentCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.DepartmentUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.AdmissionFormResponse;
import com.example.medicalrecordsmgmt.domain.response.AdmissionFormResponseAsPage;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponse;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponseAsPage;
import com.example.medicalrecordsmgmt.service.AdmissionFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdmissionFormController {
    private final AdmissionFormService admissionFormService;

    @GetMapping("/admissionforms/{id}")
    public AdmissionFormResponse getOne(@PathVariable int id) {
        return admissionFormService.getOne(id);
    }

    @GetMapping("/admissionforms")
    public AdmissionFormResponseAsPage getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return admissionFormService.getAll(page, size);
    }

    @PostMapping("/admissionforms")
    public void createAdmissionForm(@RequestBody AdmissionFormCreateRequest request) {
        admissionFormService.createAdmissionForm(request);
    }

    @PutMapping("/admissionforms")
    public void updateAdmissionForm(@RequestBody AdmissionFormUpdateRequest request){
        admissionFormService.updateAdmissionForm(request);
    }

    @DeleteMapping("/admissionforms/{id}")
    public void deleteAdmissionForm(@PathVariable int id){
        admissionFormService.deleteAdmissionForm(id);
    }
}
