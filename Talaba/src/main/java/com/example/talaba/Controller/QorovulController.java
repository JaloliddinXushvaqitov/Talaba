package com.example.talaba.Controller;
import com.example.talaba.Dto.QorivulDTO;
import com.example.talaba.Entity.Manzil;
import com.example.talaba.Entity.Qoravul;
import com.example.talaba.Repository.ManzilRepository;
import com.example.talaba.Repository.QorovulRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qorovul")
public class QorovulController {
    @Autowired
    QorovulRepozitary qorovulRepozitary;
    @Autowired
    ManzilRepository manzilRepository;
    @PostMapping ("/qoshish")
    public String Qoshih(@RequestBody QorivulDTO qorivulDTO){
      Qoravul qoravul=new Qoravul();
      String ism= qorivulDTO.getQorovulism();
      if(!(ism.length()<=5)) return "Ism 5ta harfdan kop";
      qoravul.setQorovulism(qorivulDTO.getQorovulism());
      qoravul.setQorovulfam(qorivulDTO.getQorovulfam());
      qoravul.setTel(qorivulDTO.getTel());
        Manzil manzil=new Manzil();
        manzil.setViloyat(qorivulDTO.getViloyat());
        manzil.setTuman(qorivulDTO.getTuman());
        manzil.setKucha(qorivulDTO.getKucha());
        manzilRepository.save(manzil);
        qoravul.setManzil(manzil);
        qorovulRepozitary.save(qoravul);
        return "Malumot saqlandi if ishlamadi1";
    }
    @GetMapping("/olish")
    public List<Qoravul>olish(){
        List<Qoravul>all= qorovulRepozitary.findAll();
        return  all;
    }
    @GetMapping("/qorovulolish/{id}")
    public Qoravul olish(@PathVariable Integer id){
        boolean b = qorovulRepozitary.existsById(id);
      if(!b) return null;
      List<Qoravul>oqish= qorovulRepozitary.findAll();
       for(Qoravul d:oqish){
           if (d.getId().equals(id)){
              return d;
           }
        }
     return null;

    }
    @PutMapping("/Taxrirlash/{id}")
    public String taxrirlash(@PathVariable Integer id, @RequestBody QorivulDTO qorivulDTO){
        boolean b = qorovulRepozitary.existsById(id);
        boolean b1 = manzilRepository.existsById(id);
        if(!b) return "Bunday id malumot mavjud emas!!!";
        Optional<Qoravul>taxrir=qorovulRepozitary.findById(id);
        Qoravul qoravul= taxrir.get();
        qoravul.setQorovulism(qorivulDTO.getQorovulism());
        qoravul.setQorovulfam(qoravul.getQorovulfam());
        qoravul.setTel(qorivulDTO.getTel());
            Manzil manzil = qoravul.getManzil();
            manzil.setViloyat(qorivulDTO.getViloyat());
            manzil.setTuman(qorivulDTO.getTuman());
            manzil.setKucha(qorivulDTO.getKucha());
            manzilRepository.save(manzil);
            qoravul.setManzil(manzil);
            qorovulRepozitary.save(qoravul);
            return "malumot taxrirlandi";
        }
    //@RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
     @DeleteMapping("/Ochirish/{id}")
    public String ochirish(@PathVariable Integer id){
        Optional<Qoravul> delet = qorovulRepozitary.findById(id);
        if(delet.isPresent()) {
            Qoravul qoravul = delet.get();
            qorovulRepozitary.deleteById(id);
            manzilRepository.deleteById(qoravul.getManzil().getId());
            return "Bunday id malumot ochirildi";
        }
        return "Bunday id malumot mavjudmas!!!";
    }
}
