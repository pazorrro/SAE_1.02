import java.lang.ref.Cleaner;
import java.util.Random;

public class RelationBinaire {

    // attributs
    //DERNIERE MODIF GH 16/12

    private int n;           // n > 0, E = {0,1,2, ..., n-1}
    private boolean[][] matAdj;  // matrice d'adjacence de R
    private int m;           // cardinal de R
    private EE[] tabSucc;    // tableau des ensembles de successeurs


    // constructeurs

    /**
     * pré-requis : nb > 0
     * action : construit la relation binaire vide dans l'ensemble {0,1,2, ..., nb-1}
     */
    public RelationBinaire(int nb) {
        this.n = nb;
        this.matAdj = new boolean[nb][nb]; // Nous partons du principe que la matrice est de taille maximale (n*n);
        this.m = 0;
        this.tabSucc = new EE[nb]; // de même, nous partons du principe que le tableau a au maximum n successeurs
        for (int i = 0; i < nb; i++) {
            EE ee = new EE(nb);
            tabSucc[i] = ee;
        }
    }

    //______________________________________________


    /**
     * pré-requis : nb > 0 et 0 <= p <= 1
     * action : construit une relation binaire aléatoire dans l'ensemble {0,1,2, ..., nb-1}
     * à laquelle chaque couple a la probabilité p d'appartenir.
     * En particulier, construit la relation vide si p = 0 et la relation pleine si p = 1.
     * Indication : Math.random() retourne un réel de type double aléatoire de l'intervalle [0,1[
     */
    public RelationBinaire(int nb, double p) {
        this(nb);
        // construction
        for (int i = 0; i < nb; i++) {
            this.tabSucc[i] = new EE(nb);
            for (int j = 0; j < nb; j++) {
                if (Math.random() < p) { // la matrice étant déclarée à false, nous ajoutons aux rangs i, j la valeur true si la probabilité est inférieure à p (plus p est grand, plus la probabilité est grande)
                    this.matAdj[i][j] = true;
                    this.tabSucc[i].ajoutPratique(j);
                    this.m++;
                }
            }
        }

    }

    //______________________________________________


    /**
     * pré-requis : nb > 0
     * action : construit la relation binaire dans l'ensemble {0,1,2, ..., nb-1}
     * '=' si egal a la valeur vrai et '<=' sinon
     */
    public RelationBinaire(int nb, boolean egal) { // Pas sûr, mais je pense que c'est bon
        // Construit une matrice identidée (avec des 1 en diagonal seulement) si egal est vrai, sinon une "matrice triangulaire inférieure" (les éléments en dessous de la diagonale sont à true)
        this(nb);
        if (egal == true) {
            for (int i = 0; i < nb; i++) {
                this.matAdj[i][i] = true;
                this.m++;
            }
        } else {
            for (int i = 0; i < nb; i++) {
                for (int j = 0; j < nb; j++) {
                    if (i <= j) {
                        this.matAdj[i][j] = true;
                        this.m++;
                    }
                }
            }
        }
        // construction de tabSucc
        for (
                int i = 0;
                i < nb; i++) {
            this.tabSucc[i] = new EE(nb);
            for (int j = 0; j < nb; j++) {
                if (this.matAdj[i][j] == true) {
                    this.tabSucc[i].ajoutElt(j);
                }
            }
        }

    }

//______________________________________________


    /**
     * pré-requis : mat est une matrice carrée de dimension > 0
     * action : construit une relation binaire dont la matrice d'adjacence
     * est une copie de mat
     * //MODIF
     */
    public RelationBinaire(boolean[][] mat) { // plus efficace avec le constructeur de EE(EE)
        this(mat.length);

        for (int i = 0; i < n; i++) {
            this.tabSucc[i] = new EE(n);
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == true) {
                    this.matAdj[i][j] = true;
                    this.m++;
                    this.tabSucc[i].ajoutElt(j);
                }
            }
        }
    }

