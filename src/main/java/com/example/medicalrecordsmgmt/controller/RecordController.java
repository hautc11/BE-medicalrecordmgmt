package com.example.medicalrecordsmgmt.controller;


import com.example.medicalrecordsmgmt.domain.request.RecordCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.RecordUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.RecordResponse;
import com.example.medicalrecordsmgmt.domain.response.RecordResponseAsPage;
import com.example.medicalrecordsmgmt.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/medicalrecords/{id}")
    public RecordResponse getOne(@PathVariable int id){
        return recordService.getOne(id);
    }

    @GetMapping("/medicalrecords")
    public RecordResponseAsPage getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return recordService.getAll(page,size);
    }

    @PostMapping("/medicalrecords")
    public void createMedicalRecord(@RequestBody RecordCreateRequest request){
        recordService.createMedicalRecord(request);
    }

    @PutMapping("/medicalrecords")
    public void updateMedicalRecord(@RequestBody RecordUpdateRequest request){
        recordService.updateMedicalRecord(request);
    }

    @DeleteMapping("/medicalrecords/{id}")
    public void deleteMedicalRecord(@PathVariable int id){
        recordService.deleteMedicalRecord(id);
    }
}
