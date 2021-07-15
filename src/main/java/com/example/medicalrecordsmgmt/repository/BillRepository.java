package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.MedicalBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<MedicalBill, Integer> {


    @Query("select m " +
            "from MedicalBill m left join MedicalRecord m2 " +
            "on m.medicalRecord.id = m2.id " +
            "where m2.fullName like %:keyword% or m2.phoneNumber like %:keyword%")
    Page<MedicalBill> searchBill(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
