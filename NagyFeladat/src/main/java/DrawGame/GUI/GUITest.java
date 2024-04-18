package DrawGame.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUITest extends Application {
    private final int XX = 900;
    private final int YY = 600;
    public void events(Canvas canvas,GraphicsContext gc,Scene scene){
        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.B) {

                gc.setFill(Color.BLUE);
                gc.fillRect(100, 100, canvas.getWidth(), canvas.getHeight());
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                Platform.exit();


            }


        });


    }

    @Override
    public void start(Stage TestGUI) {
        //Csinálok egy Canvast
        Canvas canvas = new Canvas(XX, YY);

        //Grafikus felület a canvashoz
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set the fill color to white
        gc.setFill(Color.rgb(200,255,200));

        // Fill the entire canvas with white color
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Create a StackPane to hold the canvas
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Letrehozom az ablakot
        Scene scene = new Scene(root, XX, YY);



        events(canvas,gc,scene); //eventek futtatasa
        // ablak kirajzolasa ez keruljon a vegere
        TestGUI.setScene(scene);
        TestGUI.setTitle("GUI test");
        TestGUI.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}