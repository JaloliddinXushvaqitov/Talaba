package com.example.talaba.Repository;

import com.example.talaba.Entity.Guruh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuruhRepozitary extends  JpaRepository<Guruh, Integer>{
    boolean existsByGuruhnomi(String guruhnomi);

}
