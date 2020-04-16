import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity {

    protected double largeur, hauteur;
    protected double x, y;

    protected int vx, vy;
    protected int ax, ay;

    protected double yAffiche;
    protected Color couleur;

    public void update(double dt) {
        this.vx += dt * this.ax;
        this.vy += dt * this.ay;
        this.x += dt * this.vx;
        this.y += dt * this.vy;
        this.vx *= 0.8;
    }
    public double getHauteur() {
        return this.hauteur;
    }
    public double getLargeur() {
        return this.largeur;
    }
    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }
    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public int getVx() {
        return this.vx;
    }
    public void setVx(int vx) {
        this.vx = vx;
    }
    public int getVy() {
        return this.vy;
    }
    public void setVy(int vy) {
        this.vy = vy;
    }
    public int getAx() {
        return this.ax;
    }
    public void setAx(int ax) {
        this.ax = ax;
    }
    public int getAy() {
        return this.ay;
    }
    public void setAy(int ay) {
        this.ay = ay;
    }
    public abstract void draw(GraphicsContext context, double fenetreY);
}
