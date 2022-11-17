package com.example.talaba.Controller;

import com.example.talaba.Dto.TalabaDto;
import com.example.talaba.Dto.UniversitetDTO;
import com.example.talaba.Entity.Fakultet;
import com.example.talaba.Entity.Manzil;
import com.example.talaba.Entity.Talaba;
import com.example.talaba.Entity.Universitet;
import com.example.talaba.Repository.FakultetRepozitary;
import com.example.talaba.Repository.ManzilRepository;
import com.example.talaba.Repository.UniversitetRespositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UniversitetController {
    @Autowired
    UniversitetRespositary universitetRespositary;
    @Autowired
    ManzilRepository manzilRepository;
    @Autowired
    FakultetRepozitary fakultetRepozitary;
    @RequestMapping(value = "/o'qish12", method = RequestMethod.GET)
    public List<Universitet> Uqimoq(){
        List<Universitet> all=universitetRespositary.findAll();
        return all;
    }
    @RequestMapping(value = "/Oqish/{id}",method = RequestMethod.GET)
    public Universitet oqishId(@PathVariable Integer id){
        Optional<Universitet> byId = universitetRespositary.findById(id);
        if(byId.isPresent()){
         byId.get();
        }
        return new Universitet();
    }
    @RequestMapping(value = "/malumot",method = RequestMethod.POST)
    public String Yozmoq(@RequestBody UniversitetDTO universitetDTO){
        Universitet universitet = new Universitet();
        universitet.setNomi(universitetDTO.getNomi());
        List<Manzil> manzils=new ArrayList<>();
        for (Manzil manzil : universitetDTO.getManzilList()) {
            Manzil manzil1 = new Manzil();
            manzil1.setViloyat(manzil.getViloyat());
            manzil1.setTuman(manzil.getTuman());
            manzil1.setKucha(manzil.getKucha());
            manzil1.setUniversitet(universitet);
            manzils.add(manzil1);
        }
        universitet.setManzilList(manzils);
        universitetRespositary.save(universitet);
        return "Malumotlar saqlandi";
    }

    @RequestMapping(value = "/taxrirlash/{id}", method = RequestMethod.PUT)
    public String taxrirlash(@PathVariable Integer id,@RequestBody UniversitetDTO universitetDTO){
        Optional<Universitet>taxrir=universitetRespositary.findById(id);
        if(!taxrir.isPresent()){
            return "Mavjudmas";
        }
        Universitet universitet= taxrir.get();
        universitet.setNomi(universitetDTO.getNomi());
        List<Manzil> manzilList=new ArrayList<>();
       List<Manzil> manzils1=manzilRepository.findByUniversitetId(id);
        int i=0;
        for (Manzil manzil : universitetDTO.getManzilList()) {
            Manzil manzil1 =manzils1.get(i);
            manzil1.setViloyat(manzil.getViloyat());
            manzil1.setTuman(manzil.getTuman());
            manzil1.setKucha(manzil.getKucha());
            manzil1.setUniversitet(universitet);
            manzilList.add(manzil1);
            i++;
        }
        universitet.setManzilList(manzilList);
        universitetRespositary.save(universitet);
        return "Ma'lumot tahrirlandi";
    }
    @RequestMapping(value = "/ochirish/{id}",method = RequestMethod.DELETE)
    public  String ochirish(@PathVariable Integer id){
        Optional<Universitet>delete=universitetRespositary.findById(id);
        if(!delete.isPresent()) return "Bunday id malumot mavjud emas!!!!";
        universitetRespositary.deleteById(id);
        manzilRepository.deleteById(id);
        return "Malumotlar o`chirildi!!!!";
    }

}
