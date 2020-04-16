import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Plateforme extends Entity {

    public Plateforme(double y) {
        this.x = (int) (Math.random() * 350) ;
        this.y = y;
        this.largeur =  new Random().nextInt((95) + 1) + 80;
        this.hauteur = 10;

        if (x-largeur<0){
            x+=(largeur/2);
        }else if (x+largeur>HighSeaTower.WIDTH){
            x-=(largeur);
        }
    }
    @Override
    public void draw(GraphicsContext context, double fenetreY) {};
    public void drawDebug(GraphicsContext context, double fenetreY) {};
}

