package DrawGame.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class ImagePromt extends Application {



    @Override
    public void start(Stage VoteStage)  {


        Image image = new Image("file:1.png"); // Töltse be a háttérben

        VBox vbox = new VBox(20, new ImageView(image));
        vbox.setAlignment(Pos.BASELINE_CENTER);

        Label GiveaPromt = new Label("Give a promt for the immage:");
        Font promz = Font.font("ComicSans", FontWeight.BOLD,20);
        GiveaPromt.setFont(promz);
        vbox.getChildren().add(GiveaPromt);

        TextField TxPromt = new TextField();
        TxPromt.setMaxWidth(image.getWidth()/3);
        vbox.getChildren().add(TxPromt);

        Button SendInPromt = new Button("SendPromt");
        Font buttonz = Font.font("ComicSans", FontWeight.BOLD,20);
        SendInPromt.setFont(buttonz);
        vbox.getChildren().add(SendInPromt);

        //System.out.println(image.getHeight());

        Scene scene = new Scene(vbox, image.getWidth(), image.getHeight()*1.3);

        VoteStage.setScene(scene);


        VoteStage.setTitle("Vote");


        VoteStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

}
