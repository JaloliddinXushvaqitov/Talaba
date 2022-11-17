package com.example.talaba.Dto;

import com.example.talaba.Entity.Fakultet;
import com.example.talaba.Entity.Manzil;
import lombok.Data;

import java.util.List;

@Data

public class UniversitetDTO {
    private  String nomi;
    private  String viloyat, tuman,kocha;
    private List<Manzil> manzilList;
    private List<Fakultet> fakultetList;
}
