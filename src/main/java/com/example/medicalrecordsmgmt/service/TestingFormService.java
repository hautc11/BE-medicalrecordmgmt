package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.Doctor;
import com.example.medicalrecordsmgmt.domain.entity.TestingForm;
import com.example.medicalrecordsmgmt.domain.request.TestingFormCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.TestingFormUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponse;
import com.example.medicalrecordsmgmt.domain.response.TestingFormResponse;
import com.example.medicalrecordsmgmt.domain.response.TestingFormResponseAsPage;
import com.example.medicalrecordsmgmt.repository.TestingFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestingFormService {

    private final TestingFormRepository testingFormRepository;

    public TestingFormResponse getOne(int id) {
        return testingFormRepository.findById(id)
                .map(testingForm -> {
                    var response = new TestingFormResponse();
                    response.setId(testingForm.getId());
                    response.setTestName(testingForm.getTestName());
                    response.setTestDate(testingForm.getTestDate());
                    response.setResult(testingForm.getResult());
                    response.setRecordId(testingForm.getRecordId());
                    return response;
                }).orElseGet(() -> new TestingFormResponse());
    }

    public TestingFormResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page,size);
        var testingFormPage = testingFormRepository.findAll(pageable);
        return TestingFormResponseAsPage.of(testingFormPage);
    }

    public void createTestingForm(TestingFormCreateRequest request) {
        var testingForm = new TestingForm();
        testingForm.setTestName(request.getTestName());
        testingForm.setTestDate(request.getTestDate());
        testingForm.setResult(request.getResult());
        testingForm.setRecordId(request.getRecordId());
        testingFormRepository.save(testingForm);
    }

    public void updateTestingForm(TestingFormUpdateRequest request) {
        testingFormRepository.findById(request.getId())
                .ifPresent(testingForm -> {
                    testingForm.setTestName(request.getTestName());
                    testingForm.setTestDate(request.getTestDate());
                    testingForm.setResult(request.getResult());
                    testingForm.setRecordId(request.getRecordId());
                    testingFormRepository.save(testingForm);
                });
    }

    public void deleteTestingForm(int id) {
        testingFormRepository.findById(id)
                .ifPresent(testingForm -> testingFormRepository.delete(testingForm));
    }
}
