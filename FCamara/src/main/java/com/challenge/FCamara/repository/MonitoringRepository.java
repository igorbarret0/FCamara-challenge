package com.challenge.FCamara.repository;

import com.challenge.FCamara.entities.SummaryVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringRepository extends JpaRepository<SummaryVehicle, Long> {
}
