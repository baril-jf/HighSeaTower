
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;

    private Plateforme[] plateformes = new Plateforme[5];
    private Jelly jelly;

    public Jeu() {
        for (int i = 0; i < plateformes.length; i++) {
            plateformes[i] = new Plateforme((double) i / plateformes.length * WIDTH, Math.random() * HEIGHT);
        }

        jelly = new Jelly(175, 472);
    }
//    private double fenetreY = 0;
//
//    public void haut() {
//        fenetreY -= 1;
//    }

    public void jump() {
        jelly.jump();
    }

    public void update(double dt) {
        /**
         * À chaque tour, on recalcule si le personnage se trouve parterre ou
         * non
         */
        jelly.setParterre(false);

        for (Plateforme p : plateformes) {
            p.update(dt);
            // Si le personnage se trouve sur une plateforme, ça sera défini ici
            jelly.testCollision(p);
        }
        jelly.update(dt);
    }

    public void draw(GraphicsContext context) {
        context.setFill(Color.BLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        jelly.draw(context);
        for (Plateforme p : plateformes) {
            p.draw(context);
        }
    }
}
