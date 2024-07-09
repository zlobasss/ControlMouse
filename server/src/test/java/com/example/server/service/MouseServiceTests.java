package com.example.server.service;

import org.junit.Before;
import org.junit.Test;

public class MouseServiceTests {

    private MouseService service;

    @Before
    public void setUp() {
        service = new MouseService();
    }

    @Test
    public void testWithZero() {
        int mouseId = 0;
        int x = 0;
        int y = 0;
        int result = service.setPosition(mouseId, x, y);
        assert result == 0;
    }
}
