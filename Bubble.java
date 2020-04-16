import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Bubble extends Entity{
    protected double vy;
    protected double rayon;
    //Construit les bulles
    public Bubble(double baseX, double yi){
        Random random;
        this.x = baseX;
        this.y = -yi;
        this.rayon = Math.random()*41+10;
        this.vy = -(Math.random()*451+350);
        this.vx = 0;
        this.couleur = Color.rgb(0,0,255,.4);
    }
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    public double getVitesseY(){
        return this.vy;
    }


    public void update(double dt) {

        y += dt*vy;

    }


    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        double yAffiche = y - fenetreY;
        context.setFill(couleur);
        context.fillOval(x-rayon,y-rayon,rayon,rayon);
    }
}
