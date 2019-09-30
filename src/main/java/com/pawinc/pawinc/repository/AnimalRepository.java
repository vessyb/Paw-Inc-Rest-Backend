package com.pawinc.pawinc.repository;

import com.pawinc.pawinc.ui.model.request.AnimalRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalRequestModel, Long> {
}
