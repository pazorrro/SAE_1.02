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

        System.out.println("Bonjour et bienvenue dans le programme de test de la classe RelationBinaire !\n\n");
        System.out.println("A l'issue de la demande de saisie, vous aurez 5 relations binaires à disposition, \n");
        System.out.println("R0 vide, R1= '=', R2 = '<=', R3 une relation binaire dépendant de la probabilité qui va suivre, \n");
        System.out.println("puis nous afficherons les propriétés de chacunes. \n");

        System.out.print("Saisissez un entier positif pour la taille de votre relation binaire : ");

        int nb = Ut.saisirEntier();
        while (nb <= 0) {
            System.out.print("\nVotre réponses n'est pas correcte. \nSaisissez un entier positif pour la taille de votre relation binaire : ");
            nb = Ut.saisirEntier();
        }


        System.out.print("\nSaisissez maintenant la probabilité que chaque couple appartienne à votre relation binaire (entre 0 et 1 exclus): ");

        double p = Ut.saisirDouble();
        while (p >= 1 || p <= 0) {
            System.out.println("\nVotre réponses n'est pas correcte. \nSaisissez maintenant la probabilité que chaque couple appartienne à votre relation binaire (entre 0 et 1 exclus): ");
            p = Ut.saisirDouble();
        }


        RelationBinaire r0 = new RelationBinaire(nb);
        RelationBinaire r1 = new RelationBinaire(nb, true);
        RelationBinaire r2 = new RelationBinaire(nb, false);
        RelationBinaire r3 = new RelationBinaire(nb, p);
        RelationBinaire r4 = new RelationBinaire(nb, 1.0);

        // on construit un tableau des relations binaires pour faciliter l'affichage
        RelationBinaire[] ensRelation = {r0, r1, r2, r3, r4};


        for (int i = 0; i < 4; i++) {
            System.out.println("\n\n --- $$ --- $$ --- $$ --- $$ --- \n\n");
            System.out.println("Relation Binaire n°" + (i + 1) + "\n");
            ensRelation[i].afficheDivers();
        }


    }


} // fin MainRelationBinaire
