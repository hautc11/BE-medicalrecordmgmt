package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.Prescription;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PrescriptionResponseAsPage {
    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<PrescriptionResponse> items;

    public static PrescriptionResponseAsPage of(Page<Prescription> prescriptionPage){
        var items = prescriptionPage.stream()
                .map(prescription -> {
                    var medicine = new MedicineResponse();
                    medicine.setId(prescription.getMedicine().getId());
                    medicine.setName(prescription.getMedicine().getName());

                    var record = new RecordResponse();
                    record.setId(prescription.getCheckUpForm().getMedicalRecord().getId());
                    record.setFullName(prescription.getCheckUpForm().getMedicalRecord().getFullName());
                    record.setPhoneNumber(prescription.getCheckUpForm().getMedicalRecord().getPhoneNumber());

                    var checkupForm = new CheckUpFormResponse();
                    checkupForm.setId(prescription.getCheckUpForm().getId());
                    checkupForm.setRecordResponse(record);
                    checkupForm.setSymptom(prescription.getCheckUpForm().getSymptom());

                    var response = new PrescriptionResponse();
                    response.setId(prescription.getId());
                    response.setQuantities(prescription.getQuantities());
                    response.setMedicine(medicine);
                    response.setCheckupForm(checkupForm);
                    return response;
                }).collect(Collectors.toList());
        var response = new PrescriptionResponseAsPage();
        response.setTotalItems(prescriptionPage.getTotalElements());
        response.setTotalPages(prescriptionPage.getTotalPages());
        response.setPage(prescriptionPage.getNumber());
        response.setSize(prescriptionPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
