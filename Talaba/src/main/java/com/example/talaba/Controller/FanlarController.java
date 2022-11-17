package com.example.talaba.Controller;

import com.example.talaba.Entity.Fanlar;
import com.example.talaba.Repository.FakultetRepozitary;
import com.example.talaba.Repository.FanlarRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FanlarController {
    @Autowired
    FanlarRepozitary fanlarRepozitary;
    @RequestMapping(value = "/fanjoylash",method = RequestMethod.POST)
    public String Joylash(@RequestBody Fanlar malumot){
        boolean b = fanlarRepozitary.existsByFannomi(malumot.getFannomi());
        if(b) return "bazada bunday fan mavjud";
        Fanlar fanlar=new Fanlar();
        fanlar.setFannomi(malumot.getFannomi());
        fanlarRepozitary.save(fanlar);
        return "Saqlandi";
    }
}
