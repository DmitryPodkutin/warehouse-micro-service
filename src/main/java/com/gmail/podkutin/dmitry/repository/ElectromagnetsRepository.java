package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ElectromagnetsRepository extends CommonRepository<Electromagnet> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Electromagnet e where e.id =:id")
    int delete(@Param("id") Integer id);

    @Query("SELECT e FROM Electromagnet e where e.electromagnetModel.model =:model")
    Optional<List<Electromagnet>> getByModel(@Param("model") String model);
}
