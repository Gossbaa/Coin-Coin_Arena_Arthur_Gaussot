/**
 * Classe représentant un canard classique de combat.
 * Hérite de CanardDeCombat et implémente un type élémentaire normal.
 */
public class CanardClassique extends CanardDeCombat {
    /**
     * Constructeur avec paramètres personnalisés.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     */
    public CanardClassique(String nom, int pvMax, int atk) { super(nom, pvMax, atk); }
    /**
     * Constructeur par défaut avec valeurs standard.
     * @param nom Le nom du canard.
     */
    public CanardClassique(String nom) { this(nom, 50, 5); }

    /**
     * Retourne le type élémentaire du canard.
     * @return TypeElementaire.NORMAL
     */
    @Override public TypeElementaire getType() { return TypeElementaire.NORMAL; }

    /**
     * Effectue une attaque sur la cible spécifiée.
     * Vérifie si l'attaquant ou la cible est KO avant d'attaquer.
     * Calcule le multiplicateur de dégâts et applique l'attaque.
     * @param cible Le canard cible de l'attaque.
     */
    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
        
        double mult = cible.etreAttaqueePar(this); 
        effectuerAttaque(cible, mult);
    }
}