/**
 * Interface définissant les actions de base d'un combattant dans l'arène.
 */
public interface Combattant {
    void attaquer(CanardDeCombat cible);
    boolean estKO();
    String getNom();
}