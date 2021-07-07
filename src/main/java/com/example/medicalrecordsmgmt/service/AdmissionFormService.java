package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.AdmissionForm;
import com.example.medicalrecordsmgmt.domain.request.AdmissionFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.AdmissionFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.AdmissionFormResponse;
import com.example.medicalrecordsmgmt.domain.response.AdmissionFormResponseAsPage;
import com.example.medicalrecordsmgmt.repository.AdmissionFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionFormService {
    private final AdmissionFormRepository admissionFormRepository;

    public AdmissionFormResponse getOne(int id) {
        return admissionFormRepository.findById(id)
                .map(admissionForm -> {
                    var response = new AdmissionFormResponse();
                    response.setId(admissionForm.getId());
                    response.setDateIn(admissionForm.getDateIn());
                    response.setDateOut(admissionForm.getDateOut());
                    return response;
                })
                .orElseGet(() -> new AdmissionFormResponse());
    }

    public AdmissionFormResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var admissionFormPage = admissionFormRepository.findAll(pageable);
        return AdmissionFormResponseAsPage.of(admissionFormPage);
    }

    public void createAdmissionForm(AdmissionFormCreateRequest request) {
        var admissionForm = new AdmissionForm();
        admissionForm.setDateIn(request.getDateIn());
        admissionForm.setDateOut(request.getDateOut());
        admissionForm.setRecordId(request.getRecordId());
        admissionFormRepository.save(admissionForm);
    }

    public void updateAdmissionForm(AdmissionFormUpdateRequest request) {
        admissionFormRepository.findById(request.getId())
                .ifPresent(admissionForm -> {
                    admissionForm.setDateIn(request.getDateIn());
                    admissionForm.setDateOut(request.getDateOut());
                    admissionForm.setRecordId(request.getRecordId());
                    admissionFormRepository.save(admissionForm);
                });
    }

    public void deleteAdmissionForm(int id) {
        admissionFormRepository.findById(id)
                .ifPresent(admissionForm -> admissionFormRepository.delete(admissionForm));
    }
}
