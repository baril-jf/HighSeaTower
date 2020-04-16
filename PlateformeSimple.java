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
        double yAffiche = this.y - fenetreY;
        context.setFill(couleur);
        context.fillRect(x, yAffiche, this.largeur, this.hauteur);
    }
    public void drawDebug(GraphicsContext context, double fenetreY) {
        double yAffiche = this.y - fenetreY;
        context.setFill(Color.YELLOW);
        context.fillRect(this.x, yAffiche, this.largeur, this.hauteur);
    }
}
