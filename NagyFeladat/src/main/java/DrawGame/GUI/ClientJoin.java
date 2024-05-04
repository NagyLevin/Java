package DrawGame.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientJoin extends Application {


    private final int XX = 300;
    private final int YY = 500;


    public void events( Scene scene, TextField UserCode,TextField UserName, Button JoinButton,Player player){   //eventek egy helyre összegyüjtve

        //public void handle(Event event) { inkább majd igy!!!!!!!!!!!!!!!!!!!!!!!

        scene.setOnKeyPressed(event -> {



            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }


        });

        JoinButton.setOnAction(event -> {
            String code = UserCode.getText();
            //UserCode.setText("");
            String nev = UserName.getText();

            System.out.println("Kod: " + code); //az eltarolt adat
            System.out.println("Nev: " + nev); //az eltarolt adat

            player.setName(nev);

        });


    }
    @Override
    public void start(Stage ClientStage) {

        Player ClientPlayer = new Player();



        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        Label Drawfullclient = new Label("DrawfulL Client");
        Font startgombfont = Font.font("ComicSans", FontWeight.BOLD,30);    //nezz utana hogy van e comicsans ///TO DO
        Drawfullclient.setFont(startgombfont);
        vBox.getChildren().add(Drawfullclient);

        Label CodeZone = new Label("Eneter Code:");
        Font codez = Font.font("ComicSans", FontWeight.BOLD,20);    //nezz utana hogy van e comicsans ///TO DO
        CodeZone.setFont(codez);
        vBox.getChildren().add(CodeZone);

        TextField userCode = new TextField();
        userCode.setMaxWidth(XX/3);
        vBox.getChildren().add(userCode);


        Label EnterName = new Label("Eneter Your Name:");
        Font namez = Font.font("ComicSans", FontWeight.BOLD,20);
        EnterName.setFont(namez);
        vBox.getChildren().add(EnterName);

        TextField userName = new TextField();
        userName.setMaxWidth(XX/3);
        vBox.getChildren().add(userName);

        Button ClientJoinButton = new Button("Join");
        Font buttonz = Font.font("ComicSans", FontWeight.BOLD,20);
        ClientJoinButton.setFont(buttonz);

        vBox.getChildren().add(ClientJoinButton);





        vBox.setStyle("-fx-background-color: rgb(200, 255, 200);"); //esetleg playercolorra?
        Scene scene = new Scene(vBox, 300, 200);


        ClientStage.setScene(scene);


        ClientStage.setTitle("ClientJoin");


        ClientStage.show();


        events(scene,userCode,userName,ClientJoinButton,ClientPlayer);






    }



    public static void main(String[] args) {

        launch(args);
    }
}