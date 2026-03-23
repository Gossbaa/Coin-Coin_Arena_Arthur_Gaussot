public class CanardPlante extends CanardDeCombat {
    public CanardPlante(String nom, int pvMax, int atk) { super(nom, pvMax, atk); }
    public CanardPlante(String nom) { this(nom, 80, 8); }

    @Override public TypeElementaire getType() { return TypeElementaire.PLANTE; }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
       
        double mult = cible.etreAttaqueePar(this); 
        effectuerAttaque(cible, mult);
    }
    
@Override 
public double etreAttaqueePar(CanardFeu attaquant) { return 2.0; } // Faible face au feu
@Override 
public double etreAttaqueePar(CanardEau attaquant) { return 0.5; } // Résiste à l'eau
@Override 
public double etreAttaqueePar(CanardPlante attaquant) { return 0.5; } // Résiste à la plante

@Override
public void finDeTour() {
    this.regenerer();
}

}