package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.CheckUpForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckUpFormRepository extends JpaRepository<CheckUpForm, Integer> {

    @Query("select c " +
            "from CheckUpForm c left join MedicalRecord m " +
            "on c.medicalRecord.id=m.id " +
            "where m.fullName like %:keyword% or m.phoneNumber like %:keyword%")
    Page<CheckUpForm> searchCheckUpForm(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
