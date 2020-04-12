import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Jelly extends Entity {

    private Image[] frames;
    private Image image;
    private double frameRate = 8; // 3 frame par sec
    private double tempsTotal = 0;

    private boolean parterre;
    protected boolean faceDroite;
    protected boolean accelere = false;

    public Jelly(double x, double y) {
        this.x = x;
        this.y = y;
        this.largeur = 50;
        this.hauteur = 50;
        this.ay = 1200;
        this.vx = 0;

        // Chargement des images
        if (faceDroite) {
            frames = new Image[]{
                new Image("/jellyfish1.png"),
                new Image("/jellyfish2.png"),
                new Image("/jellyfish3.png"),
                new Image("/jellyfish4.png"),
                new Image("/jellyfish5.png"),
                new Image("/jellyfish6.png")
            };
        }else {
            frames = new Image[]{
                new Image("/jellyfish1g.png"),
                new Image("/jellyfish2g.png"),
                new Image("/jellyfish3g.png"),
                new Image("/jellyfish4g.png"),
                new Image("/jellyfish5g.png"),
                new Image("/jellyfish6g.png")
            };
        };
        image = frames[0];
    }

    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);

        // Mise à jour de l'image affichée
        tempsTotal += dt;
        int frame = (int) (tempsTotal * frameRate);

        image = frames[frame % frames.length];
    }

    public void testCollision(Plateforme other) {
        //Ajouter la condition de ne pas passer à travers la rouge!!!
        /**
         *
         * La collision avec une plateforme a lieu seulement si :
         *
         * - Il y a une intersection entre la plateforme et le personnage
         *
         * - La collision a lieu entre la plateforme et le *bas du personnage*
         * seulement
         *
         * - La vitesse va vers le bas (le personnage est en train de tomber,
         * pas en train de sauter)
         */
        if (intersects(other) && Math.abs(this.y + hauteur - other.y) < 10
                && vy > 0) {
            pushOut(other);
            if (other instanceof PlateformeSimple || other instanceof PlateformeRouge) {
                this.vy = 0;
                this.parterre = true;
            }else if (other instanceof PlateformeBond){
                this.vy = -600;
            }else if (other instanceof PlateformeAccelerante){
                this.parterre = true;
                this.accelere = true;
            }
        }
    }

    public boolean intersects(Plateforme other) {
        return !( // Un des carrés est à gauche de l’autre
                x + largeur < other.x
                        || other.x + other.largeur < this.x
                        // Un des carrés est en haut de l’autre
                        || y + hauteur < other.y
                        || other.y + other.hauteur < this.y);
    }

    /**
     * Repousse le personnage vers le haut (sans déplacer la
     * plateforme)
     */
    public void pushOut(Plateforme other) {
        double deltaY = this.y + this.hauteur - other.y;
        this.y -= deltaY;
    }

    public void setParterre(boolean parterre) {
        this.parterre = parterre;
    }

    /**
     * Le personnage peut seulement sauter s'il se trouve sur une
     * plateforme
     */

    public void jump() {
        if (parterre) {
            vy = -600;
            parterre =! parterre;
        }
    }
    public void move(String side){
        int droite = 1;
        if (side == "gauche"){
            droite = -1;
            faceDroite = false;
        }else{
            faceDroite = true;
        }
        ax = 10 * droite;
    }
    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        double yAffiche = y - fenetreY;
        context.drawImage(image, x, yAffiche, largeur, hauteur);
    }
}
