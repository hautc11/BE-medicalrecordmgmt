package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.Medicine;
import com.example.medicalrecordsmgmt.domain.request.MedicineCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.MedicineUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponseAsPage;
import com.example.medicalrecordsmgmt.domain.response.MedicineResponse;
import com.example.medicalrecordsmgmt.domain.response.MedicineResponseAsPage;
import com.example.medicalrecordsmgmt.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineResponse getOne(int id) {
        return medicineRepository.findById(id)
                .map(medicine -> {
                    var response = new MedicineResponse();
                    response.setId(medicine.getId());
                    response.setName(medicine.getName());
                    response.setQuantities(medicine.getQuantities());
                    response.setExpirationDate(medicine.getExpirationDate());
                    return response;
                })
                .orElseGet(() -> new MedicineResponse());
    }

    public MedicineResponseAsPage getAll(int page, int size, String search) {
        var pageable = PageRequest.of(page, size);
        if (StringUtils.hasText(search)){
            var medicinePage = medicineRepository.searchMedicine(search,pageable);
            return MedicineResponseAsPage.of(medicinePage);
        }
        var medicinePage = medicineRepository.findAll(pageable);
        return MedicineResponseAsPage.of(medicinePage);
    }

    public void createMedicine(MedicineCreateRequest request) {
        var medicine = new Medicine();
        medicine.setName(request.getName());
        medicine.setQuantities(request.getQuantities());
        medicine.setExpirationDate(request.getExpirationDate());
        medicineRepository.save(medicine);
    }

    public void updateMedicine(MedicineUpdateRequest request) {
        medicineRepository.findById(request.getId())
                .ifPresent(medicine -> {
                    medicine.setName(request.getName());
                    medicine.setQuantities(request.getQuantities());
                    medicine.setExpirationDate(request.getExpirationDate());
                    medicineRepository.save(medicine);
                });
    }

    public void deleteMedicine(int id) {
        medicineRepository.findById(id)
                .ifPresent(medicine -> medicineRepository.delete(medicine));
    }
}
