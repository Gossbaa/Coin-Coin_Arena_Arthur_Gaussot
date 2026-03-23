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

# Reflexion R3

Nous avons dû écrire 13 méthodes etreAttaqueePar au total (4 dans la classe mère et 9 redéfinies dans les sous-classes).
L'ajout d'un 5ème type, comme Électrique, montre parfaitement les limites de cette approche. La lourdeur du Double Dispatch saute aux yeux car il devient obligatoire de modifier la classe mère pour y déclarer la nouvelle attaque, puis d'aller modifier toutes les sous-classes existantes pour la redéfinir. Bien que cette solution soit du polymorphisme pur et évite les conditions inutiles, elle viole le principe Ouvert/Fermé (SOLID). 

# Reflexion R4

Créer ces interfaces permet de s'affranchir de l'héritage strict qui impose qu'un participant "soit" un canard. Si l'arène était codée pour n'accepter que des objets de type CanardDeCombat, l'ajout d'un robot ou d'un dresseur serait impossible sans modifier son fonctionnement interne. Les interfaces remplacent cette contrainte d'identité par un simple contrat de capacité : tout objet qui implémente Combattant garantit au programme qu'il possède les méthodes pour attaquer et être mis KO. Ainsi, en programmant l'arène pour qu'elle manipule des Combattant ou des Soignable plutôt que des canards spécifiques, il devient possible d'ajouter n'importe quel nouveau type d'entité à l'avenir sans jamais avoir à retoucher le code de l'arène.

# Reflexion R5

L'approche par héritage crée effectivement une explosion combinatoire. Pour 4 types et 3 comportements, nous serions obligés de coder et maintenir 12 classes distinctes. Si un canard pouvait cumuler plusieurs comportements, ce nombre exploserait de façon exponentielle. Cela illustre parfaitement la rigidité de l'héritage strict pour ajouter des fonctionnalités : la création de sous-classes devient très vite ingérable. Pour résoudre ce problème, la conception orientée objet privilégie la composition, ce qui permet d'attacher dynamiquement un comportement de "confusion" à n'importe quel objet de base sans avoir à multiplier les classes.

# Reflexion R6

L'utilisation de instanceof est ici un signe flagrant de mauvaise conception. Cela force la classe Arene à connaître les types spécifiques de canards et leurs comportements intimes, ce qui brise l'encapsulation.
La solution orientée objet est d'utiliser le polymorphisme avec une méthode finDeTour() déclarée dans la classe mère et redéfinie uniquement dans les sous-classes qui en ont besoin.
Si l'on ajoute un CanardGlace qui fond à chaque tour, on code simplement sa perte de PV dans sa propre méthode finDeTour(). La classe Arene n'aura jamais besoin d'être modifiée : elle se contentera d'appeler canard.finDeTour() pour chaque combattant en lice. C'est la résolution dynamique qui exécutera automatiquement le bon comportement selon le type réel du canard.