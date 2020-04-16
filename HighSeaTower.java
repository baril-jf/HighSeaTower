
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TimerTask;

import static java.util.logging.Logger.global;

public class HighSeaTower extends Application {

    public static final int WIDTH = 350, HEIGHT = 480;
    private boolean deroule = false;
    private boolean modeDebug = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Controleur controleur = new Controleur();

        scene.setOnKeyPressed((value) -> { //Il faut que ça parte uniquement si up, left, right ou space sont touchées, là c'est n'importe quelle touche

            if (value.getCode() == KeyCode.LEFT) {
                controleur.move("gauche");
                deroule = !modeDebug;
                controleur.jeu.getjelly().ay = 1200;
            }
            if (value.getCode() == KeyCode.RIGHT) {
                controleur.move("droite");
                deroule = !modeDebug;
                controleur.jeu.getjelly().ay = 1200;
            }
            if (value.getCode() == KeyCode.SPACE || value.getCode() == KeyCode.UP) {
                controleur.jump();
                deroule = !modeDebug;
                controleur.jeu.getjelly().ay = 1200;
            } if (value.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
            if (value.getCode() == KeyCode.T){
                modeDebug = !modeDebug;
                controleur.activerDebug();
                if(modeDebug){
                    deroule = false;
                }else{
                    deroule = true;
                }
            }
        });
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double deltaTime = (now - lastTime) * 1e-9;

                controleur.update(deltaTime,deroule);
                controleur.draw(context);
                lastTime = now;
                if (controleur.checkGameOver()){
                    controleur.jeu = new Jeu();
                    deroule = false;
                }
            }
        };
        timer.start();
        primaryStage.setTitle("High Sea Tower");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/jellyfish1.png")); // Afficher l'icÃ´ne de la mÃ©duse sur la fenÃªtre
        primaryStage.setResizable(false); // Pour que le fenÃªtre ne puisse pas Ãªtre resizable
        primaryStage.show();
    }
}
