
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.w3c.dom.ls.LSOutput;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;
    private Plateforme[] plateformes = new Plateforme[5];
    private Jelly jelly;

    public Jeu() {
        for (int i = 0; i < plateformes.length; i++) {
            int prob = (int) (Math.random() * 100);
            int posY = 470 - (100 + 10*i) * (i+1);
                if (prob < 6){
                    plateformes[i] = new PlateformeRouge(posY);
                }else if (prob > 5 && prob < 16 ){
                    plateformes[i] = new PlateformeAccelerante(posY);
                }else if (prob > 16 && prob < 36){
                    plateformes[i] = new PlateformeBond(posY);
                }else {
                    plateformes[i] = new PlateformeSimple(posY);
                }
        }

        jelly = new Jelly(175, 472);
    }

    private double fenetreY = 0;

    public void derouler(int vitesse){
        int acc = 1;
        if(jelly.accelere = true) {
            acc = 3;
        }
        fenetreY -= vitesse * 3;
    }

    public void jump() {
        jelly.jump();
    }
    public void move(String side){
        jelly.move(side);
    }
    public void update(double dt) {
        /**
         * À chaque tour, on recalcule si le personnage se trouve parterre ou
         * non
         */
        //jelly.setParterre(true);

        for (Plateforme p : plateformes) {
            p.update(dt);
            // Si le personnage se trouve sur une plateforme, ça sera défini ici
            jelly.testCollision(p);
        }
        jelly.update(dt);
    }
    public void ajouterPlateforme(double hauteur) {

            Plateforme obs = null;
            int randomType = (int) (Math.random() * 10); // Nombre de types d'obstacles
            System.out.println(randomType);
//            switch (randomType) {
//                case 0:
//                    double minY = ObstacleSinus.AMPLITUDE / 2.0 + initialRayon;
//                    double maxY = FlappyGhost.GAME_HEIGHT - ObstacleSinus.AMPLITUDE / 2.0 - initialRayon;
//                    if (initialY > maxY) { initialY = maxY; } else if (initialY < minY) { initialY = minY; }
//
//                    obs = new ObstacleSinus(initialX, initialY);
//                    break;
//                case 1:
//                    obs = new ObstacleStatique(initialX, initialY);
//                    break;
//                case 2:
//                    obs = new ObstacleQuantique(initialX, initialY);
//                    break;
//            }
//
//            obs.setRayon(initialRayon);
//
//            obstacles.add(obs);
//            app.ajouterObstacle(obs.getX(), obs.getY(), obs.getRayon(), obs.getImageIndex());
        }

    public void draw(GraphicsContext context) {
        context.setFill(Color.BLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        jelly.draw(context, fenetreY);
        for (Plateforme p : plateformes) {
            p.draw(context, fenetreY);
        }
    }
}
