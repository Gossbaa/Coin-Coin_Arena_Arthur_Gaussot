/**
 * Classe représentant une équipe de canards de combat.
 * Gère une liste de jusqu'à 6 canards, avec méthodes pour ajouter, retirer, et vérifier l'état.
 */
public class Equipe {
    /** Tableau des canards de l'équipe (maximum 6). */
    private CanardDeCombat[] canards;
    /** Nom du dresseur de l'équipe. */
    private final String nomDresseur;
    /** Compteur statique du nombre total d'équipes créées. */
    private static int nbEquipesCrees = 0;
    /** Nombre actuel de canards dans l'équipe. */
    private int nbCanardsActuels = 0;

    /**
     * Constructeur d'une équipe avec le nom du dresseur.
     * Initialise le tableau de canards et incrémente le compteur d'équipes.
     * @param nomDresseur Le nom du dresseur.
     */
    public Equipe(String nomDresseur) {
        this.nomDresseur = nomDresseur;
        this.canards = new CanardDeCombat[6];
        nbEquipesCrees++;
    }

    /**
     * Ajoute un canard à l'équipe si elle n'est pas pleine.
     * @param c Le canard à ajouter.
     * @return true si ajouté, false si équipe pleine.
     */
    public boolean ajouter(CanardDeCombat c) {
        if (nbCanardsActuels < 6) {
            canards[nbCanardsActuels] = c;
            nbCanardsActuels++;
            return true;
        }
        return false;
    }

    /**
     * Retire un canard de l'équipe par son surnom.
     * Décale les éléments suivants pour combler le vide.
     * @param surnom Le surnom du canard à retirer.
     */
    public void retirer(String surnom) {
        for (int i = 0; i < nbCanardsActuels; i++) {
            if (canards[i].getSurnom().equals(surnom)) {
                
                for (int j = i; j < nbCanardsActuels - 1; j++) {
                    canards[j] = canards[j + 1];
                }
                canards[nbCanardsActuels - 1] = null;
                nbCanardsActuels--;
                return;
            }
        }
    }

    /**
     * Retourne le premier canard non KO de l'équipe.
     * @return Le premier canard valide, ou null si tous KO.
     */
    public CanardDeCombat getPremierValide() {
        for (int i = 0; i < nbCanardsActuels; i++) {
            if (!canards[i].estKO()) {
                return canards[i];
            }
        }
        return null;
    }

    /**
     * Soigne tous les canards de l'équipe.
     */
    public void soignerTous() {
        for (int i = 0; i < nbCanardsActuels; i++) {
            canards[i].soigner();
        }
    }

    /**
     * Affiche l'équipe avec tous ses canards.
     */
    public void afficher() {
        System.out.println("=== ÉQUIPE de " + nomDresseur + " ===");
        for (int i = 0; i < nbCanardsActuels; i++) {
            System.out.println(canards[i].toString());
        }
        System.out.println();
    }

    /**
     * Vérifie si toute l'équipe est KO.
     * @return true si tous les canards sont KO, false sinon.
     */
    public boolean touteKO() {
        return getPremierValide() == null;
    }

    /**
     * Retourne le nom du dresseur.
     * @return Le nom du dresseur.
     */
    public String getNomDresseur() { return nomDresseur; }
    
    /**
     * Compte le nombre de canards survivants (non KO).
     * @return Le nombre de survivants.
     */
    public int getSurvivants() {
        int count = 0;
        for (int i = 0; i < nbCanardsActuels; i++) {
            if (!canards[i].estKO()) count++;
        }
        return count;
    }
}