//______________________________________________


    /**
     * pré-requis : tab.length > 0 et pour tout i, les éléments de tab[i]
     * sont compris entre 0 et tab.length-1
     * action : construit une relation binaire dont le tableau des ensembles de successeurs
     * est une copie de tab
     */
    public RelationBinaire(EE[] tab) {
        this(tab.length);

        // construction de matAdj
        for (int i = 0; i < n; i++) {
            this.tabSucc[i] = new EE(tab[i]);
            for (int j = 0; j < n; j++) {
                this.matAdj[i][j] = tab[i].contient(j);
            }
        }
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * action : construit une copie de r
     */
    public RelationBinaire(RelationBinaire r) {
        this(r.n);

        // construction de matAdj
        for (int i = 0; i < n; i++) {
            this.tabSucc[i] = new EE(r.tabSucc[i]);
            for (int j = 0; j < n; j++) {
                this.matAdj[i][j] = r.matAdj[i][j];
            }
        }
    }


//______________________________________________


// méthodes


    /**
     * pré-requis : aucun
     * résultat : une chaîne de caractères permettant d'afficher this par sa matrice d'adjacence
     * contenant des '0' et des '1' (plus lisibles que des 'V' et des 'F') et sa définition
     * en extension (ensemble de couples {(..,..),(..,..), ...})
     */
    public String toString() {
        String s = "\nmatrice d'adjacence : \n\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.matAdj[i][j] == true) {
                    s += "1 ";
                } else {
                    s += "0 ";
                }
            }
            s += "\n";
        }
        s += "\ndéfinition en extension : \n\n{";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.matAdj[i][j] == true) {
                    s += "(" + i + "," + j + "),";
                }
            }
        }
        if (s.charAt(s.length() - 1) != '{') {
            s = s.substring(0, s.length() - 1);
            s += "}";
        } else {
            s = s.substring(0, s.length() - 1);
            s += "∅";
        }


        return s;
    }

//______________________________________________


// A) Logique et calcul matriciel
//-------------------------------


    /**
     * pré-requis : m1 et m2 sont des matrices carrées de même dimension et 1 <= numConnecteur <= 5
     * résultat : la matrice obtenue en appliquant terme à terme le  connecteur de numéro numConnecteur
     * sur m1 si numConnecteur  = 3 (dans ce cas le paramètre m2 n'est pas utilisé),
     * et sur m1 et m2 dans cet ordre sinon, sachant que les connecteurs "ou","et","non",
     * "implique"et "equivalent" sont numérotés de 1 à 5 dans cet ordre
     */

    public static boolean[][] opBool(boolean[][] m1, boolean[][] m2, int numConnecteur) {

        boolean[][] matrice = new boolean[m1.length][m1.length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                if (numConnecteur == 1) { // ou
                    matrice[i][j] = (m1[i][j] || m2[i][j]);
                } else if (numConnecteur == 2) { // et
                    matrice[i][j] = (m1[i][j] && m2[i][j]);
                } else if (numConnecteur == 3) { // non m1
                    matrice[i][j] = !m1[i][j];
                } else if (numConnecteur == 4) { // implique
                    if (m1[i][j] == true && m2[i][j] == false) {
                        matrice[i][j] = false;
                    } else matrice[i][j] = true;
                } else if (numConnecteur == 5) { // equivalance
                    if (m1[i][j] == m2[i][j]) {
                        matrice[i][j] = true;
                    } else matrice[i][j] = false;
                }

            }

        }


        return matrice;

    }

//______________________________________________


    /**
     * pré-requis : m1 et m2 sont des matrices carrées de même dimension
     * résultat : le produit matriciel de m1 et m2
     */
    public static boolean[][] produit(boolean[][] m1, boolean[][] m2) {

        boolean[][] matrice = new boolean[m1.length][m1.length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {

                for (int k = 0; k < m1.length; k++) {
                    boolean[] m3 = new boolean[m1.length];
                    matrice[i][j] = matrice[i][j] || m1[i][k] && m2[k][j];
                }
            }
        }
        return matrice;
    }

