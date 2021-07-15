package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.TestingFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.TestingFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.TestingFormResponse;
import com.example.medicalrecordsmgmt.domain.response.TestingFormResponseAsPage;
import com.example.medicalrecordsmgmt.service.TestingFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestingFormController {
    private final TestingFormService testingFormService;

    @GetMapping("/testingforms/{id}")
    public TestingFormResponse getOne(@PathVariable int id){
        return testingFormService.getOne(id);
    }

    @GetMapping("/testingforms")
    public TestingFormResponseAsPage getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return testingFormService.getAll(page,size,search);
    }

    @PostMapping("/testingforms")
    public void createTestingForm(@RequestBody TestingFormCreateRequest request){
        testingFormService.createTestingForm(request);
    }

    @PutMapping("/testingforms")
    public void updateTestingForm(@RequestBody TestingFormUpdateRequest request){
        testingFormService.updateTestingForm(request);
    }

    @DeleteMapping("/testingforms/{id}")
    private void deleteTestingForm(@PathVariable int id){
        testingFormService.deleteTestingForm(id);
    }
}
