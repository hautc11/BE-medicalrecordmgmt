package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.AdmissionForm;
import com.example.medicalrecordsmgmt.domain.entity.CheckUpForm;
import com.example.medicalrecordsmgmt.domain.request.CheckUpFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.CheckUpFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.*;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.CheckUpFormRepository;
import com.example.medicalrecordsmgmt.repository.DoctorRepository;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckUpFormService {
    private final CheckUpFormRepository checkUpFormRepository;
    private final RecordRepository recordRepository;
    private final DoctorRepository doctorRepository;

    public CheckUpFormResponse getOne(int id) {
        return checkUpFormRepository.findById(id)
                .map(checkUpForm -> {
                    var medicalRecord = new RecordResponse();
                    medicalRecord.setFullName(checkUpForm.getMedicalRecord().getFullName());
                    medicalRecord.setId(checkUpForm.getMedicalRecord().getId());

                    var doctor = new DoctorResponse();
                    doctor.setId(checkUpForm.getDoctor().getId());
                    doctor.setFullName(checkUpForm.getDoctor().getFullName());

                    var response = new CheckUpFormResponse();
                    response.setId(checkUpForm.getId());
                    response.setSymptom(checkUpForm.getSymptom());
                    response.setDoctorResponse(doctor);
                    response.setRecordResponse(medicalRecord);
                    return response;
                })
                .orElseGet(()->new CheckUpFormResponse());
    }

    public CheckUpFormResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var checkUpFormPage = checkUpFormRepository.findAll(pageable);
        return CheckUpFormResponseAsPage.of(checkUpFormPage);
    }


    public void createCheckUpForm(CheckUpFormCreateRequest request) {
        var medicalrecord = recordRepository.findById((request.getRecordId()))
                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_MEDICAL_RECORD_ID));
        var doctor = doctorRepository.findById((request.getDoctorId()))
                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_DOCTOR_ID));
        var checkUpForm = new CheckUpForm();
        checkUpForm.setSymptom(request.getSymptom());
        checkUpForm.setMedicalRecord(medicalrecord);
        checkUpForm.setDoctor(doctor);
        checkUpFormRepository.save(checkUpForm);
    }


    public void updateCheckUpForm(CheckUpFormUpdateRequest request) {
        checkUpFormRepository.findById(request.getId())
                .ifPresentOrElse(checkUpForm -> {
                    checkUpForm.setSymptom(request.getSymptom());
                    if (checkUpForm.getMedicalRecord().getId() != request.getRecordId()){
                        var medicalRecord = recordRepository.findById(request.getRecordId())
                                .orElseThrow(()->new BadRequestException(ErrorCode.INVALID_MEDICAL_RECORD_ID));
                        checkUpForm.setMedicalRecord(medicalRecord);
                    }
                    if (checkUpForm.getDoctor().getId() != request.getDoctorId()){
                        var doctor = doctorRepository.findById(request.getDoctorId())
                                .orElseThrow(()->new BadRequestException(ErrorCode.INVALID_DOCTOR_ID));
                        checkUpForm.setDoctor(doctor);
                    }
                    checkUpFormRepository.save(checkUpForm);
                }, () -> {throw new BadRequestException(ErrorCode.INVALID_ID);});
    }

    public void deleteCheckUpForm(int id) {
        checkUpFormRepository.findById(id)
                .ifPresent(checkUpForm -> checkUpFormRepository.delete(checkUpForm));
    }
}
