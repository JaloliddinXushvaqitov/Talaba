package com.example.talaba.Entity;

import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "qorovul123")
public class Qoravul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false, name = "ID")
    private Integer id;
    @Column(nullable = false, name = "ism")
    private String qorovulism;
    @Column(nullable = false)
    private String qorovulfam;
    @Column(nullable = false)
    private String tel;

    @OneToOne
    private  Manzil manzil;

}
