package com.example.talaba.Repository;

import com.example.talaba.Entity.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FakultetRepozitary extends JpaRepository<Fakultet, Integer> {
  boolean existsByFakulnomi(String fakulnomi);
  boolean existsByFakulnomiAndUniversitetId(String fakulnomi, Integer universitet_id);

}
