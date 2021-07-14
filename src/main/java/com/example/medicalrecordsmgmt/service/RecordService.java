package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.MedicalRecord;
import com.example.medicalrecordsmgmt.domain.request.RecordCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.RecordUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.RecordResponse;
import com.example.medicalrecordsmgmt.domain.response.RecordResponseAsPage;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordResponse getOne(int id) {
        return recordRepository.findById(id)
                .map(medicalRecord -> {
                    var response = new RecordResponse();
                    response.setId(medicalRecord.getId());
                    response.setFullName(medicalRecord.getFullName());
                    response.setDob(medicalRecord.getDob());
                    response.setSex(medicalRecord.getSex());
                    response.setAddress(medicalRecord.getAddress());
                    response.setPhoneNumber(medicalRecord.getPhoneNumber());
                    response.setCreateAt(medicalRecord.getCreatedAt());
                    response.setExpirationDate(medicalRecord.getExpirationDate());
                    return response;
                }).orElseGet(() -> new RecordResponse());
    }

    public RecordResponseAsPage getAll(int page, int size, String search) {
        var pageable = PageRequest.of(page, size);
        if (StringUtils.hasText(search)){
            var medicalRecordPage = recordRepository.searchMedicalRecord(search, pageable);
            return RecordResponseAsPage.of(medicalRecordPage);
        }else{
            var medicalRecordPage = recordRepository.findAll(pageable);
            return RecordResponseAsPage.of(medicalRecordPage);
        }
    }

    public void createMedicalRecord(RecordCreateRequest request) {
        var medicalRecord = new MedicalRecord();
        medicalRecord.setFullName(request.getFullName());
        medicalRecord.setDob(request.getDob());
        medicalRecord.setSex(request.getSex());
        medicalRecord.setAddress(request.getAddress());
        medicalRecord.setPhoneNumber(request.getPhoneNumber());
        recordRepository.save(medicalRecord);
    }

    public void updateMedicalRecord(RecordUpdateRequest request) {
        recordRepository.findById(request.getId())
                .ifPresent(medicalRecord -> {
                    medicalRecord.setFullName(request.getFullName());
                    medicalRecord.setDob(request.getDob());
                    medicalRecord.setSex(request.getSex());
                    medicalRecord.setAddress(request.getAddress());
                    medicalRecord.setPhoneNumber(request.getPhoneNumber());
                    recordRepository.save(medicalRecord);
                });
    }

    public void deleteMedicalRecord(int id) {
        recordRepository.findById(id)
                .ifPresent(medicalRecord -> recordRepository.delete(medicalRecord));
    }
}
