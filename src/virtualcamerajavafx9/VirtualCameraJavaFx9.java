/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualcamerajavafx9;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 *
 * @author SG_OFFICE
 */
public class VirtualCameraJavaFx9 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        boolean supp = Platform.isSupported(ConditionalFeature.SCENE3D);
        
        try{
            if(supp){
        
                    Group group = new Group();
                    
                    Box box1 = new Box(100, 200, 100);
                    Translate translate1 = new Translate(-220, 0, 800);
                    box1.getTransforms().add(translate1);
                    
                    Box box2 = new Box(100, 200, 100);
                    Translate translate2 = new Translate(0, 0, 800);
                    box2.getTransforms().add(translate2);

                    Box box3 = new Box(100, 200, 100);
                    Translate translate3 = new Translate(220, 0, 800);
                    box3.getTransforms().add(translate3);

                    Box box4 = new Box(100, 200, 100);
                    Translate translate4 = new Translate(-200, 0, 1200);
                    box4.getTransforms().add(translate4);
                    
                    Box box5 = new Box(100, 200, 100);
                    Translate translate5 = new Translate(0, 0, 1200);
                    box5.getTransforms().add(translate5);

                    Box box6 = new Box(100, 200, 100);
                    Translate translate6 = new Translate(200, 0, 1200);
                    box6.getTransforms().add(translate6);

                    group.getChildren().addAll(box4, box5, box6, box1, box2, box3);

                    PhongMaterial m = new PhongMaterial(Color.BLUE);
                    box1.setMaterial(m);
                    box6.setMaterial(m);
                    PhongMaterial m2 = new PhongMaterial(Color.ORANGE);
                    box3.setMaterial(m2);
                    box4.setMaterial(m2);
                    PhongMaterial m3 = new PhongMaterial(Color.GREEN);
                    box2.setMaterial(m3);
                    PhongMaterial m4 = new PhongMaterial(Color.AQUA);
                    box5.setMaterial(m4);

                    SplitPane layout = new SplitPane();
                    SubScene subScene;
                    layout.getItems().add(subScene = new SubScene(group, 1000, 500));
                    Translate translate = new Translate(0, 0, 0);
                    subScene.getTransforms().add(translate);
                    
                    Scene scene = new Scene(layout);

                    primaryStage.setTitle("Wirtualna Kamera - Robert Stefaniak: \n"
                        + "F - tyl, N - przod, L - lewo, R - prawo, T - gora, B - dol, Y - skret lewo, X - skret prawo, C - zoom-in, D - zoom-out");
                    primaryStage.setScene(scene);
                    primaryStage.show();

                    PerspectiveCamera cam = new PerspectiveCamera(true);
                    cam.setNearClip(0.1);
                    cam.setFarClip(2000.0);
                    cam.setFieldOfView(30);
                    cam.getTransforms().add(new Translate(0, 0, 0));
                    
//                    Rotate rotate = new Rotate(0);
//                    cam.getTransforms().add(rotate);
                    
                    cam.setRotationAxis(Rotate.Y_AXIS);
                    cam.setRotate(0);
                    subScene.setCamera(cam);
                    
                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                   switch(event.getCode()){
                       case N: 
                           cam.translateZProperty().set(cam.getTranslateZ() + 50);
                           break;
                       case F:
                           cam.translateZProperty().set(cam.getTranslateZ() - 50);
                           break;
                       }
                    });

                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                       switch(event.getCode()){
                           case R: 
                               cam.translateXProperty().set(cam.getTranslateX() + 50);
                               break;
                           case L:
                               cam.translateXProperty().set(cam.getTranslateX() - 50);
                               break;
                       }
                    });

                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                       switch(event.getCode()){
                           case B: 
                               cam.translateYProperty().set(cam.getTranslateY() + 50);
                               break;
                           case T:
                               cam.translateYProperty().set(cam.getTranslateY() - 50);
                               break;
                       }
                    });

                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                        
                       switch(event.getCode()){
                           case X: 
                               cam.rotateProperty().set(cam.getRotate() + 2);
                               break;
                           case Y:
                               cam.rotateProperty().set(cam.getRotate() - 2);
                               break;
                       }
                    });
                    
                    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                       switch(event.getCode()){
                           case C: 
                               group.scaleXProperty().set(group.getScaleX() + 0.1);
                               group.scaleYProperty().set(group.getScaleY() + 0.1);
                               break;
                           case D:
                               group.scaleXProperty().set(group.getScaleX() - 0.1);
                               group.scaleYProperty().set(group.getScaleY() - 0.1);
                               break;
                       }
                    });
                    
            }else{
                //...
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
