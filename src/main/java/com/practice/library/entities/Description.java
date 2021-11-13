package com.practice.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Descriptions")
@Table(name = "Descriptions")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreatedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate creationDate;

    @Column(columnDefinition = "DATE")
    private LocalDate  deactivationDate;

    @LastModifiedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate lastModificationDate;
}