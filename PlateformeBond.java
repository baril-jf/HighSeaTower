import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeBond extends Plateforme{
    public PlateformeBond(int y){
        super(y);
        this.couleur = Color.LIGHTGREEN;
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
        context.fillRect(this.x, yAffiche, this.largeur, this.hauteur);
    }

}


