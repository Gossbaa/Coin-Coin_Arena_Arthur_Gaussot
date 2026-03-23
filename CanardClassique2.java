public class CanardClassique extends CanardDeCombat {
    public CanardClassique(String nom, int pvMax, int atk) { super(nom, pvMax, atk); }
    public CanardClassique(String nom) { this(nom, 50, 5); }

    @Override public TypeElementaire getType() { return TypeElementaire.NORMAL; }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;
        
        double mult = cible.etreAttaqueePar(this); 
        effectuerAttaque(cible, mult);
    }
}