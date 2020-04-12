import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class PlateformeSimple extends Plateforme {

    public PlateformeSimple(double y) {
        super(y);
        this.couleur = Color.rgb(230, 134, 58);
    }

    @Override
    public void draw(GraphicsContext context, double fenetreY) {;
        double yAffiche = y - fenetreY;
        context.setFill(couleur);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }
}
