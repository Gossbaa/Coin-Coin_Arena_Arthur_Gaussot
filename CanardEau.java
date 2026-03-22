class CanardEau extends CanardDeCombat {
    private int pressionJet;

    public CanardEau(String nom, int pvMax, int atk, int pressionJet) {
        super(nom, pvMax, atk);
        this.pressionJet = pressionJet;
    }

    
    public CanardEau(String nom, int pvMax, int atk) {
        this(nom, pvMax, atk, 10); // 10 bars par défaut
    }

    @Override
    public String getType() {
        return "Eau";
    }

    @Override
    public void attaquer(CanardDeCombat cible) {
        if (!this.estKO() && !cible.estKO()) {
            System.out.println("💧 Jet d'eau (pression: " + this.pressionJet + ") !");
        }
        
        super.attaquer(cible);
    }

    @Override
    public String toString() {
        return super.toString() + " [Pression: " + this.pressionJet + "]";
    }
}