class CanardClassique extends CanardDeCombat {

    
    public CanardClassique(String nom, int pvMax, int atk) {
        super(nom, pvMax, atk);
    }

    
    public CanardClassique(String nom) {
        this(nom, 50, 5); 
    }

    @Override
    public String getType() {
        return "Normal";
    }
    
    
}