package com.example.medicalrecordsmgmt.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "medicine")
@Getter
@Setter
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int quantities;

    private Date expirationDate;


    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW()", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW()", updatable = false, insertable = false)
    private Timestamp updatedAt;
}
