import java.util.Random;

/**
 * Classe représentant un canard confus, qui peut s'attaquer lui-même ou devenir enragé.
 * Hérite de CanardEau et ajoute des comportements spéciaux de confusion.
 */
public class CanardConfus extends CanardEau {
    
    /** Indique si le canard est enragé, doublant son attaque. */
    private boolean enrage = false;
    /** Générateur de nombres aléatoires pour les comportements confus. */
    private Random random = new Random();

    
    /**
     * Constructeur avec tous les paramètres, incluant la pression du jet d'eau.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     * @param pressionJet La pression du jet d'eau.
     */
    public CanardConfus(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk, pressionJet);
    }

    /**
     * Constructeur avec paramètres standard, sans pression du jet.
     * @param nom Le nom du canard.
     * @param pvMax Les points de vie maximum.
     * @param atk Les points d'attaque.
     */
    public CanardConfus(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    /**
     * Retourne une représentation textuelle du canard, avec un émoji confus.
     * @return La chaîne de caractères représentant le canard.
     */
    @Override
    public String toString() {
        return super.toString() + " 😵"; // Ajout de l'émoji confus
    }

    
    /**
     * Retourne l'attaque du canard, doublée si enragé.
     * @return La valeur d'attaque actuelle.
     */
    @Override
    public int getAtk() {
        return this.enrage ? super.getAtk() * 2 : super.getAtk();
    }

    /**
     * Effectue une attaque, avec une chance de se blesser soi-même à cause de la confusion.
     * Désactive l'état enragé après l'attaque.
     * @param cible Le canard cible de l'attaque.
     */
    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO()) return;

        
        if (random.nextInt(4) == 0) {
            System.out.println(this.getSurnom() + " est confus ! Il se cogne la tête... Coin coin ?");
            
            super.attaquer(this); 
        } else {
            
            super.attaquer(cible);
        }

       
        if (this.enrage) {
            this.enrage = false;
        }
    }

    /**
     * Méthode spéciale qui rend le canard enragé, doublant son attaque au prochain tour.
     * Affiche un message de migraine.
     */
    public void migraine() {
        if (!this.estKO()) {
            System.out.println(this.getSurnom() + " se tient la tête... COIN. COIN.");
            this.enrage = true;
        }
    }
}