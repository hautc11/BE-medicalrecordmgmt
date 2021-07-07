package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.MedicalBill;
import com.example.medicalrecordsmgmt.domain.request.BillCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.BillUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.BillResponse;
import com.example.medicalrecordsmgmt.domain.response.BillResponseAsPage;
import com.example.medicalrecordsmgmt.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;

    public BillResponse getOne(int id) {
        return billRepository.findById(id)
                .map(medicalBill -> {
                    var response = new BillResponse();
                    response.setId(medicalBill.getId());
                    response.setTotal(medicalBill.getTotal());
                    response.setRecordId(medicalBill.getRecordId());
                    return response;
                }).orElseGet(() -> new BillResponse());
    }

    public BillResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page,size);
        var medicalBill = billRepository.findAll(pageable);
        return BillResponseAsPage.of(medicalBill);
    }


    public void deleteMedicalBill(int id) {
        billRepository.findById(id)
                .ifPresent(medicalBill -> billRepository.delete(medicalBill));
    }

    public void createMedicalBill(BillCreateRequest request) {
        var medicalBill = new MedicalBill();
        medicalBill.setTotal(request.getTotal());
        medicalBill.setRecordId(request.getRecordId());
        billRepository.save(medicalBill);
    }

    public void updateMedicalBill(BillUpdateRequest request) {
        billRepository.findById(request.getId())
                .ifPresent(medicalBill -> {
                    medicalBill.setTotal(request.getTotal());
                    medicalBill.setRecordId(request.getRecordId());
                    billRepository.save(medicalBill);
                });
    }
}
