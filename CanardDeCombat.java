public abstract class CanardDeCombat {

   
    public enum TypeElementaire {
        FEU, EAU, PLANTE, NORMAL;

        
        private static final double[][] TABLE_TYPES = {
            {0.5, 0.5, 2.0, 1.0}, // Attaquant FEU
            {2.0, 0.5, 0.5, 1.0}, // Attaquant EAU
            {0.5, 2.0, 0.5, 1.0}, // Attaquant PLANTE
            {1.0, 1.0, 1.0, 1.0}  // Attaquant NORMAL
        };

        
        public double getMultiplicateur(TypeElementaire cible) {
            return TABLE_TYPES[this.ordinal()][cible.ordinal()];
        }

        
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

   
    private final String nom;
    private String surnom;
    private final int pvMax;
    private int pvActuels;
    private int atk;
    
    private static int nbCanardsCrees = 0;
    public static final int ATK_MIN = 1;

    public CanardDeCombat(String nom, int pvMax, int atk) {
        if (pvMax <= 0) throw new IllegalArgumentException("Les points de vie (pvMax) doivent être strictement positifs.");
        if (atk < ATK_MIN) throw new IllegalArgumentException("L'attaque (atk) doit être au moins de " + ATK_MIN + ".");
        
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
    public void setSurnom(String surnom) { this.surnom = surnom; }
    
    public boolean estKO() { return this.pvActuels <= 0; }

    public void subirDegats(int degats) {
        if (degats < 0) degats = 0;
        this.pvActuels -= degats;
        if (this.pvActuels < 0) this.pvActuels = 0;
        
        System.out.println(this.surnom + " subit " + degats + " dégâts ! (PV: " + this.pvActuels + "/" + this.pvMax + ")");
    }

    public void soigner() { this.pvActuels = this.pvMax; }

    protected void gagnerPv(int soin) {
        this.pvActuels += soin;
        if (this.pvActuels > this.pvMax) {
            this.pvActuels = this.pvMax;
        }
    }

    @Override
    public String toString() {
        // Le this.getType() appellera automatiquement le toString() de l'enum (ex: "Feu")
        return "[" + this.getType() + "] " + this.nom + " «" + this.surnom + "» (PV: " + this.pvActuels + "/" + this.pvMax + " | ATK: " + this.atk + ")";
    }

   
    public abstract TypeElementaire getType();

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

        // Utilisation de la nouvelle méthode basée sur l'énumération
        double multiplicateur = calculerMultiplicateurType(this.getType(), cible.getType());
        int degatsFinaux = (int) (this.atk * multiplicateur);

        if (multiplicateur > 1.0) {
            System.out.println("C'est super efficace !");
        } else if (multiplicateur < 1.0 && multiplicateur > 0) {
            System.out.println("Ce n'est pas très efficace...");
        } else if (multiplicateur == 0) {
            System.out.println("Cela n'a aucun effet...");
        }

        cible.subirDegats(degatsFinaux);
    }

    
    protected double calculerMultiplicateurType(TypeElementaire typeAttaquant, TypeElementaire typeCible) {
        return typeAttaquant.getMultiplicateur(typeCible);
    }
}