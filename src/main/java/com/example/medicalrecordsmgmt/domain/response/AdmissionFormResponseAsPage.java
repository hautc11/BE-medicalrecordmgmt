package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.AdmissionForm;
import com.example.medicalrecordsmgmt.domain.entity.Department;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdmissionFormResponseAsPage {
    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<AdmissionFormResponse> items;

    public static AdmissionFormResponseAsPage of(Page<AdmissionForm> admissionFormPage){
        var items = admissionFormPage.stream()
                .map(admissionForm -> {
                    var response = new AdmissionFormResponse();
                    response.setId(admissionForm.getId());
                    response.setDateIn(admissionForm.getDateIn());
                    response.setDateOut(admissionForm.getDateOut());
                    return response;
                })
                .collect(Collectors.toList());

        var response = new AdmissionFormResponseAsPage();
        response.setTotalItems(admissionFormPage.getTotalElements());
        response.setTotalPages(admissionFormPage.getTotalPages());
        response.setPage(admissionFormPage.getNumber());
        response.setSize(admissionFormPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
