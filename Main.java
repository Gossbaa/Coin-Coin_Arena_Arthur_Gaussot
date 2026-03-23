public class Main {
    public static void main(String[] args) {
        
        
        Equipe sacha = new Equipe("Sacha");
        
        CanardFeu gerard = new CanardFeu("Canard Flamme", 39, 52);
        gerard.setSurnom("Gérard");
        
        CanardConfus coincoin = new CanardConfus("Canard Confus", 52, 48);
        coincoin.setSurnom("Coin-Coin");
        
        CanardPlante fernand = new CanardPlante("Canard Mousse", 45, 49);
        fernand.setSurnom("Fernand");
        
        sacha.ajouter(gerard);
        sacha.ajouter(coincoin);
        sacha.ajouter(fernand);

        Equipe ondine = new Equipe("Ondine");
        
        CanardEau hubert = new CanardEau("Canard Marin", 44, 48);
        hubert.setSurnom("Hubert");
        
        CanardClassique marcel = new CanardClassique("Canard Classique", 50, 45);
        marcel.setSurnom("Marcel");
        
        CanardFeu josette = new CanardFeu("Canard Braise", 42, 55);
        josette.setSurnom("Josette");
        
        ondine.ajouter(hubert);
        ondine.ajouter(marcel);
        ondine.ajouter(josette);

       
        sacha.afficher();
        ondine.afficher();

        
        Arene arene = new Arene();
        arene.combattre(sacha, ondine);

        
        System.out.println("\nStatistiques : " + CanardDeCombat.getNbCanardsCrees() + " canards ont été créés pour ce match.");
    }
}