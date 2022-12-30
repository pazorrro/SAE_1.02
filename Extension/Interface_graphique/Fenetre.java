
import javax.swing.*;

/* Auteur principal : Loic Wisniewski ; date de creation : decembre 2014 ; mise a jour : 1 decembre 2014  */

public class Fenetre extends JFrame {

    private static boolean thereIsInstance = false;
    private static Fenetre THEFENETRE = null;
    private static Panneau pan = null;

    private Fenetre(int w, int h, String titre) { // parametrage de la fenetre
        // Remarque : constructeur prive (!) car utilise seulement par la methode createFenetre
        this.setTitle(titre);
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        Fenetre.pan = new Panneau();
        this.setContentPane(Fenetre.pan);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        thereIsInstance = true;
    }

    public static void createFenetre(int w, int h, String titre) {
	/* 
	   Role : creation de l'unique objet/instance de la classe Fenetre.
	   L'attribut de classe THEFENETRE permet de stocker cette unique fenetre creee.
	   L'attribut de classe booleen thereIsInstance est mis a true par le constructeur, 
	   empechant la creation future d'une deuxieme fenetre.
	*/
        if (!thereIsInstance)
            THEFENETRE = new Fenetre(w, h, titre);
    }

    public static Fenetre getInstance() { // permet d'acceder a l'unique instance de la fenetre
        return Fenetre.THEFENETRE;
    }

    public void actualiserAffichage() { // actualise l'affichage
        pan.repaint();
    }
}
