import java.util.*;
import java.lang.*;

public class MainRelationBinaire {

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

        System.out.println("Bonjour et bienvenue dans le programme de test de la classe RelationBinaire avec extensions !\n\n");

        // Modifier ici 
        boolean[][] relation = {{false, true, true}, {false, false, true}, {false, false, false}};
        RelationBinaire r = new RelationBinaire(relation);


        Ut.pause(1000);

        System.out.print("Souhaiterez-vous définir vous même la relation binaire, ou la générer aléatoirement ? (Choix 1 ou ~) : ");

        String currentRepUser = Ut.saisirChaine();

        if(currentRepUser.equals("1")){
            System.out.println("Modifiez directement la relationBinaire dans le code source si vous voulez définir vous même la relation.");
        }
        else{
            r = new RelationBinaire(Ut.randomMinMax(1, 10), (double)(Ut.randomMinMax(1, 10))/10.0);
        }

        boolean continuer = true;
        
        System.out.println("Voici la relation sur laquelle nous allons travailler :\n" + r.toString() + "\n");

        while(continuer){

            String choix = menu();
            Ut.pause(500);
            System.out.println("\nTrès bon choix !");
            Ut.pause(500);
            switch(choix){
                case "1":
                    Ut.pause(500);
                    System.out.println("Vous avez choisi l'extension Descendant :\n");
                    System.out.println("Selon un point donné, la fonction revoie tous ses descendants dans la relation binaire."); 
                    System.out.println("Quel point voulez-vous tester ? : ");
                    System.out.println("\nVoici tous les descendants de votre point : " + r.descendant(Ut.saisirEntier()));
                    Ut.saisirChaine();
                    break;
                case "2":
                    System.out.println("Vous avez choisi l'extension baseBis :\n");
                    System.out.println("Pour tester cette fonction, chaque fonction bis va être exécutée à la suite. Appuyez sur entrée pour continuer.");
                    Ut.saisirChaine();
                    System.out.println("avecBouclesBis :" + r.avecBouclesBis());
                    Ut.saisirChaine();
                    System.out.println("sansBouclesBis :" + r.sansBouclesBis());
                    Ut.saisirChaine();
                    System.out.println("unionBis avec la matrice identitée :" + r.unionBis(new RelationBinaire(r.getLongeur(), true)));
                    Ut.saisirChaine();
                    System.out.println("IntersectionBis avec la matrice identitée :" + r.intersectionBis(new RelationBinaire(r.getLongeur(), true)));
                    Ut.saisirChaine();
                    System.out.println("ComplementaireBis :" + r.complementaireBis());
                    Ut.saisirChaine();
                    System.out.println("DifferenceBis avec la matrice identitée :" + r.differenceBis(new RelationBinaire(r.getLongeur(), true)));
                    Ut.saisirChaine();
                    System.out.println("EstIncluseBis avec la matrice identitée :" + r.estIncluseBis(new RelationBinaire(r.getLongeur(), true)));
                    Ut.saisirChaine();
                    System.out.println("EstEgaleBis avec la matrice identitée :" + r.estEgaleBis(new RelationBinaire(r.getLongeur(), true)));
                    Ut.saisirChaine();
                    System.out.println("succBis du point 1 :" + r.succBis(1));
                    Ut.saisirChaine();
                    System.out.println("predBis du point 1 :" + r.predBis(1));
                    Ut.saisirChaine();
                    System.out.println("estReflexiveBis :" + r.estReflexiveBis());
                    Ut.saisirChaine();
                    System.out.println("estAntiReflexiveBis :" + r.estAntireflexiveBis());
                    Ut.saisirChaine();
                    System.out.println("estSymetriqueBis :" + r.estSymetriqueBis());
                    Ut.saisirChaine();
                    System.out.println("estAntiSymetriqueBis :" + r.estAntisymetriqueBis());
                    Ut.saisirChaine();
                    System.out.println("estTransitiveBis :" + r.estTransitiveBis());
                    Ut.saisirChaine();
                    System.out.println("estRelOrdreBis :" + r.estRelOrdreBis());
                    Ut.saisirChaine();
                    System.out.println("hasseBis :" + r.hasseBis());
                    Ut.saisirChaine();
                    System.out.println("ferTransBis :" + r.ferTransBis());
                    Ut.saisirChaine();
                    break;
                case "3":
                    System.out.println("Vous avez choisi l'extension de l'interface graphique.");
                    System.out.println("Pour accéder, vous devez lancer le programme Interface_graphique/Main.java");
                    Ut.saisirChaine();
                    break;
                case "4":
                    System.out.println("Vous avez choisi l'extension CN d'une relation d'ordre.");
                    System.out.println("Cela ne dépend pas de la relation précédente, mais uniquement d'une condition que nous avions posé.");
                    System.out.println("Pour faire des tests aléatoires, entrez deux nombres, le premier étant le nombre de relations, et le second le cardinal maximal. (cela peut prendre un certain temps)");
                    System.out.println("\n\nLa fonction a renvoyé : " + r.verifCNordre(Ut.saisirEntier(), Ut.saisirEntier()));
                    Ut.saisirChaine();
                    break;
                case "5":
                    System.out.println("Vous avez choisi l'extension fermeture ordonnée.");
                    System.out.println("Voici la fermeture ordonnée de la relation : " + r.ferOrdonnee());
                    Ut.saisirChaine();
                    break;
                case "6":
                    System.out.println("Vous avez choisi l'extension niveaux.");
                    System.out.println("Les niveaux affichent dans un tableau d'ensemble, les points se situant respectivement au niveau 0, 1, 2, 3, etc.");
                    System.out.println("ATTENTION: Sur des très grandes relations, cela peut ne pas marcher.");
                    RelationBinaire rHasse = r.hasse();
                    System.out.println("Voici le diagramme de Hasse de notre relation :" + rHasse.toString());
                    System.out.println("Voici les niveaux de notre relation : ");
                    EE[] res = rHasse.seqNiveau().clone();
                    for(int i = 0; i < res.length; i++){
                        System.out.println("Niveau " + i + " : " + res[i]);
                    }
                    Ut.saisirChaine();
                    break;
                default:
                    continuer = false;
                    break;
            }
        }
        

    }

    public static String menu(){
        System.out.println("-- Menu de navagation --");
        System.out.println("1 - Descendant");
        System.out.println("2 - Toutes les fonctions bis");
        System.out.println("3 - Interface graphique");
        System.out.println("4 - CN d'une relation d'ordre");
        System.out.println("5 - fermeture ordonnée");
        System.out.println("6 - niveaux");
        System.out.println("Insérez autre chose pour quitter");
        System.out.print("\nSur quoi vous lancez-vous ? : ");
        return Ut.saisirChaine();
    }
} // fin MainRelationBinaire
