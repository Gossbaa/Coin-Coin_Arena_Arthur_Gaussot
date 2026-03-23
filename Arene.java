/**
 * Classe représentant l'arène où se déroulent les combats entre équipes de canards.
 * Gère la logique de combat tour par tour.
 */
public class Arene {
    
    /**
     * Lance un combat entre deux équipes jusqu'à ce qu'une équipe soit entièrement KO.
     * Gère les tours, les attaques, les changements de canards et la fin de tour.
     * @param e1 La première équipe.
     * @param e2 La deuxième équipe.
     */
    public void combattre(Equipe e1, Equipe e2) {
    public void combattre(Equipe e1, Equipe e2) {
        System.out.println("=== COMBAT ===");
        int tour = 1;

        // Sélection des premiers canards valides
        CanardDeCombat c1 = e1.getPremierValide();
        CanardDeCombat c2 = e2.getPremierValide();

        if (c1 != null) System.out.println(e1.getNomDresseur() + " envoie " + c1.getSurnom() + " !");
        if (c2 != null) System.out.println(e2.getNomDresseur() + " envoie " + c2.getSurnom() + " !");

        // Boucle principale du combat
        while (!e1.touteKO() && !e2.touteKO()) {
            System.out.println("\nTour " + tour + " :");
            
            
            c1.attaquer(c2);
            
            if (c2.estKO()) {
                System.out.println(c2.getSurnom() + " est KO !");
                c2 = e2.getPremierValide();
                if (c2 != null) System.out.println(e2.getNomDresseur() + " envoie " + c2.getSurnom() + " !");
            } else {
                
                c2.attaquer(c1);
                if (c1.estKO()) {
                    System.out.println(c1.getSurnom() + " est KO !");
                    c1 = e1.getPremierValide();
                    if (c1 != null) System.out.println(e1.getNomDresseur() + " envoie " + c1.getSurnom() + " !");
                }
            }

            // Fin de tour : Polymorphisme pur (R6)
            if (c1 != null && !c1.estKO()) c1.finDeTour();
            if (c2 != null && !c2.estKO()) c2.finDeTour();

            tour++;
        }

       
        System.out.println("\n=== FIN DU COMBAT ===");
        if (e1.touteKO() && e2.touteKO()) {
            System.out.println("Égalité parfaite !");
        } else if (e2.touteKO()) {
            System.out.println("Victoire de " + e1.getNomDresseur() + " en " + (tour - 1) + " tours !");
            System.out.println("Survivants : " + e1.getSurvivants());
        } else {
            System.out.println("Victoire de " + e2.getNomDresseur() + " en " + (tour - 1) + " tours !");
            System.out.println("Survivants : " + e2.getSurvivants());
        }
    }
}