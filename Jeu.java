

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class Jeu {
    public static final int WIDTH = 350, HEIGHT = 480;
    private ArrayList<Plateforme> obstacles= new ArrayList<>();
    private Plateforme[] plateformes = new Plateforme[5];
    private Jelly jelly;
    private ArrayList<Bubble> bubbles = new ArrayList<>();
    private double vitesseFenetreY = 50;
    private double minuterie;
    private double lastPlatformdistance = 0;
    private boolean debug;
    private String altitude;
    protected boolean gameOver;
    private double fenetreY = 0;
    public Jelly getjelly(){
        return this.jelly;
    }
    public Jeu() {
        gameOver = false;
        obstacles.add(new Fond(HEIGHT ));

        for (int j = 0; j < 5; j++) {
            lastPlatformdistance += 100;
            double r = Math.random();
            if (r <= 0.05 && !(obstacles.get(obstacles.size() - 1) instanceof PlateformeRouge)) {
                plateformes[j] = new PlateformeRouge(400 - j * 100);
            } else if (r <= 0.7) {
                plateformes[j] = new PlateformeSimple(400 - j * 100);
            } else if (r <= 0.8) {
                plateformes[j] = new PlateformeAccelerante(400 - j * 100);
            } else {
                plateformes[j] = new PlateformeBond(400 - j * 100);
            }
            obstacles.add(plateformes[j]);
        }
        lastPlatformdistance = 0;
        jelly = new Jelly(175, 430);
    }

    public void derouler(double dx){
        double acc = 1;
        if(jelly.accelere) {
            acc *= 3;
        }
        fenetreY -= dx * acc;
    }
    public void jump() {
        jelly.jump();
    }
    public void move(String side){
        jelly.move(side);
    }
    public void activerDebug(){
        debug = !debug;
    }

    public void update(double dt, boolean deroule) {
        if (jelly.testGameOver(-fenetreY)){
            gameOver = true;
        }
        if (deroule) {
            this.vitesseFenetreY+=2*dt;
            derouler((vitesseFenetreY * dt));
        }
        if ((jelly.y-fenetreY)<350*0.25){
            derouler(-(jelly.y-fenetreY)+350*0.25);
        }
        minuterie += dt;
        if(minuterie >= 3) {
            for (int i = 0; i < 3; i++) {
                double baseX = Math.random() * (WIDTH + 1);
                double yi = fenetreY-HEIGHT;
                for (int j = 0; j < 5; j++) {
                    if (Math.random() < 0.5){
                        int ajout= 20;
                        Bubble bubblesAdded = new Bubble(baseX+ajout, yi);
                        bubbles.add(bubblesAdded);
                    }
                    else{
                        int ajout = -20;
                        Bubble bubblesAdded = new Bubble(baseX+ajout, yi);
                        bubbles.add(bubblesAdded);
                    }
                }
            }
            minuterie = 0;
        }
        if (Math.abs((Math.abs(fenetreY)-lastPlatformdistance))>100){
            lastPlatformdistance+=100;
            double r = Math.random();
            if (r<=0.05 && !(obstacles.get(obstacles.size()-1) instanceof PlateformeRouge)){
                obstacles.add(new PlateformeRouge(fenetreY+(Math.abs(fenetreY)-lastPlatformdistance)));
            }
            else if (r<=0.7){
                obstacles.add(new PlateformeSimple(fenetreY+(Math.abs(fenetreY)-lastPlatformdistance)));
            }
            else if (r<=0.8){
                obstacles.add(new PlateformeAccelerante(fenetreY+(Math.abs(fenetreY)-lastPlatformdistance)));
            }
            else {
                obstacles.add(new PlateformeBond((int) (fenetreY+(Math.abs(fenetreY)-lastPlatformdistance))));
            }
        }
        jelly.accelere=false;
        for (Plateforme p : obstacles) {
            jelly.testCollision(p);
        }
        jelly.update(dt);
        for (Bubble b:bubbles)
            b.update(dt); {
        }
    }
    public void draw(GraphicsContext context) {
        context.setFill(Color.rgb(0, 0, 200));
        context.fillRect(0, 0, WIDTH, HEIGHT);
        context.setFill(Color.WHITE);
        altitude = Integer.toString(getjelly().score) + "m";
        context.setFont(Font.font("Purisa", 28));
        context.fillText(altitude, WIDTH/2 - 25 ,30);
        if(!debug) {
            jelly.draw(context, fenetreY);
            for (Plateforme p : obstacles) {
                p.draw(context, fenetreY);
            }
            for (Bubble b: bubbles){
                b.draw(context, fenetreY);
            }
        }else {
            String test = "";
            if(jelly.parterre){
                test = "oui";
            }else {
                test = "non";
            }
            context.setFont(Font.font("Purissa", 12));
            context.fillText("Position :", 5, 10);
            context.fillText("v :", 5, 20);
            context.fillText("a :", 5, 30);
            context.fillText("touche le sol : " + test, 5, 40);
            context.fillText("(" + Integer.toString((int)getjelly().getX())+" , " + (Integer.toString((int)(getjelly().getY()))) + ")", 55,10);
            context.fillText("(" + Integer.toString((int)getjelly().getVx())+" , " + (Integer.toString((int)(-getjelly().getVy()))) + ")", 20,20);
            context.fillText("(" + Integer.toString((int)getjelly().getAx())+" , " + (Integer.toString((int)(-getjelly().getAy()))) + ")", 20,30);
            context.fillText("Fond :", 5, 40);
            jelly.drawDebug(context, fenetreY);
            for (Plateforme p : obstacles) {
                if(jelly.intersects(p)){
                    p.drawDebug(context, fenetreY);
                }else{
                    p.draw(context, fenetreY);
                }
            }
            for (Bubble b: bubbles){
                b.draw(context, fenetreY);
            }
        }

    }
}