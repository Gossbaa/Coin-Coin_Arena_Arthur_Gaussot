# Reflexion R1
Le problème principal est la redondance des données.
En stockant ces attributs directement dans la classe, on crée deux soucis majeurs :

Gaspillage mémoire : Si on crée 1 000 "Canard Flamme", le programme va allouer de la mémoire 1 000 fois pour stocker exactement le même nom d'espèce, les mêmes PV max et la même attaque de base.

Problème d'équilibrage : Si on décide d'augmenter l'attaque de base de cette espèce lors d'une mise à jour du jeu, on devra modifier individuellement toutes les instances existantes.