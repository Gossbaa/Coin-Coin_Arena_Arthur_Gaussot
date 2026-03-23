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
    
}