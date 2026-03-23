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