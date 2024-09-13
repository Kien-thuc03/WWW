package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.services;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Log;
import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.repositories.LogRepository;

import java.time.Instant;

public class LogServices {

    public Long logLogin(String accountId) {
        LogRepository logRepository = new LogRepository();
        return logRepository.logLogin(accountId);
    }
    public void logLogout(Long logId) {
        LogRepository logRepository = new LogRepository();
        logRepository.logLogout(logId);
    }
}
