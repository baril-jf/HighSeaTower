import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bulles extends Entity {

    public Bulles (double x, double y){
        this.x = x;
        this.y = y;
        this.couleur = Color.rgb(0, 0, 255, 0.4);
        this.vy = -(double) (Math.random() * (450 - 350) + 350);
        this.rayon = 2;

    }
    protected double rayon = (double) (Math.random() * (40 - 10)) + 10; //trouvé sur stack OF, à vérifier
    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        for (int i =0;i< 5;i++){
            context.fillOval(
                    this.x - this.rayon,
                    this.y - this.rayon,
                    2 * this.rayon,
                    2 * this.rayon);
        }

    }
//    @Override
//    public void draw(GraphicsContext context, double fenetreY) {
//        double yAffiche = y - fenetreY;
//        context.setFill(couleur);
//        context.fillRect(x, yAffiche, largeur, hauteur);
//    }
}

//Ça va se régler ailleurs:

//BullesPour donner un peu de vie à l’océan, l’arrière-plan va afficher des bulles qui remontent à la
//        surface de temps en temps.
//        Des bulles apparaissent dans l’arrière-plan à toutes les 3 secondes :
//        •Les bulles font partie du décor, la méduse ne peut pas entrer en collision avec elles
//        •Pour donner un effet plus réaliste, les bulles sont générées en 3 petits groupes de 5 bulles :
//        –Trois coordonnées base x sont générées aléatoirement entre 0 et la largeur de l’écran
//        –Pour chacune de ces coordonnées, cinq bulles sont générées avec comme positionxinitiale la valeur base x ±20
//        –La position y initiale des bulles doit être à l’extérieur de l’écran (tout en bas).
//        Les bulles montent jusqu’à ce qu’elles dépassent le haut de l’écran