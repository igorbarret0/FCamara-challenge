package com.challenge.FCamara.service;

import com.challenge.FCamara.entities.SummaryVehicle;
import com.challenge.FCamara.repository.MonitoringRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MonitoringService {

    private MonitoringRepository monitoringRepository;

    public MonitoringService(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    private int currentEntries = 0;
    private int currentExits = 0;

    public void registerEntry() {
        currentEntries += 1;
    }
    public void registerExit() {
        currentExits += 1;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void saveInfo() {

        var summaryVehicle = new SummaryVehicle();
        summaryVehicle.setStartTime(LocalDateTime.now().minusHours(1));
        summaryVehicle.setEndTime(LocalDateTime.now());
        summaryVehicle.setEntries(currentEntries);
        summaryVehicle.setExits(currentExits);

        monitoringRepository.save(summaryVehicle);

        // Reset counters
        currentEntries = 0;
        currentExits = 0;
    }

}
