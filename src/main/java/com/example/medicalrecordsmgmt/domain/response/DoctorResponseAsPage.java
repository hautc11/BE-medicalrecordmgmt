package com.example.medicalrecordsmgmt.domain.response;

import com.example.medicalrecordsmgmt.domain.entity.Doctor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DoctorResponseAsPage {
    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<DoctorResponse> items;

    public static DoctorResponseAsPage of(Page<Doctor> doctorPage){
        var items = doctorPage.stream()
                .map(doctor -> {
                    var departmentResponse = new DepartmentResponse();
                    departmentResponse.setId(doctor.getDepartment().getId());
                    departmentResponse.setName(doctor.getDepartment().getDepartmentName());

                    var response = new DoctorResponse();
                    response.setId(doctor.getId());
                    response.setFullName(doctor.getFullName());
                    response.setPhoneNumber(doctor.getPhoneNumber());
                    response.setDepartment(departmentResponse);
                    return response;
                })
                .collect(Collectors.toList());

        var response = new DoctorResponseAsPage();
        response.setTotalItems(doctorPage.getTotalElements());
        response.setTotalPages(doctorPage.getTotalPages());
        response.setPage(doctorPage.getNumber());
        response.setSize(doctorPage.getNumberOfElements());
        response.setItems(items);
        return response;
    }
}
