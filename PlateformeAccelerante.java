import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeAccelerante extends Plateforme {
    public PlateformeAccelerante(double y){
        super(y);
        this.couleur = Color.rgb(230, 221, 58);
    }

    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        double yAffiche = y - fenetreY;
        context.setFill(couleur);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }
}
