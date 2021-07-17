package com.example.medicalrecordsmgmt.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "prescription")
@Getter
@Setter
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "medicineId")
    private Medicine medicine;

    private int quantities;

    @ManyToOne
    @JoinColumn(name = "checkupId")
    private CheckUpForm checkUpForm;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW()", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW()", updatable = false, insertable = false)
    private Timestamp updatedAt;
}
