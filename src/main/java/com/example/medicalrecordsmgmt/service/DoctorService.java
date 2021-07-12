package com.example.medicalrecordsmgmt.service;

import com.example.medicalrecordsmgmt.domain.entity.Doctor;
import com.example.medicalrecordsmgmt.domain.request.DoctorCreateRequest;
import com.example.medicalrecordsmgmt.domain.request.DoctorUpdateRequest;
import com.example.medicalrecordsmgmt.domain.response.DepartmentResponse;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponse;
import com.example.medicalrecordsmgmt.domain.response.DoctorResponseAsPage;
import com.example.medicalrecordsmgmt.exception.BadRequestException;
import com.example.medicalrecordsmgmt.exception.ErrorCode;
import com.example.medicalrecordsmgmt.repository.DepartmentRepository;
import com.example.medicalrecordsmgmt.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public DoctorResponse getOne(int id) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    var department  = new DepartmentResponse();
                    department.setId(doctor.getDepartment().getId());
                    department.setName(doctor.getDepartment().getDepartmentName());

                    var response = new DoctorResponse();
                    response.setId(doctor.getId());
                    response.setFullName(doctor.getFullName());
                    response.setPhoneNumber(doctor.getPhoneNumber());
                    response.setDepartment(department);
                    return response;
                }).orElseGet(() -> new DoctorResponse());
    }

    public DoctorResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page,size);
        var doctorPage = doctorRepository.findAll(pageable);
        return DoctorResponseAsPage.of(doctorPage);
    }

    public void createDoctor(DoctorCreateRequest request) {
        var department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_DEPARTMENT_ID));

        var doctor = new Doctor();
        doctor.setFullName(request.getFullName());
        doctor.setPhoneNumber(request.getPhoneNumber());
        doctor.setDepartment(department);
        doctorRepository.save(doctor);
    }

    public void updateDoctor(DoctorUpdateRequest request) {
        doctorRepository.findById(request.getId())
                .ifPresentOrElse(doctor -> {
                    doctor.setFullName(request.getFullName());
                    doctor.setPhoneNumber(request.getPhoneNumber());

                    if (doctor.getDepartment().getId() != request.getDepartmentId()){
                        var department = departmentRepository.findById(request.getDepartmentId())
                                .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_DEPARTMENT_ID));

                        doctor.setDepartment(department);
                    }
                    doctorRepository.save(doctor);
                },() -> {throw new BadRequestException(ErrorCode.INVALID_DOCTOR_ID);}
                );
    }

    public void deleteDoctor(int id) {
        doctorRepository.findById(id)
                .ifPresent(doctor -> doctorRepository.delete(doctor));
    }
}
