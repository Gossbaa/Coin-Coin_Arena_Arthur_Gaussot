import java.util.Random;

public class CanardConfus extends CanardEau {
    
    private boolean enrage = false;
    private Random random = new Random();

    
    public CanardConfus(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk, pressionJet);
    }

    public CanardConfus(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    @Override
    public String toString() {
        return super.toString() + " 😵"; // Ajout de l'émoji confus
    }

    
    @Override
    public int getAtk() {
        return this.enrage ? super.getAtk() * 2 : super.getAtk();
    }

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

    public void migraine() {
        if (!this.estKO()) {
            System.out.println(this.getSurnom() + " se tient la tête... COIN. COIN.");
            this.enrage = true;
        }
    }
}