package com.example.talaba.Controller;

import com.example.talaba.Dto.FakultetDTO;
import com.example.talaba.Entity.Fakultet;
import com.example.talaba.Entity.Manzil;
import com.example.talaba.Entity.Universitet;
import com.example.talaba.Repository.FakultetRepozitary;
import com.example.talaba.Repository.UniversitetRespositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FakultetController {
    @Autowired
    FakultetRepozitary fakultetRepozitary;
   @Autowired
    UniversitetRespositary universitetRespositary;

    @RequestMapping (value = "/FakultetJoylash",method = RequestMethod.POST)
    public  String Joylash(@RequestBody FakultetDTO fakultetDTO){
        Optional<Universitet> joylash=universitetRespositary.findById(fakultetDTO.getUniversitetID());
        if(!joylash.isPresent()) return "universitet yoq";
        boolean b = fakultetRepozitary.existsByFakulnomiAndUniversitetId(fakultetDTO.getFakulNomi(), fakultetDTO.getUniversitetID());
        if (b) return "bor fakultet";
        Fakultet fakultet = new Fakultet();
     fakultet.setFakulnomi(fakultetDTO.getFakulNomi());
   fakultet.setUniversitet(universitetRespositary.findById(fakultetDTO.getUniversitetID()).get());
     fakultetRepozitary.save(fakultet);
        return "Malumotlar saqlandi";
    }
    @RequestMapping(value = "/faktaxrirlashF/{id}",method = RequestMethod.PUT)
    public  String taxrirlash(@PathVariable Integer id, @RequestBody FakultetDTO fakultetDTO){
        Optional<Fakultet>taxrir=fakultetRepozitary.findById(id);
        if(!taxrir.isPresent()){
            return  "ID mavjud emas";
        }
        Fakultet fakultet= taxrir.get();
        fakultet.setFakulnomi(fakultetDTO.getFakulNomi());
        fakultetRepozitary.save(fakultet);
    return  "Saqlandi";
    }
    @RequestMapping(value = "/fakultetochirish/{id}",method = RequestMethod.DELETE)
    public String ochirish(@PathVariable Integer id){
        Optional<Fakultet> byId = fakultetRepozitary.findById(id);
        if(byId.isPresent()){
            fakultetRepozitary.deleteById(id);
        }
        return "Malumot ochirildi";
    }
    @RequestMapping(value = "/fakultetmalulotolish/{id}",method = RequestMethod.GET)
    public Fakultet fakultetmalumot(@PathVariable Integer id){
        Optional<Fakultet> byId = fakultetRepozitary.findById(id);
        if(byId.isPresent()){
           return byId.get();
        }
        return new Fakultet();
    }
}
