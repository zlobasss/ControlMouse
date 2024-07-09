package com.example.client.service;

import com.example.client.config.Config;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
