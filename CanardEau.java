/**
 * Classe représentant un canard de type eau, avec une attaque par jet d'eau.
 * Hérite de CanardDeCombat et définit des multiplicateurs de dégâts pour les attaques élémentaires.
 */
public class CanardEau extends CanardDeCombat {
    /** La pression du jet d'eau utilisé pour l'attaque. */
    private int pressionJet;

    /**
     * Constructeur avec tous les paramètres, incluant la pression du jet.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     * @param pressionJet La pression du jet d'eau.
     */
    public CanardEau(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk);
        this.pressionJet = pressionJet;
    }
    /**
     * Constructeur avec paramètres standard, pression par défaut à 10.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     */
    public CanardEau(String nom, int pvMax, int atk) { this(nom, pvMax, atk, 10); }

    /**
     * Retourne le type élémentaire du canard.
     * @return TypeElementaire.EAU
     */
    @Override public TypeElementaire getType() { return TypeElementaire.EAU; }

    /**
     * Effectue une attaque par jet d'eau sur la cible.
     * Affiche un message avec la pression du jet.
     * @param cible Le canard cible de l'attaque.
     */
    /**
     * Effectue une attaque par jet d'eau sur la cible.
     * Affiche un message avec la pression du jet.
     * @param cible Le canard cible de l'attaque.
     */
    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
        System.out.println("💧 Jet d'eau (pression: " + this.pressionJet + ") !");
        
        double mult = cible.etreAttaqueePar(this); 
        effectuerAttaque(cible, mult);
    }

    // Multiplicateurs de dégâts reçus selon le type de l'attaquant
    /** @return 0.5 pour les attaques de feu (résistance). */
    @Override public double etreAttaqueePar(CanardFeu attaquant) { return 0.5; }
    /** @return 0.5 pour les attaques d'eau (résistance). */
    @Override public double etreAttaqueePar(CanardEau attaquant) { return 0.5; }
    /** @return 0.5 pour les attaques de plante (résistance). */
    @Override public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; } 
}