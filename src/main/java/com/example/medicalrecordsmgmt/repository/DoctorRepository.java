package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.Doctor;
import com.example.medicalrecordsmgmt.domain.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("SELECT d FROM Doctor d WHERE d.fullName LIKE %:keyword% OR d.phoneNumber LIKE %:keyword%")
    Page<Doctor> searchDoctor(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
