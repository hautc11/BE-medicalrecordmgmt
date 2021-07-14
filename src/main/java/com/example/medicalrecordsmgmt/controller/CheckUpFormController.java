package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.CheckUpFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.CheckUpFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.request.RecordUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.CheckUpFormResponse;
import com.example.medicalrecordsmgmt.domain.response.CheckUpFormResponseAsPage;
import com.example.medicalrecordsmgmt.service.CheckUpFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CheckUpFormController {
    private final CheckUpFormService checkUpFormService;

    @GetMapping("/checkupforms/{id}")
    public CheckUpFormResponse getOne(@PathVariable int id){
        return checkUpFormService.getOne(id);
    }

    @GetMapping("/checkupforms")
    public CheckUpFormResponseAsPage getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return checkUpFormService.getAll(page,size);
    }

    @PostMapping("/checkupforms")
    public void createCheckUpForm(@RequestBody CheckUpFormCreateRequest request){
        checkUpFormService.createCheckUpForm(request);
    }

    @PutMapping("/checkupforms")
    public void updateCheckUpForm(@RequestBody CheckUpFormUpdateRequest request){
        checkUpFormService.updateCheckUpForm(request);
    }

    @DeleteMapping("/checkupforms/{id}")
    public void deleteCheckUpForm(@PathVariable int id){
        checkUpFormService.deleteCheckUpForm(id);
    }
}
