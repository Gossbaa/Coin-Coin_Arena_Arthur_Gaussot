public class CanardEau extends CanardDeCombat {
    private int pressionJet;

    public CanardEau(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk);
        this.pressionJet = pressionJet;
    }
    public CanardEau(String nom, int pvMax, int atk) { this(nom, pvMax, atk, 10); }

    @Override public TypeElementaire getType() { return TypeElementaire.EAU; }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
        System.out.println("💧 Jet d'eau (pression: " + this.pressionJet + ") !");
        
        double mult = cible.etreAttaqueePar(this); 
        effectuerAttaque(cible, mult);
    }

    
    @Override public double etreAttaqueePar(CanardFeu attaquant) { return 0.5; }
    @Override public double etreAttaqueePar(CanardEau attaquant) { return 0.5; }
    @Override public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; } 
}