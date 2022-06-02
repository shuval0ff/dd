package com.example.demo.controller;

import com.example.demo.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private VBox vbox;

    @FXML
    private Button qr, pdf, warning;

    private long minutes;

    @FXML
    protected void initialize() throws SQLException, IOException {
        Timer timer = new Timer(true);
        minutes = 45 - ChronoUnit.MINUTES.between(LocalDateTime.now(), LocalDateTime.of(2022, 06, 02, 3, 0)) * -1;
        timer.scheduleAtFixedRate(new MyTimerTask(), 0, 1000 * 60);
        qr.setOnMouseClicked(mouseEvent -> {
            try {
                HelloApplication.stage.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("qr.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pdf.setOnMouseClicked(mouseEvent -> {
            try {
                HelloApplication.stage.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("pdf.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        warning.setOnMouseClicked(mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Время доступа");
            alert.setContentText("Отсталось " + minutes * -1 + " минут до выхода из аккаунта!");
            alert.show();
        });
//        ResultSet resultSet = HelloApplication.connection.createStatement().executeQuery("SELECT * FROM test;");
//        while (resultSet.next()) {
//            vbox.getChildren().add(
//                    FXMLLoader.load(
//                            HelloApplication.class.getResource("elem.fxml"),
//                            new CustomResourceBundle(
//                                    new Pair[] {
//                                            new Pair<>("id", resultSet.getInt(1)),
//                                            new Pair<>("title", resultSet.getString(2))
//                                    }
//                            )
//                    )
//            );
//        }
    }

    @FXML
    protected void onHelloButtonClick() throws SQLException {


    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Platform.runLater(() -> {
                int hours = (int) minutes / 60;
                qr.setText(String.valueOf(hours) + ":" + String.valueOf(minutes - (hours * 60)));
                minutes--;
            });
        }
    }

    // .format(DateTimeFormatter.ofPattern("dd.MM.yyyy h:m:s")
}
