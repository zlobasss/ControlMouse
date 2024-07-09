package com.example.server.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor
@Configuration
public class Config {

    private int port;

    public Config() {
        port = 8072;
    }
}