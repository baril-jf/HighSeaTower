import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Fond extends Plateforme{
        public Fond(double y) {
            super(y);
            this.x = 0;
            this.largeur = 350;
            this.hauteur = 3;
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


