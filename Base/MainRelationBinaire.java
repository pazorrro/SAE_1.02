import java.util.*;
import java.lang.*;

public class MainRelationBinaire {


    /**
     * action : demande à l'utilisateur de saisir un entier nb > 0 et un réel p strictement compris entre 0 et 1 (avec vérification des saisie),
     * puis crée 5 relations binaires dans l'ensemble {0,1,2, ..., nb-1}, R0 vide, R1= '=', R2 = '<=', R3 une relation
     * binaire aléatoire à laquelle chaque couple a la probabilité p d'appartenir et R4 pleine, puis, pour i de 0 à 4,
     * affiche Ri sous 2 formes (matrice et ensemble de couples), puis affiche ses propriétés
     * (réflexive, ..., relation d'ordre) et les relations binaires suivantes obtenues à partir de Ri :
     * Hasse, fermeture transitive de Hasse et fermeture transitive de Hasse avec boucles (sous 2 formes aussi)
     */
    public static void main(String[] args) {

        System.out.println("\n\n\n\n");
        System.out.println(" /$$$$$$$            /$$             /$$     /$$                              ");
        System.out.println("| $$__  $$          | $$            | $$    |__/                              ");
        System.out.println("| $$  \\ $$  /$$$$$$ | $$  /$$$$$$  /$$$$$$   /$$  /$$$$$$  /$$$$$$$   /$$$$$$$");
        System.out.println("| $$$$$$$/ /$$__  $$| $$ |____  $$|_  $$_/  | $$ /$$__  $$| $$__  $$ /$$_____/");
        System.out.println("| $$__  $$| $$$$$$$$| $$  /$$$$$$$  | $$    | $$| $$  \\ $$| $$  \\ $$|  $$$$$$ ");
        System.out.println("| $$  \\ $$| $$_____/| $$ /$$__  $$  | $$ /$$| $$| $$  | $$| $$  | $$ \\____  $$");
        System.out.println("| $$  | $$|  $$$$$$$| $$|  $$$$$$$  |  $$$$/| $$|  $$$$$$/| $$  | $$ /$$$$$$$/");
        System.out.println("|__/  |__/ \\_______/|__/ \\_______/   \\___/  |__/ \\______/ |__/  |__/|_______/ ");


        System.out.println("\n\n");

        System.out.println(" /$$$$$$$  /$$                     /$$                              ");
        System.out.println("| $$__  $$|__/                    |__/                              ");
        System.out.println("| $$  \\ $$ /$$ /$$$$$$$   /$$$$$$  /$$  /$$$$$$   /$$$$$$   /$$$$$$$");
        System.out.println("| $$$$$$$ | $$| $$__  $$ |____  $$| $$ /$$__  $$ /$$__  $$ /$$_____/");
        System.out.println("| $$__  $$| $$| $$  \\ $$  /$$$$$$$| $$| $$  \\__/| $$$$$$$$|  $$$$$$ ");
        System.out.println("| $$  \\ $$| $$| $$  | $$ /$$__  $$| $$| $$      | $$_____/ \\____  $$");
        System.out.println("| $$$$$$$/| $$| $$  | $$|  $$$$$$$| $$| $$      |  $$$$$$$ /$$$$$$$/");
        System.out.println("|_______/ |__/|__/  |__/ \\_______/|__/|__/       \\_______/|_______/ ");

        System.out.println("\n\n");


        System.out.println("Veuillez Saisir un entier positif");

        int nb = Ut.saisirEntier();
        while (nb <= 0) {
            System.out.println("Votre réponses n'est pas correcte. \nVeuillez Saisir un entier positif");
            nb = Ut.saisirEntier();
        }


        System.out.println("Veuillez Saisir un réel compris entre 0 et 1");

        double p = Ut.saisirDouble();
        while (p >= 1 || p <= 0) {
            System.out.println("Votre réponses n'est pas correcte. \nVeuillez Saisir un réel compris entre 0 et 1");
            p = Ut.saisirDouble();
        }


        RelationBinaire r0 = new RelationBinaire(nb);
        RelationBinaire r1 = new RelationBinaire(nb, true);
        RelationBinaire r2 = new RelationBinaire(nb, false);
        RelationBinaire r3 = new RelationBinaire(nb, p);
        RelationBinaire r4 = new RelationBinaire(nb, 1.0); // <== comme ça // pleine avec quel constructeur ?

        // on construit un tableau des relations binaires pour faciliter l'affichage
        RelationBinaire[] ensRelation = {r0, r1, r2, r3, r4};


        for (int i = 0; i < 4; i++) {
            System.out.println("\n\n ---\n\n");
            System.out.println("Relation Binaire n°" + (i + 1) + "\n");
            ensRelation[i].afficheDivers();
        }


    }


} // fin MainRelationBinaire
