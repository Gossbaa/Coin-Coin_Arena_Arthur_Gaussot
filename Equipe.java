public class Equipe {
    private CanardDeCombat[] canards;
    private final String nomDresseur;
    private static int nbEquipesCrees = 0;
    private int nbCanardsActuels = 0;

    public Equipe(String nomDresseur) {
        this.nomDresseur = nomDresseur;
        this.canards = new CanardDeCombat[6];
        nbEquipesCrees++;
    }

    public boolean ajouter(CanardDeCombat c) {
        if (nbCanardsActuels < 6) {
            canards[nbCanardsActuels] = c;
            nbCanardsActuels++;
            return true;
        }
        return false;
    }

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

    public CanardDeCombat getPremierValide() {
        for (int i = 0; i < nbCanardsActuels; i++) {
            if (!canards[i].estKO()) {
                return canards[i];
            }
        }
        return null;
    }

    public void soignerTous() {
        for (int i = 0; i < nbCanardsActuels; i++) {
            canards[i].soigner();
        }
    }

    public void afficher() {
        System.out.println("=== ÉQUIPE de " + nomDresseur + " ===");
        for (int i = 0; i < nbCanardsActuels; i++) {
            System.out.println(canards[i].toString());
        }
        System.out.println();
    }

    public boolean touteKO() {
        return getPremierValide() == null;
    }

    public String getNomDresseur() { return nomDresseur; }
    
    public int getSurvivants() {
        int count = 0;
        for (int i = 0; i < nbCanardsActuels; i++) {
            if (!canards[i].estKO()) count++;
        }
        return count;
    }
}