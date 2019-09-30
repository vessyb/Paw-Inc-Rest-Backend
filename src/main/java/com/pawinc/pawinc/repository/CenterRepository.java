package com.pawinc.pawinc.repository;

import com.pawinc.pawinc.ui.model.request.CenterRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<CenterRequestModel, Long> {
}
