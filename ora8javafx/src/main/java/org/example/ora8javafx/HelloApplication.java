package org.example.ora8javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class HelloApplication extends Application {
    public void start(Stage stage) {
        Group root = new Group();
        Button gomb = new Button("Hello World!");
        root.getChildren().add(gomb);
        gomb.setLayoutX(100);
        gomb.setLayoutY(100);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("ABLAK");
        //stage.setMaxHeight(500);

        stage.setScene(scene);
        stage.show();



    }
}
//Platform.exit() megállitja
//Stage az ablak
//Scene az ablakok tartalma
//Panel nodeok csoportostása
//Panel layout extra szabályok
//FlowPane
//sor-, vagy oszlopfolytonosan helyezi el a gyerekeit; azok mérete a preferált méret lesz
//ne felejsd el ezeket használni
//figyelj, hogy minek hol állitod be az elhelyezkedeset
//szülö befolyásolja a gyereket, gyerek a szulot, last szelesseg
//gridbe bele a flow planet akár
//guis alkokhoz SCENEBUILDER APP
//mause event key event touch event
//nem mindegy hogy event listener vagy filter
//kep be kiimportalas az egyik példában
//Helyette létre kell hozni egy Runnable objektumot, aminek a run() metódusa elvégzi a GUI állapotmódosítását, és ezt kell a Platform.runLater() utasítással meghívni. Ekkor a módosítást a JAT szál fogja végrehajtani, tehát a szabály teljesül.
//Tetszőleges kódban a Platform.isFxApplicationThread() megadja, hogy a JAT szál futtatja-e az adott kódot. Észrevehető, hogy ez már a GUI inicializálásakor is teljesül, hiszen az Application.start() a JAT szálon hívódik meg.
//EZZEL TUDOM MEGNÉZNI, HOGY A JAT SZÁLON VAGYOK-e


//minden elem node ként létezik
//Label: szöveg megjelenítése
//TextField, PasswordField: szöveg bevitelére
//Button: nyomógomb
//RadioButton, CheckBox, ToggleButton: választási lehetőségek kiválasztására
//TextArea: többsoros szövegbevitel
//ScrollBar, ScrollPane: görgethető felület
//MenuBar, Menu, MenuItem: menük létrehozására
//ImageView: képek megjelenítésére






