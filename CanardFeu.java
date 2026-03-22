class CanardFeu extends CanardDeCombat {
    private double intensiteFlamme;

    
    public CanardFeu(String nom, int pvMax, int atk, double intensiteFlamme) {
        super(nom, pvMax, atk);
        if (intensiteFlamme < 0.8 || intensiteFlamme > 1.5) {
            throw new IllegalArgumentException("L'intensité doit être entre 0.8 et 1.5");
        }
        this.intensiteFlamme = intensiteFlamme;
    }

    
    public CanardFeu(String nom, int pvMax, int atk) {
        this(nom, pvMax, atk, 1.0); // Intensité de 1.0 par défaut
    }

    
    @Override
    public TypeElementaire getType() {
        return TypeElementaire.FEU;
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (this.estKO() || cible.estKO()) return;

        System.out.println(this.getSurnom() + " attaque " + cible.getSurnom() + " !");

        
        double multiplicateur = calculerMultiplicateurType(this.getType(), cible.getType());
        int degatsFinaux = (int) (this.getAtk() * multiplicateur * this.intensiteFlamme);

        if (multiplicateur > 1.0) System.out.println("C'est super efficace !");
        else if (multiplicateur < 1.0 && multiplicateur > 0) System.out.println("Ce n'est pas très efficace...");
        else if (multiplicateur == 0) System.out.println("Cela n'a aucun effet...");

        cible.subirDegats(degatsFinaux);
    }

    @Override
    public String toString() {
        return super.toString() + " [Intensité: " + this.intensiteFlamme + "]";
    }
}