//______________________________________________


    /**
     * pré-requis : m est une matrice carrée
     * résultat : la matrice transposée de m
     */
    public static boolean[][] transposee(boolean[][] m) {

        boolean[][] matrice = new boolean[m.length][m.length];

        for (int i = 0; i < m.length; i++) {

            for (int j = 0; j < m.length; j++) {
                matrice[i][j] = m[j][i];
            }

        }

        return matrice;

    }

//______________________________________________


// B) Théorie des ensembles
//--------------------------


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est vide
     */
    public boolean estVide() {
        for (int i = 0; i < tabSucc.length; i++) {
            if (tabSucc[i].getCardinal() != 0) return false;
        }
        return true;
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est pleinee (contient tous les couples d'éléments de E)
     */
    public boolean estPleine() {
        return this.m == this.n * this.n;
    }

//______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi (x,y) appartient à this
     */
    public boolean appartient(int x, int y) {
        return this.matAdj[x][y];
    }

//______________________________________________


    /**
     * pré-requis : 0 <= x < this.n et 0 <= y < this.n
     * résultat : ajoute (x,y) à this s'il n'y est pas déjà
     */
    public void ajouteCouple(int x, int y) {
        if (!this.appartient(x, y)) {
            this.matAdj[x][y] = true;
            this.tabSucc[x].ajoutElt(y);
            this.m++;
        }
    }

//______________________________________________


    /**
     * pré-requis : 0 <= x < this.n et 0 <= y < this.n
     * résultat : enlève (x,y) de this s'il y est
     */
    public void enleveCouple(int x, int y) {
        if (this.appartient(x, y)) {
            this.matAdj[x][y] = false;
            this.tabSucc = new RelationBinaire(this.matAdj).tabSucc; // utilise le constructeur pour réadapter notre tableau de successeurs actuel
        }
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : une nouvelle relation binaire obtenue à partir de this en ajoutant
     * les couples de la forme  (x,x) qui n'y sont pas déjà
     */
    public RelationBinaire avecBoucles() {
        RelationBinaire r = new RelationBinaire(this.matAdj);
        for (int i = 0; i < this.n; i++) {
            r.ajouteCouple(i, i);
        }
        return r;
    }

    public RelationBinaire avecBouclesBis() {
        return new RelationBinaire(opBool(this.matAdj, new RelationBinaire(this.n, true).matAdj, 1));
    }
//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : une nouvelle relation binaire obtenue à partir de this en enlèvant
     * les couples de la forme  (x,x) qui y sont
     * //DERNIERE MODIF
     */
    public RelationBinaire sansBoucles() {
        RelationBinaire r = new RelationBinaire(this.matAdj);
        for (int i = 0; i < this.n; i++) {
            r.enleveCouple(i, i);
        }
        return r;
    }

    public RelationBinaire sansBouclesBis() { // fonction à faire
        RelationBinaire r1 = new RelationBinaire(opBool(new RelationBinaire(n, true).matAdj, new RelationBinaire(n, true).matAdj, 3));
        return new RelationBinaire(opBool(this.matAdj, r1.matAdj, 2));
    }


//______________________________________________


    /**
     * pré-requis : this.n = r.n
     * résultat : l'union de this et r
     */
    public RelationBinaire union(RelationBinaire r) {
        RelationBinaire r1 = new RelationBinaire(this);
        for (int i = 0; i < n; i++) {
            r1.tabSucc[i] = r1.tabSucc[i].union(r.tabSucc[i]);
        }
        return new RelationBinaire(r1.tabSucc);
    }

    public RelationBinaire unionBis(RelationBinaire r) {
        return new RelationBinaire((opBool(this.matAdj, r.matAdj, 1)));
    }

//______________________________________________


    /**
     * pré-requis : this.n = r.n
     * résultat : l'intersection de this et r
     */
    public RelationBinaire intersection(RelationBinaire r) {
        RelationBinaire r1 = new RelationBinaire(this);
        for (int i = 0; i < n; i++) {
            r1.tabSucc[i] = r1.tabSucc[i].intersection(r.tabSucc[i]);
        }

        return new RelationBinaire(r1.tabSucc);
    }

    public RelationBinaire intersectionBis(RelationBinaire r) {
        return new RelationBinaire((opBool(this.matAdj, r.matAdj, 2)));
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : la relation complémentaire de this
     */

    public RelationBinaire complementaire() {

        RelationBinaire r1 = new RelationBinaire(this);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                r1.matAdj[i][j] = !this.matAdj[i][j];
            }
        }
        return r1;
    }

    public RelationBinaire complementaireBis() {

        RelationBinaire r1 = new RelationBinaire(this);
        r1.matAdj = opBool(r1.matAdj, r1.matAdj, 3); // on utilise la fonction opBool qui nous donne la négation
        r1.tabSucc = new RelationBinaire(r1).tabSucc; // on créé les tabsucc grâce aux constructeurs

        return r1;
    }


//______________________________________________


    /**
     * pré-requis : this.n = r.n
     * résultat : la différence de this et r
     */
    public RelationBinaire difference(RelationBinaire r) {

        RelationBinaire r1 = new RelationBinaire(this);
        for (int i = 0; i < this.n; i++) {
            r1.tabSucc[i] = new EE(this.tabSucc[i].difference(r.tabSucc[i]));
        }
        return new RelationBinaire(r1.tabSucc);
    }

    public RelationBinaire differenceBis(RelationBinaire r) {
        RelationBinaire r1 = new RelationBinaire(opBool(r.matAdj, r.matAdj, 3));
        return new RelationBinaire(opBool(this.matAdj, r1.matAdj, 2));
    }

//______________________________________________


    /**
     * pré-requis : this.n = r.n
     * résultat : vrai ssi this est incluse dans r
     */
    public boolean estIncluse(RelationBinaire r) {
        for (int i = 0; i < this.n; i++) {
            if (!tabSucc[i].estInclus(r.tabSucc[i])) return false;
        }
        return true;
    }

    public boolean estIncluseBis(RelationBinaire r) {
        RelationBinaire r1 = new RelationBinaire(opBool(this.matAdj, r.matAdj, 2));
        RelationBinaire r2 = new RelationBinaire(opBool(r1.matAdj, r1.matAdj, 3));
        return (new RelationBinaire(opBool(r1.matAdj, r2.matAdj, 2)).estVide());
    }

//______________________________________________


    /**
     * pré-requis : this.n = r.n
     * résultat : vrai ssi this est égale à r
     */
    public boolean estEgale(RelationBinaire r) {
        for (int i = 0; i < this.n; i++) {
            if (!tabSucc[i].estEgal(r.tabSucc[i])) return false;
        }
        return true;
    }

    public boolean estEgaleBis(RelationBinaire r) {
        RelationBinaire r1 = new RelationBinaire(opBool(r.matAdj, r.matAdj, 3));
        return new RelationBinaire(opBool(this.matAdj, r1.matAdj, 2)).estVide();
    }


//______________________________________________


// C) Théorie des graphes orientés
//---------------------------------

    /**
     * pré-requis : 0 <= x < this.n
     * résultat : l'ensemble des successeurs de x dans this, "indépendant"
     * (c'est-à-dire dans une autre zône mémoire) de l'attribut this.tabSucc
     */
    public EE succ(int x) {
        return new EE(this.tabSucc[x]);
    }

    public EE succBis(int x) {
        return new EE(this.tabSucc[x]);
    } // on peut rien modifier si ?

//______________________________________________


    /**
     * pré-requis : 0 <= x < this.n
     * résultat : l'ensemble des prédécesseurs de x dans this
     */
    public EE pred(int x) {
        EE e = new EE(this.n);
        for (int i = 0; i < this.n; i++) {
            if (this.matAdj[i][x]) {
                e.ajoutElt(i);
            }
        }
        return e;
    }

//______________________________________________


// D) Relation binaire
//---------------------

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est réflexive
     */
    public boolean estReflexive() {
        for (int i = 0; i < matAdj.length; i++) {
            if (matAdj[i][i] == false) return false;
        }
        return true;
    }

    public boolean estReflexiveBis() {
        RelationBinaire r1 = new RelationBinaire(this.n, true);
        return r1.estIncluse(this);
    }


//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est antiréflexive
     */
    public boolean estAntireflexive() {
        for (int i = 0; i < matAdj.length; i++) {
            if (matAdj[i][i] == true) return false;
        }
        return true;
    }

    public boolean estAntireflexiveBis() {
        RelationBinaire r = new RelationBinaire(opBool(this.matAdj, this.matAdj, 3));
        RelationBinaire r1 = new RelationBinaire(this.n, true);
        return r1.estIncluse(r);
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est symétrique
     */
    public boolean estSymetrique() {
        for (int i = 0; i < matAdj.length; i++) {
            for (int j = 0; j < matAdj.length; j++) {
                if (matAdj[i][j] != matAdj[j][i]) return false;
            }
        }
        return true;
    }


    public boolean estSymetriqueBis() {
        return this.estEgale(new RelationBinaire(transposee(this.matAdj)));
    }


//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est antisymétrique
     */
    public boolean estAntisymetrique() {
        for (int i = 0; i < matAdj.length; i++) {
            for (int j = 0; j < matAdj.length; j++) {
                if (matAdj[i][j] && matAdj[j][i] && i != j) return false;
            }
        }
        return true;
    }

    public boolean estAntisymetriqueBis() {
        RelationBinaire r = new RelationBinaire(transposee(this.matAdj));
        RelationBinaire r1 = new RelationBinaire(opBool(this.matAdj, r.matAdj, 2));
        RelationBinaire r2 = r1.difference(new RelationBinaire(n, true));
        return r2.estVide();
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est transitive
     */
    public boolean estTransitive() {
        for (int i = 0; i < matAdj.length; i++) {
            for (int j = 0; j < matAdj.length; j++) {
                for (int k = 0; k < matAdj.length; k++) {
                    if (matAdj[i][j] && matAdj[j][k] && !matAdj[i][k]) return false;
                }
            }
        }
        return true;
    }

    public boolean estTransitiveBis() {
        return true;
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est une relation d'ordre
     */
    public boolean estRelOrdre() {
        return this.estReflexive() && this.estAntisymetrique() && this.estTransitive();
    }

    public boolean estRelOrdreBis() {
        return this.estReflexive() && this.estAntisymetrique() && this.estTransitive();
    }

//______________________________________________


    /**
     * pré-requis : aucun
     * résultat : la relation binaire associée au diagramme de Hasse de this
     */
    public RelationBinaire hasse() { 
        RelationBinaire r = new RelationBinaire(this); // crée une relation entièrement positive

        for(int i = 0; i<this.matAdj.length; i++) {r.matAdj[i][i] = false;} // retire les boucles (préalable nécessaire)

        for (int i = 0; i < this.n; i++) { // tous les constituants de this
            for(int j = 0; j<this.n; j++){ // recherche des successeurs de i
                if(this.sansBoucles().tabSucc[i].contient(j)){ // si j est un successeur de i
                    for(int k = 0; k<this.m; k++){ // recherche des successeurs de j
                        if(this.sansBoucles().tabSucc[j].contient(k) && this.sansBoucles().tabSucc[i].contient(k)){ // si k est un successeur de j mais aussi de i
                            r.matAdj[i][k] = false; // retire les éléments voulus de la relation
                        }
                    }
                }
            }
        }
        return new RelationBinaire(r.matAdj);
    }

    public RelationBinaire hasseBis() {
        return this;
    }


//______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la fermeture transitive de this
     */
    public RelationBinaire ferTrans() {
        RelationBinaire r = new RelationBinaire(this);

        for (int i = 0; i < tabSucc.length; i++) {
            EE totSucc = new EE(n);
            if (tabSucc[i] != null) {
                totSucc = totSucc.union(tabSucc[i]);
                for (int j = 0; j < totSucc.getCardinal(); j++) {
                    if (j < totSucc.getCardinal()) {
                        for (int k = 0; k < n; k++) {
                            EE successeur = new EE(tabSucc[k].getCardinal());
                            if (totSucc.contient(k)) {
                                successeur = succ(k);
                            }
                            totSucc = totSucc.union(successeur);
                        }
                    }
                }
                while (!totSucc.estVide()) {
                    r.tabSucc[i].ajoutElt(totSucc.retraitUnElt());
                }
            }
        }
        RelationBinaire finale = new RelationBinaire(r.tabSucc);
        return finale;
    }

//______________________________________________

    /**
     * pré-requis : aucun
     * action : affiche this sous 2 formes (matrice et ensemble de couples), puis affiche ses propriétés
     * (réflexive, ..., relation d'ordre) et les relations binaires suivantes obtenues à partir de this :
     * Hasse, fermeture transitive de Hasse et fermeture transitive de Hasse avec boucles (sous 2 formes aussi)
     */
    public void afficheDivers() {

        System.out.println(this.toString());

        int compteur = 0;

        String propriete = "";

        if (this.estReflexive() == true) {
            propriete += "- réflexive\n";
            compteur++;
        }

        if (this.estAntireflexive() == true) {
            propriete += "- antiréflexive\n";
            compteur++;
        }

        if (this.estSymetrique() == true) {
            propriete += "- symétrique\n";
            compteur++;
        }

        if (this.estAntisymetrique() == true) {
            propriete += "- antisymétrique\n";
            compteur++;
        }

        if (this.estTransitive() == true) {
            propriete += "- transitive\n";
            compteur++;
        }

        if (this.estRelOrdre() == true) {
            propriete += "- relation d'ordre\n";
            compteur++;
        }


        if (compteur == 0) System.out.println("Cette relation n'as aucune propriété.");
        else {
            System.out.println("\n\nCette relation a  " + compteur + " propriétés :\n");
            System.out.println(propriete);
        }

        // manque les hass
    }

    public void afficheDiversBis() {

        System.out.println(this.toString());

        int compteur = 0;

        String propriete = "";

        if (this.estReflexiveBis() == true) {
            propriete += "- réflexive\n";
            compteur++;
        }

        if (this.estAntireflexiveBis() == true) {
            propriete += "- antiréflexive\n";
            compteur++;
        }

        if (this.estSymetriqueBis() == true) {
            propriete += "- symétrique\n";
            compteur++;
        }

        if (this.estAntisymetriqueBis() == true) {
            propriete += "- antisymétrique\n";
            compteur++;
        }

        if (this.estTransitiveBis() == true) {
            propriete += "- transitive\n";
            compteur++;
        }

        if (this.estRelOrdreBis() == true) {
            propriete += "- relation d'ordre\n";
            compteur++;
        }


        if (compteur == 0) System.out.println("Cette relation n'as aucune propriété.");
        else {
            System.out.println("\n\nCette relation a  " + compteur + " propriétés :\n");
            System.out.println(propriete);
        }

        // manque les hass
    }

//______________________________________________

/////////////////////////////////
// Toutes les autres extensions//
/////////////////////////////////

    //
    // EXTENTION 1 - DESCENDANT
    //

    public EE descendant(int x) {
        EE total = new EE(n);
        EE todo = succ(x);

        while (!todo.estVide()) {
            int a = todo.retraitEltAleatoirement();
            total.ajoutPratique(a);
            EE nouv_succ = succ(a).difference(total.union(todo));
            todo = todo.union(nouv_succ);
        }
        return total;
    }


    //
    // EXTENTION 4 - C.N. d'une relation d'ordre
    //

    // faire 4 relation d'odre


    public static void verifier_avec_quatre() {

        boolean[][] m1 = {{true, true}, {false, true}};
        boolean[][] m2 = {{true, false, true}, {true, true, true}, {false, false, true}};

        boolean[][] m3 = {{true, false, true, false}, {false, true, true, true}, {false, false, true, false,}, {false, false, false, true}};
        boolean[][] m4 = {{true, false, false, false}, {true, true, true, true}, {true, false, true, true,}, {true, false, false, true}};

        RelationBinaire r1 = new RelationBinaire(m1);
        RelationBinaire r2 = new RelationBinaire(m2);
        RelationBinaire r3 = new RelationBinaire(m3);
        RelationBinaire r4 = new RelationBinaire(m4);

        RelationBinaire[] ens = {r1, r2, r3, r4};

        boolean verif = true;
        for (int i = 0; i < 4; i++) {
            System.out.println("Relation binaire " + i + " : ");
            System.out.println(ens[i].toString());
            if (!ens[i].ferTrans().estEgale(ens[i])) {
                System.out.println("Oupss");
                verif = false;
            } else System.out.println("Verifiée ! \n\n\n---\n\n");
        }
        if (verif) System.out.println("Toutes les vérificatinos sont ok.");
        else System.out.println("Toutes les vérifications ne sont pas ok.");
    }


    public static boolean verifCNordre(int nbRel, int cardMax) {
        boolean prouve = true;
        for (int i = 0; i < nbRel; i++) {
            System.out.println("Recherche de matrice relation d'odre en cours...");
            RelationBinaire r = new RelationBinaire(Ut.randomMinMax(2, cardMax), (double)(Ut.randomMinMax(0, 100)/100));
            while (!r.estRelOrdre()) {
                r = new RelationBinaire(Ut.randomMinMax(2, cardMax), (double)(Ut.randomMinMax(0, 100)/100));
            }
            r.union(r.avecBoucles());
            r.afficheDivers();
            if (!r.ferTrans().estEgale(r)) prouve = false;
        }
        return prouve;
    }


    //
    // EXTENTION 5 - Fermeture ordonnée
    //

    private boolean CSNOrdo(){
        return this.ferTrans().estAntisymetrique() && !this.estVide(); // vérifier si la fermeture transitive n'enlèvera pas le caractère antisymétrique de la relation
    }

    public static boolean verifCNSferOrdo(int nbRel, int cardMax){
        for(int i = 0; i<5; i++){
            // Si la relation obtenue après ferOrdonnee est vide
            RelationBinaire r = new RelationBinaire(Ut.randomMinMax(2, cardMax), (double)(Ut.randomMinMax(1, 100)/100.0));
            boolean prevCNS = r.CSNOrdo();
            System.out.println(r + "\nEst-elle ordonnable ? " + prevCNS);
            if (r.ferOrdonnee().estVide() == prevCNS) return false; // Si c'est vide alors qu'on a dit le contraire
            System.out.println("Effectivement, ça a marché !");
        }
        return true;
    }

    public void makeItReflexif(RelationBinaire r){
        r = r.avecBoucles();
    }

    public void makeItTransitif(RelationBinaire r){
        r.union(r.ferTrans());
    }

    public RelationBinaire ferOrdonnee(){ // renvoie une relation binaire si elle est ordonnée, sinon renvoie une relation binaire vide
        RelationBinaire r = new RelationBinaire(this);
        makeItReflexif(r);
        makeItTransitif(r);
        // Si on fait une fonction "MakeItAntisymétrique", ça demandera de réduire R, et du coup ce sera pas bon car 
        // la fermeture Ordonnée doit contenir R
        if(r.estAntisymetrique()){ // Donc si elle est antisymétrique, alors on y est parvenu (à savoir que makeItTransitif 
                                   // peut possiblement la rendre non-antisymétrique)
            return r;
        }
        else { // et là c'est le cas où on peut rien faire sans réduire R
            return new RelationBinaire(0, 0.0);
        }
    }

    //
    // EXTENTION 6 - niveaux
    //


    public static void main(String[] args) {

        boolean test = verifCNSferOrdo(1000, 4);
        while (test != false){
            test = verifCNSferOrdo(1, 4);
        }

    }


} // fin RelationBinaire