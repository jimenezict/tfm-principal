package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.TreeSet;

@Repository
interface StationSystemRawRepository extends JpaRepository<StationSystemRaw, Long> {

    TreeSet<StationSystemRaw> findAllBySystem(Integer id);

}