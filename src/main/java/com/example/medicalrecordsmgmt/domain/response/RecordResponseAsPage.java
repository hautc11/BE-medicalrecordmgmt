package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.MedicalRecord;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecordResponseAsPage {
    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<RecordResponse> items;

    public static RecordResponseAsPage of(Page<MedicalRecord> medicalRecordPage){
        var items = medicalRecordPage.stream()
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
                }).collect(Collectors.toList());

        var response = new RecordResponseAsPage();
        response.setTotalItems(medicalRecordPage.getTotalElements());
        response.setTotalPages(medicalRecordPage.getTotalPages());
        response.setPage(medicalRecordPage.getNumber());
        response.setSize(medicalRecordPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
