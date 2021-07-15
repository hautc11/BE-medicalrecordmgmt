package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.CheckUpForm;
import com.example.medicalrecordsmgmt.domain.entity.TestingForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestingFormRepository extends JpaRepository<TestingForm, Integer> {

    @Query("select tf " +
            "from TestingForm tf left join MedicalRecord m " +
            "on tf.medicalRecord.id=m.id " +
            "where m.fullName like %:keyword% or m.phoneNumber like %:keyword%")
    Page<TestingForm> searchTestingForm(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
