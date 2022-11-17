package com.example.talaba.Controller;

import com.example.talaba.Dto.GuruhDTO;
import com.example.talaba.Entity.Fakultet;
import com.example.talaba.Entity.Fanlar;
import com.example.talaba.Entity.Guruh;
import com.example.talaba.Entity.Talaba;
import com.example.talaba.Repository.FakultetRepozitary;
import com.example.talaba.Repository.FanlarRepozitary;
import com.example.talaba.Repository.GuruhRepozitary;
import com.example.talaba.Repository.TalabaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GuruhController {
   @Autowired
    GuruhRepozitary guruhRepozitary;
   @Autowired
    FanlarRepozitary fanlarRepozitary;
   @Autowired
    FakultetRepozitary fakultetRepozitary;
   @RequestMapping(value = "/guruhjoylash",method = RequestMethod.POST)
    public String Guruhjoylash(@RequestBody GuruhDTO guruhDTO){
   boolean b=guruhRepozitary.existsByGuruhnomi(guruhDTO.getGuruhnomi());
   if(b) return "Guruh mavjud";
       Optional<Fakultet> openOption=fakultetRepozitary.findById(guruhDTO.getFakultetID());
       if(!openOption.isPresent()) return "Fakultet mavjud emas";
       Guruh guruh=new Guruh();
       guruh.setGuruhnomi(guruhDTO.getGuruhnomi());
       guruh.setFakultet(fakultetRepozitary.findById(guruhDTO.getFakultetID()).get());
       List<Fanlar> fanlarList=new ArrayList<>();
       for (Integer f : guruhDTO.getFanlarList()) {
           fanlarList.add(fanlarRepozitary.findById(f).get());
       }
       guruh.setFanlarList(fanlarList);
       guruhRepozitary.save(guruh);
       return   "Malumot joylandi";
   }
   @RequestMapping(value = "/olish",method = RequestMethod.GET)
    public List<Guruh>olish(){
       List<Guruh>olish=guruhRepozitary.findAll();
       return olish;
   }
   @RequestMapping(value = "/Taxrirlash/{id}",method = RequestMethod.PUT)
    public String taxrir(@PathVariable Integer id, @RequestBody GuruhDTO guruhDTO){
       Optional<Guruh>guruh=guruhRepozitary.findById(id);
       if(!guruh.isPresent()) return "Mavjudmas!!!!";
       Guruh guruh1= guruh.get();
       guruh1.setGuruhnomi(guruhDTO.getGuruhnomi());
       guruhRepozitary.save(guruh1);
       return "Guruh nomi taxrirlandi!!!";
   }
   @RequestMapping(value = "/Guruhdelete/{id}",method = RequestMethod.DELETE)
    public String Delete(@PathVariable Integer id){
       Optional<Guruh>guruh=guruhRepozitary.findById(id);
       if(!guruh.isPresent()) return "Bunday malumot mavjudmas";
       guruhRepozitary.deleteById(id);
       return "Malumotlar ochirildi";
   }
}
