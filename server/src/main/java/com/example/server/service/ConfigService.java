package com.example.server.service;

import com.example.server.config.Config;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private Config config = new Config();

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
