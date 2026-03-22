public abstract class CanardDeCombat {
    
    
    private final String nom;
    private String surnom;
    private final int pvMax;
    private int pvActuels;
    private int atk;
    
    private static int nbCanardsCrees = 0;
    public static final int ATK_MIN = 1;

  
    public CanardDeCombat(String nom, int pvMax, int atk) {
        // Validation des paramètres
        if (pvMax <= 0) {
            throw new IllegalArgumentException("Les points de vie (pvMax) doivent être strictement positifs.");
        }
        if (atk < ATK_MIN) {
            throw new IllegalArgumentException("L'attaque (atk) doit être au moins de " + ATK_MIN + ".");
        }
        
        this.nom = nom;
        this.pvMax = pvMax;
        this.atk = atk;
        
        
        this.pvActuels = pvMax;
        this.surnom = nom; 
        
        
        nbCanardsCrees++;
    }

    
    public String getNom() { return nom; }
    public String getSurnom() { return surnom; }
    public int getPvMax() { return pvMax; }
    public int getPvActuels() { return pvActuels; }
    public int getAtk() { return atk; }
    
    public static int getNbCanardsCrees() { return nbCanardsCrees; }

    
    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }
    public boolean estKO() {
        return this.pvActuels <= 0;
    }

    public void subirDegats(int degats) {
        if (degats < 0) {
            degats = 0; // On évite que des dégâts négatifs soignent le canard
        }
        
        this.pvActuels -= degats;
        
        // Les PV ne peuvent pas descendre en dessous de 0
        if (this.pvActuels < 0) {
            this.pvActuels = 0;
        }
        
        System.out.println(this.surnom + " subit " + degats + " dégâts ! (PV: " + this.pvActuels + "/" + this.pvMax + ")");
    }

    public void soigner() {
        this.pvActuels = this.pvMax;
    }
    protected void gagnerPv(int soin) {
        this.pvActuels += soin;
        if (this.pvActuels > this.pvMax) {
            this.pvActuels = this.pvMax;
        }
    }

    @Override
    public String toString() {
        // Ex: "[Feu] Canard Flamme «Gérard» (PV: 39/39 | ATK: 52)"
        return "[" + this.getType() + "] " + this.nom + " «" + this.surnom + "» (PV: " + this.pvActuels + "/" + this.pvMax + " | ATK: " + this.atk + ")";
    }

    //  Méthode abstraite
    public abstract String getType();

    //  Méthode concrète de combat 
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO()) {
            System.out.println(this.surnom + " est KO et ne peut pas attaquer.");
            return;
        }
        if (cible.estKO()) {
            System.out.println(cible.getSurnom() + " est déjà KO !");
            return;
        }

        System.out.println(this.surnom + " attaque " + cible.getSurnom() + " !");

        // Calcul du multiplicateur de type
        double multiplicateur = calculerMultiplicateurType(this.getType(), cible.getType());
        int degatsFinaux = (int) (this.atk * multiplicateur);

        // Affichage du déroulement selon l'efficacité
        if (multiplicateur > 1.0) {
            System.out.println("C'est super efficace !");
        } else if (multiplicateur < 1.0 && multiplicateur > 0) {
            System.out.println("Ce n'est pas très efficace...");
        } else if (multiplicateur == 0) {
            System.out.println("Cela n'a aucun effet...");
        }

        // Application des dégâts
        cible.subirDegats(degatsFinaux);
    }

    // Méthode utilitaire 
   protected double calculerMultiplicateurType(String typeAttaquant, String typeCible) {
    // type  Feu
    if (typeAttaquant.equals("Feu") && typeCible.equals("Plante")) return 2.0;
    if (typeAttaquant.equals("Feu") && typeCible.equals("Eau")) return 0.5;
    if (typeAttaquant.equals("Feu") && typeCible.equals("Feu")) return 0.5;

    // type Eau
    if (typeAttaquant.equals("Eau") && typeCible.equals("Feu")) return 2.0;
    if (typeAttaquant.equals("Eau") && typeCible.equals("Plante")) return 0.5;
    if (typeAttaquant.equals("Eau") && typeCible.equals("Eau")) return 0.5;

    // type Plante
    if (typeAttaquant.equals("Plante") && typeCible.equals("Eau")) return 2.0;
    if (typeAttaquant.equals("Plante") && typeCible.equals("Feu")) return 0.5;
    if (typeAttaquant.equals("Plante") && typeCible.equals("Plante")) return 0.5;

    //type Normal
    return 1.0; 
}
}