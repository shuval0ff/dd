package com.example.demo.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class QrController {
    public Canvas canvas;
    public AnchorPane anchorPane;

    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double mm = 15.1;
        System.out.println(mm);

        int[] sticks = new int[]{
                5, 1, 4, 0, 9, 2, 0, 2, 0, 1, 2, 3, 4, 5, 6,
        };

        gc.setFill(Color.BLACK);

        int x0 = 20, y0 = 10;
        double heightStick = 22.85 * mm;
        double widthLimitingStick = 0.5 * mm;
        double spaceBetweenSticks = 0.2 * mm; // ОЧ МАЛО
        int startStick = x0;

        gc.fillRect(startStick, y0, widthLimitingStick, heightStick + 1.65 * mm);
        startStick += widthLimitingStick + spaceBetweenSticks;
        gc.fillRect(startStick, y0, widthLimitingStick, heightStick + 1.65 * mm);
        startStick += widthLimitingStick + spaceBetweenSticks;

        boolean drawMiddleStick = false;
        for (int i = 0; i < sticks.length; i++) {
            double widthStick = sticks[i] * 0.15 * mm;
            if (i == sticks.length / 2 && !drawMiddleStick) {
                widthStick = widthLimitingStick;
                gc.setFill(Color.BLACK);
                gc.fillRect(startStick, y0, widthStick, heightStick + 1.65 * mm);
                startStick += widthStick + spaceBetweenSticks;
                gc.fillRect(startStick, y0, widthStick, heightStick + 1.65 * mm);
                startStick += widthStick + spaceBetweenSticks;
                i--;
                drawMiddleStick = true;
                continue;
            }
            if (widthStick == 0) {
                widthStick = 1.35 * mm;
                gc.setFill(Color.WHITE);
            } else {
                gc.setFill(Color.BLACK);
            }
            gc.fillRect(startStick, y0, widthStick, heightStick);
            startStick += widthStick + spaceBetweenSticks;
        }

        gc.fillRect(startStick, y0, widthLimitingStick, heightStick + 1.65 * mm);
        startStick += widthLimitingStick + spaceBetweenSticks;
        gc.fillRect(startStick, y0, widthLimitingStick, heightStick + 1.65 * mm);
        canvas = canvas.getGraphicsContext2D().getCanvas();
    }
}
