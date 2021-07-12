package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.Doctor;
import com.example.medicalrecordsmgmt.domain.entity.TestingForm;
import com.example.medicalrecordsmgmt.domain.request.TestingFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.TestingFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponse;
import com.example.medicalrecordsmgmt.domain.response.RecordResponse;
import com.example.medicalrecordsmgmt.domain.response.TestingFormResponse;
import com.example.medicalrecordsmgmt.domain.response.TestingFormResponseAsPage;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import com.example.medicalrecordsmgmt.repository.TestingFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestingFormService {

    private final TestingFormRepository testingFormRepository;
    private final RecordRepository recordRepository;

    public TestingFormResponse getOne(int id) {
        return testingFormRepository.findById(id)
                .map(testingForm -> {
                    var medicalRecord = new RecordResponse();
                    medicalRecord.setId(testingForm.getMedicalRecord().getId());
                    medicalRecord.setFullName(testingForm.getMedicalRecord().getFullName());

                    var response = new TestingFormResponse();
                    response.setId(testingForm.getId());
                    response.setTestName(testingForm.getTestName());
                    response.setTestDate(testingForm.getTestDate());
                    response.setResult(testingForm.getResult());
                    response.setRecordResponse(medicalRecord);
                    return response;
                }).orElseGet(() -> new TestingFormResponse());
    }

    public TestingFormResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page,size);
        var testingFormPage = testingFormRepository.findAll(pageable);
        return TestingFormResponseAsPage.of(testingFormPage);
    }

    public void createTestingForm(TestingFormCreateRequest request) {
        var medicalrecord = recordRepository.findById((request.getRecordId()))
                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_ID));
        var testingForm = new TestingForm();
        testingForm.setTestName(request.getTestName());
        testingForm.setTestDate(request.getTestDate());
        testingForm.setResult(request.getResult());
        testingForm.setMedicalRecord(medicalrecord);
        testingFormRepository.save(testingForm);
    }

    public void updateTestingForm(TestingFormUpdateRequest request) {
        testingFormRepository.findById(request.getId())
                .ifPresentOrElse(testingForm -> {
                    testingForm.setTestName(request.getTestName());
                    testingForm.setTestDate(request.getTestDate());
                    testingForm.setResult(request.getResult());
                    if (testingForm.getMedicalRecord().getId()!= request.getRecordId()){
                        var medicalRecord = recordRepository.findById(request.getRecordId())
                                .orElseThrow((() -> new BadRequestException(ErrorCode.INVALID_ID)));
                        testingForm.setMedicalRecord(medicalRecord);
                    }
                    testingFormRepository.save(testingForm);
                }, ()->{throw new BadRequestException(ErrorCode.INVALID_ID);});
    }

    public void deleteTestingForm(int id) {
        testingFormRepository.findById(id)
                .ifPresent(testingForm -> testingFormRepository.delete(testingForm));
    }
}
