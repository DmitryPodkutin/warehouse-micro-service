package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.model.Label;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface LabelsRepository  extends  CommonRepository<Label>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Label lb where lb.id =:id")
    int delete(@Param("id") int id);

    @Query("SELECT lb FROM Label lb where lb.model =:model")
    Optional<Label> getByModel(@Param("model") String model);
}
