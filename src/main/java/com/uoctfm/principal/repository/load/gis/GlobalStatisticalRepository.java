package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Optional;

@Repository
interface GlobalStatisticalRepository extends JpaRepository<GlobalStatistical, Long> {

    Optional<GlobalStatistical> findById(Long id);

    Optional<GlobalStatistical> findBySystem(Integer system);

    List findAllBySystem(Integer id);

}