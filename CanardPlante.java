class CanardPlante extends CanardDeCombat {

    
    public CanardPlante(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    
    public CanardPlante(String nom) {
        this(nom, 80, 8); // Stats par défaut d'un canard plante
    }

    @Override
    public TypeElementaire getType() {
        return TypeElementaire.PLANTE;
    }

    public void regenerer() {
        if (!this.estKO()) {
            int soin = (int) (this.getPvMax() * 0.10);
            
            
            this.gagnerPv(soin); 
            
            System.out.println(this.getSurnom() + " se régénère de " + soin + " PV !");
        }
    }
}
