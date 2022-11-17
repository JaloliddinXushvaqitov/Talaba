package com.example.talaba.Repository;

import com.example.talaba.Entity.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManzilRepository extends JpaRepository<Manzil,Integer> {
  List<Manzil> findByUniversitetId( Integer universitet_id);
}
