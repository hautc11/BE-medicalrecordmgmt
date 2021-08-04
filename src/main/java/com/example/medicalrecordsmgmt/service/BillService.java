package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.MedicalBill;
import com.example.medicalrecordsmgmt.domain.request.BillCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.BillUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.BillResponse;
import com.example.medicalrecordsmgmt.domain.response.BillResponseAsPage;
import com.example.medicalrecordsmgmt.domain.response.RecordResponse;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.BillRepository;
import com.example.medicalrecordsmgmt.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final RecordRepository recordRepository;

    public BillResponse getOne(int id) {
        return billRepository.findById(id)
                .map(medicalBill -> {
                    var medicalRecord = new RecordResponse();
                    medicalRecord.setId(medicalBill.getMedicalRecord().getId());
                    medicalRecord.setFullName(medicalBill.getMedicalRecord().getFullName());
                    medicalRecord.setPhoneNumber(medicalBill.getMedicalRecord().getPhoneNumber());

                    var response = new BillResponse();
                    response.setId(medicalBill.getId());
                    response.setTotal(medicalBill.getTotal());
                    response.setCreateAt(medicalBill.getCreatedAt());
                    response.setService(medicalBill.getService());
                    response.setPrice(medicalBill.getPrice());
                    response.setDiscount(medicalBill.getDiscount());
                    response.setTotal(medicalBill.getTotal());
                    response.setRecordResponse(medicalRecord);
                    return response;
                }).orElseGet(() -> new BillResponse());
    }

    public BillResponseAsPage getAll(int page, int size,String search) {
        var pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"createdAt"));
        if (StringUtils.hasText(search)){
            var medicalBill = billRepository.searchBill(search,pageable);
            return BillResponseAsPage.of(medicalBill);
        }
        var medicalBill = billRepository.findAll(pageable);
        return BillResponseAsPage.of(medicalBill);
    }


    public void deleteMedicalBill(int id) {
        billRepository.findById(id)
                .ifPresent(medicalBill -> billRepository.delete(medicalBill));
    }

    public void createMedicalBill(BillCreateRequest request) {
        var medicalrecord = recordRepository.findById((request.getRecordId()))
                .orElseThrow(()-> new BadRequestException(ErrorCode.INVALID_MEDICAL_RECORD_ID));
        var medicalBill = new MedicalBill();
        medicalBill.setService(request.getService());
        medicalBill.setMedicalRecord(medicalrecord);
        medicalBill.setPrice(request.getPrice());
        if (StringUtils.hasText(medicalrecord.getInsuaranceCode())){
            medicalBill.setDiscount(medicalBill.getPrice()*0.8);
            medicalBill.setTotal(medicalBill.getPrice()-(medicalBill.getPrice() * 0.8));
        }else{
            medicalBill.setDiscount(0);
            medicalBill.setTotal(medicalBill.getPrice());
        }
        billRepository.save(medicalBill);
    }

    public void updateMedicalBill(BillUpdateRequest request) {
        billRepository.findById(request.getId())
                .ifPresentOrElse(medicalBill -> {
                    medicalBill.setPrice(request.getPrice());
                    medicalBill.setService(request.getService());
                    if(medicalBill.getMedicalRecord().getId()!= request.getRecordId()){
                        var medicalRecord = recordRepository.findById(request.getRecordId())
                                .orElseThrow((() -> new BadRequestException(ErrorCode.INVALID_MEDICAL_RECORD_ID)));
                        medicalBill.setMedicalRecord(medicalRecord);
                    }
                    if(StringUtils.hasText(medicalBill.getMedicalRecord().getInsuaranceCode())){
                        medicalBill.setDiscount(medicalBill.getPrice()*0.8);
                        medicalBill.setTotal(medicalBill.getPrice()-(medicalBill.getPrice() * 0.8));
                    }else{
                        medicalBill.setDiscount(0);
                        medicalBill.setTotal(medicalBill.getPrice());
                    }
                    billRepository.save(medicalBill);
                }, () -> {throw new BadRequestException(ErrorCode.INVALID_ID);});
    }
}
