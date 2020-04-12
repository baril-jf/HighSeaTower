import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeRouge  extends Plateforme {
    public PlateformeRouge(double y){
        super(y);
        this.couleur = Color.rgb(184, 15, 36);
    }

    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        double yAffiche = y - fenetreY;
        context.setFill(couleur);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }
}
