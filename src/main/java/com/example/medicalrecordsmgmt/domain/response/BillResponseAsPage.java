package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.MedicalBill;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BillResponseAsPage {
    private long totalPages;
    private long totalItems;
    private int page;
    private int size;
    private List<BillResponse> items;

    public static BillResponseAsPage of(Page<MedicalBill> medicalBillPage){
        var items = medicalBillPage.stream()
                .map(medicalBill -> {
                    var medicalResponse = new RecordResponse();
                    medicalResponse.setId(medicalBill.getMedicalRecord().getId());
                    medicalResponse.setFullName(medicalBill.getMedicalRecord().getFullName());
                    medicalResponse.setPhoneNumber(medicalBill.getMedicalRecord().getPhoneNumber());

                    var response = new BillResponse();
                    response.setId(medicalBill.getId());
                    response.setService(medicalBill.getService());
                    response.setPrice(medicalBill.getPrice());
                    response.setDiscount(medicalBill.getDiscount());
                    response.setTotal(medicalBill.getTotal());
                    response.setCreateAt(medicalBill.getCreatedAt());
                    response.setRecordResponse(medicalResponse);
                    return response;
                })
                .collect(Collectors.toList());

        var response = new BillResponseAsPage();
        response.setTotalPages(medicalBillPage.getTotalPages());
        response.setTotalItems(medicalBillPage.getTotalElements());
        response.setPage(medicalBillPage.getNumber());
        response.setSize(medicalBillPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
