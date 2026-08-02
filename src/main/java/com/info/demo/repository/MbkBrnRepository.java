package com.info.demo.repository;

import com.info.demo.entity.MbkBrn;
import com.info.demo.entity.MbkBrnKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbkBrnRepository extends JpaRepository<MbkBrn, MbkBrnKey> {
}
