package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.configuration.LastSampleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LastSampleRepository extends JpaRepository<LastSampleDTO, Long> {

    LastSampleDTO findById(Integer id);

}