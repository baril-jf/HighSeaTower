
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TimerTask;

public class HighSeaTower extends Application {

    public static final int WIDTH = 350, HEIGHT = 480;

    /**
     * @param args the command line arguments
     */
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

        //ArrayList<Plateforme> plateformes = new ArrayList<Plateforme>();
//
//        scene.setOnKeyPressed((e) -> {
//            switch (e.getCode()) {
//                case LEFT:
//                    controleur.gauche();
//                    break;
//                case RIGHT:
//                    controleur.droite();
//                    break;
//                case UP:
//                    controleur.jump();
//                    break;
//                case ESCAPE:
//                    Platform.exit();
//                case T:
//                    controleur.debug() ou debug = true
//                }
//            }
//        }

        scene.setOnKeyPressed((value) -> {

            if (value.getCode() == KeyCode.LEFT) {
                controleur.move("gauche");
            }
            if (value.getCode() == KeyCode.RIGHT) {
                controleur.move("droite");
            }
            if (value.getCode() == KeyCode.SPACE || value.getCode() == KeyCode.UP) {
                controleur.jump();
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
                controleur.update(deltaTime);
                controleur.draw(context);
                //controleur.derouler(1);
                lastTime = now;
            }
        };
        
        timer.start();
        primaryStage.setTitle("High Sea Tower");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
