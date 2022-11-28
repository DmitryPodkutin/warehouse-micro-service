package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.model.HydraulicValve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface HydraulicValvesRepository extends  CommonRepository<HydraulicValve> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM HydraulicValve hv where hv.id =:id")
    int delete(@Param("id") Integer id);
}
