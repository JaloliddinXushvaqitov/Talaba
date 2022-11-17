package com.example.talaba.Repository;

import com.example.talaba.Entity.Qoravul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QorovulRepozitary extends JpaRepository<Qoravul, Integer> {
     boolean existsById(Integer id);
}
