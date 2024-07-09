package com.example.client.service;

import com.example.client.entity.MousePosition;
import com.example.client.repository.MousePositionRepository;
import com.example.client.request.MousePositionRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class MouseService {

    private final MousePositionRepository repository;

    public MouseService(MousePositionRepository repository) {
        this.repository = repository;
    }

    public int save(MousePositionRequest dto) {

        if (dto == null) {
            System.out.println("Данные пусты");
            return -10;
        }
        String[] date = dto.getDateTime().split(" ");
        if (date.length != 7) {
            System.out.println("Количество чисел для даты и времени не совпадает");
            return -11;
        }
        int x, y, year, month, dayOfMonth, hour, minute, second, nanoOfSeconds;
        try {
            x = Integer.parseInt(dto.getX());
            y = Integer.parseInt(dto.getY());
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            dayOfMonth = Integer.parseInt(date[2]);
            hour = Integer.parseInt(date[3]);
            minute = Integer.parseInt(date[4]);
            second = Integer.parseInt(date[5]);
            nanoOfSeconds = Integer.parseInt(date[6]);
        } catch (NumberFormatException e) {
            System.out.println("В координатах и дате со временем обнаружены не числа!!!");
            return -12;
        }

        Timestamp dateTime;
        try {
            LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSeconds);
            dateTime = Timestamp.valueOf(localDateTime);
        } catch (Exception e) {
            System.out.println("Не удалось конвертировать дату");
            System.out.println(dto.getDateTime());
            return -13;
        }

        repository.save(MousePosition.builder()
                        .x(x)
                        .y(y)
                        .dateTime(dateTime)
                        .build());

        return 0;
    }
}
