package com.example.medicalrecordsmgmt.repository;

import com.example.medicalrecordsmgmt.domain.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query("select p from Prescription p left join CheckUpForm  c on p.checkUpForm.id = c.id where c.id = ?1 ")
    Page<Prescription> searchPrescription(
            @Param("keyword") int keyword,
            Pageable pageable
    );
}
