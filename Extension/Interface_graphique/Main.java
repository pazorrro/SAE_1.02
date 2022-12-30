
public class Main {

    public static void main(String[] args) {
        boolean i = false;
        Fenetre.createFenetre(1000, 900, "Ma fenetre"); // Creation de la fenetre avec cette methode de classe ; la fenetre se gere seule par la suite.
        Point j = new Point(100, 100); // Creation d'un point en position (100,100)
        Point t = new Point(200, 100); // Creation d'un point en position (200,100)
        Trait f = new Trait(j, t);     // Creation d'un trait a partir des 2 points

        while (true) {
            if (i) { // Test pour savoir de quelles couleurs on doit afficher les points
                j.setColor(100, 0, 100);
                t.setColor(0, 255, 255);
            } else {
                j.setColor(0, 255, 255);
                t.setColor(100, 0, 100);
            }
            try {
                Thread.sleep(1000); // timer d'une seconde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = !i;  // Pour alterner, faire clignoter les 2 points
        }
    }

}
