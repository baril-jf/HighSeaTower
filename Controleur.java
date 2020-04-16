
import javafx.scene.canvas.GraphicsContext;


public class Controleur {

    Jeu jeu;

    public Controleur() {
        jeu = new Jeu();
    }

    void draw(GraphicsContext context) {
        jeu.draw(context);
    }

    void update(double deltaTime, boolean deroule) {
        jeu.update(deltaTime, deroule);
    }

    void jump() {
        jeu.jump();
    }

    void move(String side) { jeu.move(side);}

    boolean checkGameOver(){
        if(jeu.gameOver){
            return true;
        }else {
            return false;
        }
    }
    void activerDebug() {jeu.activerDebug();}
}
