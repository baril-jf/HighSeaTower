import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Plateforme extends Entity {

    public Plateforme(double y) {
        this.x = (int) (Math.random() * 350) ;
        this.y = y;
        this.largeur =  new Random().nextInt((95) + 1) + 80;
        this.hauteur = 10;
    }

    @Override
    public void draw(GraphicsContext context, double fenetreY) {};
}



//public class Plateforme extends Sprite {
//    protected Color couleur;
//
//    public Plateforme (double y){
//        super(x,y);
//        this.x = (int) (Math.random() * 350);
//    }
//    protected double hauteur = 10;
//    protected double largeur = x + (int) (Math.random() * (175-80)) + 80; //trouvé sur stack OF, à vérifier
//    protected double probabilite;
//}
