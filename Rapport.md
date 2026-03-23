# Reflexion R1
Le problème principal est la redondance des données.
En stockant ces attributs directement dans la classe, on crée deux soucis majeurs :

Gaspillage mémoire : Si on crée 1 000 "Canard Flamme", le programme va allouer de la mémoire 1 000 fois pour stocker exactement le même nom d'espèce, les mêmes PV max et la même attaque de base.

Problème d'équilibrage : Si on décide d'augmenter l'attaque de base de cette espèce lors d'une mise à jour du jeu, on devra modifier individuellement toutes les instances existantes.

# Reflexion R2

Si on écrit ce code, on obtient une erreur de compilation. Le compilateur cherche strictement getMultiplicateur(CanardDeCombat) car c'est le type déclaré de la variable cible. Comme aucune méthode ne correspond exactement à cela, la compilation ne marchera pas.
Puisque la surcharge  est bloquée par le type déclaré, il faut exploiter la redéfinition pour que le programme utilise le type réel à l'exécution.
Pour s'en sortir, il faut contourner la vérification stricte des types des classes Java. 
Pour ce faire, on utilise l'abstraction via une méthode polymorphe  : Au lieu de passer l'objet entier, on crée une méthode abstraite getType() redéfinie par chaque sous-classe. La méthode attaquer utilise ensuite l'énumération retournée par this.getType() et cible getType() pour trouver le multiplicateur dans un tableau.

