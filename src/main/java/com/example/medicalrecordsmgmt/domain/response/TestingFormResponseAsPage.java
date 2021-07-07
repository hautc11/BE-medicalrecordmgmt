package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.MedicalBill;
import com.example.medicalrecordsmgmt.domain.entity.TestingForm;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TestingFormResponseAsPage {
    private long totalPages;
    private long totalItems;
    private int page;
    private int size;
    private List<TestingFormResponse> items;

    public static TestingFormResponseAsPage of(Page<TestingForm> testingFormPage){
        var items = testingFormPage.stream()
                .map(testingForm -> {
                    var response = new TestingFormResponse();
                    response.setId(testingForm.getId());
                    response.setTestName(testingForm.getTestName());
                    response.setTestDate(testingForm.getTestDate());
                    response.setRecordId(testingForm.getRecordId());
                    return response;
                })
                .collect(Collectors.toList());

        var response = new TestingFormResponseAsPage();
        response.setTotalPages(testingFormPage.getTotalPages());
        response.setTotalItems(testingFormPage.getTotalElements());
        response.setPage(testingFormPage.getNumber());
        response.setSize(testingFormPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
