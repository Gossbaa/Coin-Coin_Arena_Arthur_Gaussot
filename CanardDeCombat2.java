public abstract class CanardDeCombat {

    public enum TypeElementaire {
        FEU, EAU, PLANTE, NORMAL;
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
        if (pvMax <= 0) throw new IllegalArgumentException("Les points de vie doivent être > 0.");
        if (atk < ATK_MIN) throw new IllegalArgumentException("L'attaque doit être au moins de " + ATK_MIN);
        
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
    public abstract TypeElementaire getType();

    public void subirDegats(int degats) {
        if (degats < 0) degats = 0;
        this.pvActuels -= degats;
        if (this.pvActuels < 0) this.pvActuels = 0;
        System.out.println(this.surnom + " subit " + degats + " dégâts ! (PV: " + this.pvActuels + "/" + this.pvMax + ")");
    }

    //Double Dispatch
    public double etreAttaqueePar(CanardFeu attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardEau attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardPlante attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardClassique attaquant) { return 1.0; }

    
    protected void effectuerAttaque(CanardDeCombat cible, double mult) {
        int degats = (int)(getAtk() * mult);
        System.out.println(getSurnom() + " attaque " + cible.getSurnom() 
            + " ! (" + getType() + " → " + cible.getType() 
            + " : x" + mult + ") → " + degats + " dégâts");
        cible.subirDegats(degats);
    }

    
    public abstract void attaquer(CanardDeCombat cible);
}