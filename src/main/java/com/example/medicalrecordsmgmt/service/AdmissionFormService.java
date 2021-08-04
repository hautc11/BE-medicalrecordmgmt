package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.AdmissionForm;
import com.example.medicalrecordsmgmt.domain.entity.MedicalRecord;
import com.example.medicalrecordsmgmt.domain.request.AdmissionFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.AdmissionFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.AdmissionFormResponse;
import com.example.medicalrecordsmgmt.domain.response.AdmissionFormResponseAsPage;
import com.example.medicalrecordsmgmt.domain.response.RecordResponse;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.AdmissionFormRepository;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AdmissionFormService {
    private final AdmissionFormRepository admissionFormRepository;
    private final RecordRepository recordRepository;

    public AdmissionFormResponse getOne(int id) {
        return admissionFormRepository.findById(id)
                .map(admissionForm -> {
                    var medicalRecord = new RecordResponse();
                    medicalRecord.setId(admissionForm.getMedicalRecord().getId());
                    medicalRecord.setFullName(admissionForm.getMedicalRecord().getFullName());
                    medicalRecord.setPhoneNumber(admissionForm.getMedicalRecord().getPhoneNumber());

                    var response = new AdmissionFormResponse();
                    response.setId(admissionForm.getId());
                    response.setDateIn(admissionForm.getDateIn());
                    response.setDateOut(admissionForm.getDateOut());
                    response.setRecordResponse(medicalRecord);
                    return response;
                })
                .orElseGet(() -> new AdmissionFormResponse());
    }

    public AdmissionFormResponseAsPage getAll(int page, int size, String search) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"createdAt"));
        if (StringUtils.hasText(search)){
            var admissionFormPage = admissionFormRepository.searchAdmissionForm(search,pageable);
            return AdmissionFormResponseAsPage.of(admissionFormPage);
        }
        var admissionFormPage = admissionFormRepository.findAll(pageable);
        return AdmissionFormResponseAsPage.of(admissionFormPage);
    }

    public void createAdmissionForm(AdmissionFormCreateRequest request) {
        var medicalrecord = recordRepository.findById((request.getRecordId()))
                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_MEDICAL_RECORD_ID));
        var admissionForm = new AdmissionForm();
        admissionForm.setDateIn(request.getDateIn());
        admissionForm.setDateOut(request.getDateOut());
        admissionForm.setMedicalRecord(medicalrecord);
        admissionFormRepository.save(admissionForm);
    }

    public void updateAdmissionForm(AdmissionFormUpdateRequest request) {
        admissionFormRepository.findById(request.getId())
                .ifPresentOrElse(admissionForm -> {
                    admissionForm.setDateIn(request.getDateIn());
                    admissionForm.setDateOut(request.getDateOut());
                    if (admissionForm.getMedicalRecord().getId() != request.getRecordId()){
                        var medicalRecord = recordRepository.findById(request.getRecordId())
                                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_MEDICAL_RECORD_ID));
                        admissionForm.setMedicalRecord(medicalRecord);
                    }
                    admissionFormRepository.save(admissionForm);
                }, ()->{throw new BadRequestException(ErrorCode.INVALID_ID);});
    }

    public void deleteAdmissionForm(int id) {
        admissionFormRepository.findById(id)
                .ifPresent(admissionForm -> admissionFormRepository.delete(admissionForm));
    }
}
