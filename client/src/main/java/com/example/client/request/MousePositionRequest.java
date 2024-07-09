package com.example.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MousePositionRequest {

    private String x;
    private String y;
    private String dateTime;
}
