
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* Auteur principal : Loic Wisniewski ; date de creation : decembre 2014 ; mise a jour : 1 decembre 2014  */

public class Trait {

    /* Classe permettant de dessiner des segments. Classe similaire a Point... qui est plus commentee ! */

    private Color c;
    private int xA;
    private int xB;
    private int yA;
    private int yB;
    private static List<Trait> InstanceList = new ArrayList<Trait>();

    public Trait(Point pointA, Point pointB, int R, int G, int B) {
        this(pointA, pointB);
        c = new Color(R, G, B);
    }

    public Trait(Trait p) {
        c = new Color(p.getColor().getRGB());
        xA = p.xA;
        xB = p.xB;
        yB = p.yB;
        yA = p.yA;
    }

    public Trait(Point pointA, Point pointB) {
        this.xA = pointA.getPosx() + 10;
        this.xB = pointB.getPosx() + 10;
        this.yA = pointA.getPosy() + 10;
        this.yB = pointB.getPosy() + 10;
        c = new Color(0, 0, 0);
        InstanceList.add(this);
    }

    private void draw() {
        Fenetre.getInstance().actualiserAffichage();
    }

    public Color getColor() {
        return c;
    }

    public int[] get2Position() {
        int[] i = new int[4];
        i[0] = this.xA;
        i[1] = this.xB;
        i[2] = this.yA;
        i[3] = this.yB;
        return i;
    }

    public static Trait getInstance(int id) {
        return InstanceList.get(id);
    }

    public static int numberInstances() {
        return InstanceList.size();
    }

    public void setColor(int R, int G, int B) {
        c = new Color(R, G, B);
        draw();
    }
}
