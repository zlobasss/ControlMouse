package com.example.client.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name = "mouse_position")
@Entity(name = "mouse_position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MousePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;
    private Timestamp dateTime;

    @Override
    public String toString() {
        return "Mouse position { " +
                "x = " + x +
                ", y = " + y +
                ", dateTime = " + dateTime +
                " }";
    }
}
