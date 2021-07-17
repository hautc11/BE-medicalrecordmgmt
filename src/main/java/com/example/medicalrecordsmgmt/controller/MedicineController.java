package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.MedicineCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.MedicineUpdateRequest;
import com.example.medicalrecordsmgmt.domain.request.RecordCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.RecordUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.MedicineResponse;
import com.example.medicalrecordsmgmt.domain.response.MedicineResponseAsPage;
import com.example.medicalrecordsmgmt.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping("/medicines/{id}")
    public MedicineResponse getOne(@PathVariable int id){
        return medicineService.getOne(id);
    }

    @GetMapping("/medicines")
    public MedicineResponseAsPage getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ){
        return medicineService.getAll(page,size,search);
    }

    @PostMapping("/medicines")
    public void createMedicine(@RequestBody MedicineCreateRequest request){
        medicineService.createMedicine(request);
    }

    @PutMapping("/medicines")
    public void updateMedicine(@RequestBody MedicineUpdateRequest request){
        medicineService.updateMedicine(request);
    }

    @DeleteMapping("/medicines/{id}")
    public void deleteMedicine(@PathVariable int id){
        medicineService.deleteMedicine(id);
    }
}
