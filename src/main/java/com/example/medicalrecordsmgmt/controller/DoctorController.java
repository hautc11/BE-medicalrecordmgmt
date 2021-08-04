package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.DoctorCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.DoctorUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponse;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponseAsPage;
import com.example.medicalrecordsmgmt.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/doctors/{id}")
    public DoctorResponse getOne(@PathVariable int id){
        return doctorService.getOne(id);
    }

    @GetMapping("/doctors")
    public DoctorResponseAsPage getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size){
        return doctorService.getAll(page,size,search);
    }

    @PostMapping("/doctors")
    public void createDoctor(@RequestBody DoctorCreateRequest request){
        doctorService.createDoctor(request);
    }

    @PutMapping("/doctors")
    public void updateDoctor(@RequestBody DoctorUpdateRequest request){
        doctorService.updateDoctor(request);
    }

    @DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@PathVariable int id){
        doctorService.deleteDoctor(id);
    }

}
