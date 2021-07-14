package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<MedicalRecord, Integer> {

    Page<MedicalRecord> findByFullNameContaining(String fullName, Pageable pageable);

    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.fullName LIKE %:keyword% OR mr.phoneNumber LIKE %:keyword%")
    Page<MedicalRecord> searchMedicalRecord(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
