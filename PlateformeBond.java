import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeBond extends Plateforme{
    public PlateformeBond(int y){
        super(y);
        this.couleur = Color.LIGHTGREEN;
    }

    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        double yAffiche = y - fenetreY;
        context.setFill(couleur);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }
}


