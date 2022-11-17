package com.example.talaba.Controller;

import com.example.talaba.Dto.TalabaDto;
import com.example.talaba.Entity.*;
import com.example.talaba.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TalabaController {
    @Autowired
    TalabaRepository talabaRepository;
    @Autowired
    ManzilRepository manzilRepository;
    @Autowired
    GuruhRepozitary guruhRepozitary;
    @Autowired
    FakultetRepozitary fakultetRepozitary;
    @Autowired
    UniversitetRespositary universitetRespositary;
    @RequestMapping(value = "/o'qish", method = RequestMethod.GET)
    public List<Talaba> Uqimoq(){
        List<Talaba> all = talabaRepository.findAll();
        return all;
    }

    @RequestMapping(value = "/yozmoq1",method = RequestMethod.POST)
    public String Yozmoq(@RequestBody TalabaDto talabaDto){
        boolean b = talabaRepository.existsByTelRaqam(talabaDto.getTelRaqam());
        if (b) return "Bunday telefon raqam mavjud. Iltimos boshqa raqamdan ro'yhatdan o'ting!";
        Talaba talaba1 = new Talaba();
        talaba1.setIsm(talabaDto.getIsm());
        talaba1.setFam(talabaDto.getFam());
        talaba1.setTelRaqam(talabaDto.getTelRaqam());
        Manzil manzil = new Manzil();
        manzil.setViloyat(talabaDto.getViloyat());
        manzil.setTuman(talabaDto.getTuman());
        manzil.setKucha(talabaDto.getKucha());
        Optional<Universitet> byId2 = universitetRespositary.findById(talabaDto.getUniversitet());
        if(!byId2.isPresent()) return "BUnday universtet malumoti topilmadi!!";
        talaba1.setUniversitet(universitetRespositary.findById(talabaDto.getUniversitet()).get());
        Optional<Guruh> byId1 =guruhRepozitary .findById(talabaDto.getGuruhid());
        if(!byId1.isPresent()) return "Bunday guruh mavjud emas!!";
        talaba1.setGuruh(guruhRepozitary.findById(talabaDto.getGuruhid()).get());
        manzilRepository.save(manzil);
        talaba1.setManzil(manzil);
        talabaRepository.save(talaba1);
        return "Malumotlar saqlandi";
    }

    @RequestMapping(value = "/Talabao'qish/{id}",method = RequestMethod.GET)
    public Talaba MalumotOlish(@PathVariable Integer id){
        boolean b = talabaRepository.existsById(id);
        if (!b) return null;
        List<Talaba> list = talabaRepository.findAll();
        for (Talaba d:list) {
            if (d.getId().equals(id)){
                return d;
            }
        }
        return null;
    }

    @RequestMapping(value = "Tahrilash/{id}",method = RequestMethod.PUT)
    public String Tahrirlash(@PathVariable Integer id, @RequestBody TalabaDto talaba){
        Optional<Talaba> optionalTalaba = talabaRepository.findById(id);
        if (!optionalTalaba.isPresent()) return "Bunday IDli ma'lumotlar mavjud emas";
        Talaba talaba1 = optionalTalaba.get();
        talaba1.setIsm(talaba.getIsm());
        talaba1.setFam(talaba.getFam());
        talaba1.setTelRaqam(talaba.getTelRaqam());
        Optional<Manzil> optionalManzil = manzilRepository.findById(id);
        Manzil manzil = optionalManzil.get();
        manzil.setViloyat(talaba.getViloyat());
        manzil.setTuman(talaba.getTuman());
        manzil.setKucha(talaba.getKucha());
        manzilRepository.save(manzil);
        talaba1.setManzil(manzil);
        talabaRepository.save(talaba1);
        return "Ma'lumot tahrirlandi";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public String Delete(@PathVariable Integer id){
        Optional<Talaba> optional = talabaRepository.findById(id);
        if (!optional.isPresent()) return "Bunday IDli ma'lumot mavjud emas";
        talabaRepository.deleteById(id);
        manzilRepository.deleteById(id);
        return "Muvaffaqiyatli o'chirildi";
    }
}
