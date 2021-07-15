package com.example.medicalrecordsmgmt.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "medicalbill")
@Getter
@Setter
@NoArgsConstructor
public class MedicalBill {
    @Id
    private int id;

    private double total;

    private String service;
    @ManyToOne
    @JoinColumn(name = "recordId")
    private MedicalRecord medicalRecord;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW()", updatable = false, insertable = false)
    private Timestamp createdAt;
    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW()", updatable = false, insertable = false)
    private Timestamp updatedAt;
}
