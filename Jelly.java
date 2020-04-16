import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Jelly extends Entity {

    private Image[] frames;
    private Image image;
    private double frameRate = 8;
    private double tempsTotal = 0;
    protected boolean parterre = true;
    protected boolean faceDroite;
    protected boolean accelere = false;
    protected int score = 0;

    public Jelly(double x, double y) {
        this.x = x;
        this.y = y;
        this.largeur = 50;
        this.hauteur = 50;
        this.ay = 0;
        this.vx = 0;
        updateImage();
    }
    public void setParterre(boolean vF){
        if (vF == true){
            this.parterre = true;
        }else {
            this.parterre = false;
        }
    }
    public void updateImage(){
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
        super.update(dt);
        score = (int)(-this.y + HighSeaTower.HEIGHT - this.hauteur);
        tempsTotal += dt;
        int frame = (int) (tempsTotal * frameRate);
        image = frames[frame % frames.length];
        if (this.vx<0 && this.x<=0){
            this.x = 0;
            this.vx = -vx;
        }
        if (this.vx>0 && this.x+this.largeur>= HighSeaTower.WIDTH){
            this.x=HighSeaTower.WIDTH-this.largeur;
            this.vx = -vx;
        }
    }
    public boolean testGameOver(double fond){
        if ((-this.y + HighSeaTower.HEIGHT) < fond){
            return true;
        }else {
            return false;
        }
    }
    public void testCollision(Plateforme other) {
        if (intersects(other) && Math.abs(this.y + this.hauteur - other.y) < 10
                && this.vy > 0 ) {
            pushOut(other);
            if (other instanceof PlateformeSimple || other instanceof PlateformeRouge || other instanceof Fond) {
                this.vy = 0;
                this.parterre = true;
            }else if (other instanceof PlateformeBond){
                this.parterre = true;
                this.vy *=-1.5;
                if(this.vy > -100){
                    this.vy = -100;
                }
            }else if (other instanceof PlateformeAccelerante){
                this.parterre= true;
                this.vy = 0;
                this.accelere = true;
            }
        }
        if (intersects(other) && Math.abs(this.y - other.y-10) < 10
                && vy < 0 && other instanceof PlateformeRouge) {
            this.vy = -vy;
        }
    }
    public boolean intersects(Plateforme other) {
        return !(this.x + largeur < other.x || other.x + other.largeur < this.x
                  || this.y + this.hauteur < other.y || other.y + other.hauteur < this.y);
    }
    /**
     * Repousse le personnage vers le haut (sans déplacer la
     * plateforme)
     */
    public void pushOut(Plateforme other) {
        double deltaY = this.y + this.hauteur - other.y;
        this.y -= deltaY;
    }
    /**
     * Le personnage peut seulement sauter s'il se trouve sur une
     * plateforme
     */
    public void jump() {
        if(this.parterre) {
            this.setParterre(false);
            this.vy = -600;
        }
    }
    public void move(String side){
        int mult = 1;
        if (side == "gauche"){
            mult = -1;
            faceDroite = false;
        }else{
            faceDroite = true;
        }
        this.ax = 1200 * mult;
        updateImage();
    }
    @Override
    public void draw(GraphicsContext context, double fenetreY) {
        this.yAffiche = this.y - fenetreY;
        context.drawImage(this.image, this.x, yAffiche, this.largeur, this.hauteur);
    }
    public void drawDebug(GraphicsContext context, double fenetreY) {
        this.yAffiche = this.y - fenetreY;
        context.setFill(Color.PINK);
        context.fillRect(this.x, yAffiche, this.largeur, this.hauteur);
        context.drawImage(image, x, yAffiche, this.largeur, this.hauteur);
    }
}
