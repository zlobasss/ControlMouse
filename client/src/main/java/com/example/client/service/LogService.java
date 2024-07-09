package com.example.client.service;

import com.example.client.config.LogConfig;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private LogConfig logConfig;

    public void setLogConfig(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    public LogConfig getLogConfig() {
        return logConfig;
    }
}
