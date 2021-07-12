package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.MedicalBill;
import com.example.medicalrecordsmgmt.domain.request.BillCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.BillUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.BillResponse;
import com.example.medicalrecordsmgmt.domain.response.BillResponseAsPage;
import com.example.medicalrecordsmgmt.domain.response.RecordResponse;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.BillRepository;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final RecordRepository recordRepository;

    public BillResponse getOne(int id) {
        return billRepository.findById(id)
                .map(medicalBill -> {
                    var medicalRecord = new RecordResponse();
                    medicalRecord.setId(medicalBill.getMedicalRecord().getId());
                    medicalRecord.setFullName(medicalBill.getMedicalRecord().getFullName());

                    var response = new BillResponse();
                    response.setId(medicalBill.getId());
                    response.setTotal(medicalBill.getTotal());
                    response.setCreateAt(medicalBill.getCreatedAt());
                    response.setRecordResponse(medicalRecord);
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
        var medicalrecord = recordRepository.findById((request.getRecordId()))
                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_ID));
        var medicalBill = new MedicalBill();
        medicalBill.setTotal(request.getTotal());
        medicalBill.setMedicalRecord(medicalrecord);
        billRepository.save(medicalBill);
    }

    public void updateMedicalBill(BillUpdateRequest request) {
        billRepository.findById(request.getId())
                .ifPresentOrElse(medicalBill -> {
                    medicalBill.setTotal(request.getTotal());
                    if(medicalBill.getMedicalRecord().getId()!= request.getRecordId()){
                        var medicalRecord = recordRepository.findById(request.getRecordId())
                                .orElseThrow((() -> new BadRequestException(ErrorCode.INVALID_ID)));

                        medicalBill.setMedicalRecord(medicalRecord);
                    }
                    billRepository.save(medicalBill);
                }, () -> {throw new BadRequestException(ErrorCode.INVALID_DOCTOR_ID);});
    }
}
