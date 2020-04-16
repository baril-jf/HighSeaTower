import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeRouge  extends Plateforme {
    public PlateformeRouge(double y){
        super(y);
        this.couleur = Color.rgb(184, 15, 36);
    }

    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        double yAffiche = this.y - fenetreY;
        context.setFill(this.couleur);
        context.fillRect(this.x, yAffiche, this.largeur, this.hauteur);
    }
    public void drawDebug(GraphicsContext context, double fenetreY) {
        double yAffiche = this.y - fenetreY;
        context.setFill(Color.YELLOW);
        context.fillRect(x, yAffiche, this.largeur, this.hauteur);
    }
}
