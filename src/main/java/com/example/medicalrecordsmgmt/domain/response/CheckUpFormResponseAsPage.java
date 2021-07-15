package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.CheckUpForm;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CheckUpFormResponseAsPage {
    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<CheckUpFormResponse> items;

    public static CheckUpFormResponseAsPage of(Page<CheckUpForm> checkUpFormPage){
        var items = checkUpFormPage.stream()
                .map(checkUpForm -> {
                    var medicalRecord = new RecordResponse();
                    medicalRecord.setFullName(checkUpForm.getMedicalRecord().getFullName());
                    medicalRecord.setId(checkUpForm.getMedicalRecord().getId());
                    medicalRecord.setPhoneNumber(checkUpForm.getMedicalRecord().getPhoneNumber());

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
                .collect(Collectors.toList());
        var response = new CheckUpFormResponseAsPage();
        response.setTotalItems(checkUpFormPage.getTotalElements());
        response.setTotalPages(checkUpFormPage.getTotalPages());
        response.setPage(checkUpFormPage.getNumber());
        response.setSize(checkUpFormPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
