package com.example.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "mouse_position")
@Entity(name = "mouse_position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MousePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;
    private String dateTime;

    @Override
    public String toString() {
        return "Mouse position { " +
                "x = " + x +
                ", y = " + y +
                ", dateTime = " + dateTime +
                " }";
    }
}
