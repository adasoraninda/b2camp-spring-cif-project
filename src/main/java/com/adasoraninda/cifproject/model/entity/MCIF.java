package com.adasoraninda.cifproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_cif")
public class MCIF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "id_ktp", length = 16, nullable = false, unique = true)
    private String idKtp;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String npwp;

    @Column(name = "no_telephone", length = 15, nullable = false, unique = true)
    private String noTelepon;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String type;

}
