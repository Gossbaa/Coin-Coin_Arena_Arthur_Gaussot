/**
 * Classe représentant un canard de type feu, avec une attaque par flamme.
 * Hérite de CanardDeCombat et définit des multiplicateurs de dégâts pour les attaques élémentaires.
 */
public class CanardFeu extends CanardDeCombat {
    /** L'intensité de la flamme utilisée pour amplifier l'attaque. */
    private double intensiteFlamme;

    /**
     * Constructeur avec tous les paramètres, incluant l'intensité de la flamme.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     * @param intensiteFlamme L'intensité de la flamme.
     */
    public CanardFeu(String nom, int pvMax, int atk, double intensiteFlamme) {
        super(nom, pvMax, atk);
        this.intensiteFlamme = intensiteFlamme;
    }
    /**
     * Constructeur avec paramètres standard, intensité par défaut à 1.0.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     */
    public CanardFeu(String nom, int pvMax, int atk) { this(nom, pvMax, atk, 1.0); }

    /**
     * Retourne le type élémentaire du canard.
     * @return TypeElementaire.FEU
     */
    @Override public TypeElementaire getType() { return TypeElementaire.FEU; }

    /**
     * Effectue une attaque par flamme sur la cible, amplifiée par l'intensité.
     * @param cible Le canard cible de l'attaque.
     */
    /**
     * Effectue une attaque par flamme sur la cible, amplifiée par l'intensité.
     * @param cible Le canard cible de l'attaque.
     */
    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
       
        double mult = cible.etreAttaqueePar(this); 
        
        effectuerAttaque(cible, mult * intensiteFlamme);
    }

    // Multiplicateurs de dégâts reçus selon le type de l'attaquant
    /** @return 0.5 pour les attaques de feu (résistance). */
    @Override 
    public double etreAttaqueePar(CanardFeu attaquant) { return 0.5; } // Résiste au feu
    /** @return 2.0 pour les attaques d'eau (faiblesse). */
    @Override 
    public double etreAttaqueePar(CanardEau attaquant) { return 2.0; } // Faible face à l'eau
    /** @return 0.5 pour les attaques de plante (résistance). */
    @Override 
    public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; } // Résiste à la plante

}