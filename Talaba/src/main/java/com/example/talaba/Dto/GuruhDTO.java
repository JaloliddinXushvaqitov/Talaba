package com.example.talaba.Dto;

import com.example.talaba.Entity.Fanlar;
import com.example.talaba.Entity.Talaba;
import lombok.Data;

import java.util.List;

@Data
public class GuruhDTO {
private String guruhnomi;
private Integer fakultetID;
private List<Integer>fanlarList;
//private List<Integer>talabaList;

}
