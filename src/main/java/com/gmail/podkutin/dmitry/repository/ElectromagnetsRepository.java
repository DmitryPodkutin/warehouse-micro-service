package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ElectromagnetsRepository extends CommonRepository<Electromagnet> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Electromagnet e where e.id =:id")
    int delete(@Param("id") Integer id);
}
