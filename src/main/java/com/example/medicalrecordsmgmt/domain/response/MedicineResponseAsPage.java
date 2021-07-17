package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.Medicine;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MedicineResponseAsPage {
    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<MedicineResponse> items;

    public static MedicineResponseAsPage of(Page<Medicine> medicinePage){
        var items = medicinePage.stream()
                .map(medicine -> {
                    var medicineResponse = new MedicineResponse();
                    medicineResponse.setId(medicine.getId());
                    medicineResponse.setName(medicine.getName());
                    medicineResponse.setQuantities(medicine.getQuantities());
                    medicineResponse.setExpirationDate(medicine.getExpirationDate());
                    return medicineResponse;
                })
                .collect(Collectors.toList());
        var response = new MedicineResponseAsPage();
        response.setTotalItems(medicinePage.getTotalElements());
        response.setTotalPages(medicinePage.getTotalPages());
        response.setPage(medicinePage.getNumber());
        response.setSize(medicinePage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
