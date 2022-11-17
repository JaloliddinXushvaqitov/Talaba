package com.example.talaba.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Talaba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private String fam;
    @Column(nullable = false)
    private String telRaqam;

    @OneToOne
    Manzil manzil;
    @OneToOne
    Guruh guruh;
    @OneToOne
    Universitet universitet;
}
