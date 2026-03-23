/**
 * Classe représentant un canard de type plante, avec régénération à la fin du tour.
 * Hérite de CanardDeCombat et définit des multiplicateurs de dégâts pour les attaques élémentaires.
 */
public class CanardPlante extends CanardDeCombat {
    /**
     * Constructeur avec paramètres personnalisés.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     */
    public CanardPlante(String nom, int pvMax, int atk) { super(nom, pvMax, atk); }
    /**
     * Constructeur par défaut avec valeurs standard.
     * @param nom Le nom du canard.
     */
    public CanardPlante(String nom) { this(nom, 80, 8); }

    /**
     * Retourne le type élémentaire du canard.
     * @return TypeElementaire.PLANTE
     */
    @Override public TypeElementaire getType() { return TypeElementaire.PLANTE; }

    /**
     * Effectue une attaque sur la cible.
     * @param cible Le canard cible de l'attaque.
     */
    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
       
        double mult = cible.etreAttaqueePar(this); 
        effectuerAttaque(cible, mult);
    }
    
    // Multiplicateurs de dégâts reçus selon le type de l'attaquant
    /** @return 2.0 pour les attaques de feu (faiblesse). */
    @Override 
    public double etreAttaqueePar(CanardFeu attaquant) { return 2.0; } // Faible face au feu
    /** @return 0.5 pour les attaques d'eau (résistance). */
    @Override 
    public double etreAttaqueePar(CanardEau attaquant) { return 0.5; } // Résiste à l'eau
    /** @return 0.5 pour les attaques de plante (résistance). */
    @Override 
    public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; } // Résiste à la plante

    /**
     * Méthode appelée à la fin du tour pour régénérer les PV.
     */
    @Override
    public void finDeTour() {
        this.regenerer();
    }

}