package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.MedicineCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.MedicineUpdateRequest;
import com.example.medicalrecordsmgmt.domain.request.PrescriptionCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.PrescriptionUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponseAsPage;
import com.example.medicalrecordsmgmt.domain.response.PrescriptionResponse;
import com.example.medicalrecordsmgmt.domain.response.PrescriptionResponseAsPage;
import com.example.medicalrecordsmgmt.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @GetMapping("/prescriptions/{id}")
    public PrescriptionResponse getOne(@PathVariable int id){
        return prescriptionService.getOne(id);
    }

    @GetMapping("/prescriptions")
    public PrescriptionResponseAsPage getAll(
            @RequestParam(defaultValue = "0") int search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return prescriptionService.getAll(page,size,search);
    }
    @PostMapping("/prescriptions")
    public void createPrescription(@RequestBody PrescriptionCreateRequest request){
        prescriptionService.createPrescription(request);
    }

    @PutMapping("/prescriptions")
    public void updatePrescription(@RequestBody PrescriptionUpdateRequest request){
        prescriptionService.updatePrescription(request);
    }

    @DeleteMapping("/prescriptions/{id}")
    public void deletePrescription(@PathVariable int id){
        prescriptionService.deletePrescription(id);
    }
}
