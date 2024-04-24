package FXML;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlMain extends Application {
    // Pelda alkalmazas FXML hasznalatara.
    // Az itt talalhato kodok lenyegeben 2 tutorial eredmenye:
    //https://www.callicoder.com/javafx-fxml-form-gui-tutorial/
    //https://www.callicoder.com/javafx-css-tutorial/
    // Alapvetoen valamilyen SceneBuilder alkalmazassal szokas ilyeneket csinalni.
    // Ezek lenyege, hogy a statikus GUIt megtervezed majd legeneralja az FXML es CSS fajlokat.
    // A dinamikus viselkedest pedig kodbol oldod meg.
    // Scene builder pelda: https://gluonhq.com/products/scene-builder/

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainScene.fxml"));
        Parent root = fxmlLoader.load();
        // A generalt UI-hoz tartozo contoller lekerese:
        MainSceneController controller = fxmlLoader.getController();
        stage.setTitle("FXML Example app");
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
