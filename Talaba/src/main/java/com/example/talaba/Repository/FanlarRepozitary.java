package com.example.talaba.Repository;

import com.example.talaba.Entity.Fanlar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanlarRepozitary  extends JpaRepository<Fanlar, Integer> {
    boolean existsByFannomi(String fannomi);
}
