/**
 * Interface définissant les capacités de soin d'une entité.
 * Permet de soigner n'importe quel objet qui l'implémente (pas seulement des canards).
 */
public interface Soignable {
    void soigner();
    int getPvActuels();
    int getPvMax();
    
    /**
     * Méthode par défaut permettant de calculer le pourcentage de vie restant.
     * Accessible automatiquement par toutes les classes implémentant Soignable.
     */
    default double pourcentagePV() {
        return (double) getPvActuels() / getPvMax() * 100;
    }
}

/**
 * Interface définissant les actions de base d'un combattant dans l'arène.
 */
public interface Combattant {
    void attaquer(CanardDeCombat cible);
    boolean estKO();
    String getNom();
}

/**
 * Classe de base abstraite représentant tous les canards de l'arène.
 * Elle centralise les statistiques, la gestion des dégâts et le système de combat de base.
 */
public abstract class CanardDeCombat implements Soignable, Combattant {

    /**
     * Énumération des différents éléments du jeu.
     */
    public enum TypeElementaire {
        FEU, EAU, PLANTE, NORMAL;
        
        // Formate l'affichage 
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    // Statistiques de base du canard
    private final String nom;
    private String surnom;
    private final int pvMax;
    private int pvActuels;
    private int atk;
    
    // Compteur statique pour suivre le nombre total de canards instanciés
    private static int nbCanardsCrees = 0;
    public static final int ATK_MIN = 1;

    /**
     * Constructeur principal du CanardDeCombat.
     */
    public CanardDeCombat(String nom, int pvMax, int atk) {
        // Sécurisation des données entrantes
        if (pvMax <= 0) throw new IllegalArgumentException("Les points de vie doivent être > 0.");
        if (atk < ATK_MIN) throw new IllegalArgumentException("L'attaque doit être au moins de " + ATK_MIN);
        
        this.nom = nom;
        this.pvMax = pvMax;
        this.atk = atk;
        this.pvActuels = pvMax;
        this.surnom = nom; // Le surnom est par défaut égal au nom
        
        nbCanardsCrees++; // Incrémente le compteur global à chaque création
    }

    //Getters et Setters 
    
    @Override
    public String getNom() { return nom; }
    
    public String getSurnom() { return surnom; }
    
    @Override
    public int getPvMax() { return pvMax; }
    
    @Override
    public int getPvActuels() { return pvActuels; }
    
    public int getAtk() { return atk; }
    
    public static int getNbCanardsCrees() { return nbCanardsCrees; }
    
    public void setSurnom(String surnom) { this.surnom = surnom; }
    
    @Override
    public boolean estKO() { return this.pvActuels <= 0; }
    
    /**
     * Méthode abstraite forçant chaque sous-classe à définir son propre type élémentaire.
     */
    public abstract TypeElementaire getType();

    /**
     * Réduit les PV du canard en fonction des dégâts reçus et gère la limite basse (0 PV).
     */
    public void subirDegats(int degats) {
        if (degats < 0) degats = 0; // Empêche les dégâts négatifs (qui soigneraient)
        this.pvActuels -= degats;
        if (this.pvActuels < 0) this.pvActuels = 0; // Un canard ne peut pas avoir moins de 0 PV
        System.out.println(this.surnom + " subit " + degats + " dégâts ! (PV: " + this.pvActuels + "/" + this.pvMax + ")");
    }

    @Override
    public void soigner() {
        this.pvActuels = this.pvMax;
        System.out.println(this.surnom + " a été soigné et récupère tous ses PV !");
    }

    // Double Dispatch
    
    public double etreAttaqueePar(CanardFeu attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardEau attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardPlante attaquant) { return 1.0; }
    public double etreAttaqueePar(CanardClassique attaquant) { return 1.0; }

    /**
     * Logique commune d'attaque après calcul du multiplicateur élémentaire.
     */
    protected void effectuerAttaque(CanardDeCombat cible, double mult) {
        int degats = (int)(getAtk() * mult);
        System.out.println(getSurnom() + " attaque " + cible.getSurnom() 
            + " ! (" + getType() + " → " + cible.getType() 
            + " : x" + mult + ") → " + degats + " dégâts");
        cible.subirDegats(degats);
    }

    /**
     * L'attaque brute est abstraite, car chaque canard doit l'implémenter pour 
     * passer son propre type statique (`this`) au système de Double Dispatch.
     */
    @Override
    public abstract void attaquer(CanardDeCombat cible);
    
    /**
     * Méthode appelée à la fin de chaque tour de combat.
     * Vide par défaut, redéfinie dans les classes nécessitant un effet persistant.
     */
    public void finDeTour() {}
}