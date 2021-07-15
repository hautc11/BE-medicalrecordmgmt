package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.BillCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.BillUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.BillResponse;
import com.example.medicalrecordsmgmt.domain.response.BillResponseAsPage;
import com.example.medicalrecordsmgmt.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping("/medicalbills/{id}")
    public BillResponse getOne(@PathVariable int id){
        return billService.getOne(id);
    }

    @GetMapping("/medicalbills")
    public BillResponseAsPage getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size){
        return billService.getAll(page,size,search);
    }

    @PostMapping("/medicalbills")
    public void createMedicalBill(@RequestBody BillCreateRequest request){
        billService.createMedicalBill(request);
    }

    @PutMapping("/medicalbills")
    public void updateMedicalBill(@RequestBody BillUpdateRequest request){
        billService.updateMedicalBill(request);
    }

    @DeleteMapping("/medicalbills/{id}")
    private void deleteMedicalBill(@PathVariable int id){
        billService.deleteMedicalBill(id);
    }
}
