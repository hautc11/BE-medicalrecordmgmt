package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.Prescription;
import com.example.medicalrecordsmgmt.domain.request.PrescriptionCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.PrescriptionUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.*;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.CheckUpFormRepository;
import com.example.medicalrecordsmgmt.repository.MedicineRepository;
import com.example.medicalrecordsmgmt.repository.PrescriptionRepository;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final CheckUpFormRepository checkUpFormRepository;
    private final MedicineRepository medicineRepository;

    public PrescriptionResponse getOne(int id) {
        return prescriptionRepository.findById(id)
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
                    checkupForm.setSymptom(prescription.getCheckUpForm().getSymptom());
                    checkupForm.setRecordResponse(record);

                    var response = new PrescriptionResponse();
                    response.setId(prescription.getId());
                    response.setQuantities(prescription.getQuantities());
                    response.setMedicine(medicine);
                    response.setCheckupForm(checkupForm);
                   return response;
                }).orElseGet(() -> new PrescriptionResponse());
    }

    public PrescriptionResponseAsPage getAll(int page, int size, int search) {
        var pageable = PageRequest.of(page,size);
        if (search!=0){
            var prescriptionPage = prescriptionRepository.searchPrescription(search,pageable);
            return PrescriptionResponseAsPage.of(prescriptionPage);
        }
        var prescriptionPage = prescriptionRepository.findAll(pageable);
        return PrescriptionResponseAsPage.of(prescriptionPage);
    }

    public void createPrescription(PrescriptionCreateRequest request) {
        var checkup = checkUpFormRepository.findById(request.getCheckupId())
                .orElseThrow(()->new BadRequestException(ErrorCode.INVALID_CHECKUP_ID));
        var medicine = medicineRepository.findById(request.getMedicineId())
                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_MEDICINE_ID));
        var prescription = new Prescription();
        prescription.setMedicine(medicine);
        prescription.setCheckUpForm(checkup);
        prescription.setQuantities(request.getQuantities());
        prescriptionRepository.save(prescription);
    }

    public void updatePrescription(PrescriptionUpdateRequest request) {
        prescriptionRepository.findById(request.getId())
                .ifPresentOrElse(prescription -> {
                    prescription.setQuantities(request.getQuantities());

                    if (prescription.getCheckUpForm().getId() != request.getCheckupId()){
                        var checkup = checkUpFormRepository.findById(request.getCheckupId())
                                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_CHECKUP_ID));
                        prescription.setCheckUpForm(checkup);
                    }

                    if (prescription.getMedicine().getId() != request.getMedicineId()){
                        var medicine = medicineRepository.findById(request.getMedicineId())
                                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_MEDICINE_ID));
                        prescription.setMedicine(medicine);
                    }
                    prescriptionRepository.save(prescription);
                },() -> {throw new BadRequestException(ErrorCode.INVALID_DOCTOR_ID);}
        );
    }

    public void deletePrescription(int id) {
        prescriptionRepository.findById(id)
                .ifPresent(prescription -> prescriptionRepository.delete(prescription));
    }
}
