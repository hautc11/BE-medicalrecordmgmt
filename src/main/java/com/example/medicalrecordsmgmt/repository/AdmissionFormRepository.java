package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.AdmissionForm;
import com.example.medicalrecordsmgmt.domain.entity.TestingForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionFormRepository extends JpaRepository<AdmissionForm, Integer> {
    @Query("select af " +
            "from AdmissionForm af left join MedicalRecord m " +
            "on af.medicalRecord.id=m.id " +
            "where m.fullName like %:keyword% or m.phoneNumber like %:keyword%")
    Page<AdmissionForm> searchAdmissionForm(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
