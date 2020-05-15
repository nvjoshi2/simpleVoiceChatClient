package client_voice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ClientWindow extends Application {

    private Stage window;
    private Button startButton;
    private Button stopButton;

    public static boolean calling = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Client client = new Client();

        window = primaryStage;
        startButton = new Button("start");
        stopButton = new Button("stop");

        //set button logic
        startButton.setOnAction(e -> {
            //start button logic
            System.out.println("BUTTON PRESSED");
            if (!calling) {
                client.initAudio();
            }

        });
        stopButton.setOnAction(e -> {
            //stop button logic
            calling = false;

        });

        VBox layout = new VBox(5);
        layout.getChildren().add(startButton);
        layout.getChildren().add(stopButton);

        Scene scene = new Scene(layout, 500, 250);
        window.setScene(scene);
        window.show();

    }
}
