public class CanardFeu extends CanardDeCombat {
    private double intensiteFlamme;

    public CanardFeu(String nom, int pvMax, int atk, double intensiteFlamme) {
        super(nom, pvMax, atk);
        this.intensiteFlamme = intensiteFlamme;
    }
    public CanardFeu(String nom, int pvMax, int atk) { this(nom, pvMax, atk, 1.0); }

    @Override public TypeElementaire getType() { return TypeElementaire.FEU; }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
       
        double mult = cible.etreAttaqueePar(this); 
        
        effectuerAttaque(cible, mult * intensiteFlamme);
    }

    
}