---
title: "Laboratoire 07 - Chess"
author: "Tristan Gerber, Edison Sahitaj, Arnaud Leyre"
date: "\today"
geometry: margin=1in
header-includes:
  - \usepackage{fancyhdr}
  - \pagestyle{fancy}
  - \fancyhead[L]{Laboratoire 07 - Chess}
  - \fancyhead[R]{Page \thepage}
  - \fancyfoot[L]{Tristan Gerber, Edison Sahitaj, Arnaud Leyre}
  - \fancyfoot[C]{}
  - \fancyfoot[R]{\thepage}
toc: true
---

\pagebreak

## Problématique
<span style="text-align: justify;">
Ce laboratoire a pour but d'implémenter la partie logique d'un jeu d'échecs en Java. Le laboratoire étant réalisé dans le cadre d'un cours de Programmation Orientée Objet, nous avons mis l'accent sur la modularité et l'extensibilité du code.  

L'interface utilisateur nous est déjà fournie au début du laboratoire.
</span>

---

## Conception

En vue de notre objectif, c'est-à-dire d'avoir un code extensible et modulaire, nous avons opté pour créer une classe par pièce.  

**ChessGame** s'occupe uniquement d'instancier le Board et de créer une nouvelle partie, en plaçant toutes les pièces initiales.  

**Board** gère l'ensemble du jeu. C'est celui qui stocke le plateau d'échecs et s'occupe de toute la logique générale, soit tout ce qui n'est pas spécifique aux pièces.

**Chess** n'est pas modifié. Il s'occupe de créer le contrôleur et la vue, puis de lancer le programme.

### Pièces
Les pièces ont été créées dans un package "pieces". Celles-ci sont créées par une **PieceFactory**, et héritent toutes de la classe abstraite **Piece**.

### Algorithmes pour Check et Matcheck

#### Conception initiale
Nous avons au début du projet, avant même de commencer à coder, effectué la conception de notre projet. Elle a été changée après plusieurs discussions avec les encadrants, car elle manquait de modularité et n'était pas assez orientée objet.

##### CHECKCHECK()

On vérifie les différentes menaces autour du roi.

###### Vérifications des menaces

1. **Check horizontal :**
   - Pièces à vérifier : Dame, Tour, Roi.
   - On parcourt la ligne ou colonne à partir du roi.
   - On retourne la première pièce rencontrée.
   - Si cette pièce est une des pièces dangereuses respectives (et peut atteindre le roi), alors échec.

2. **Check diagonal :**
   - Pièces à vérifier : Dame, Fou, Pion, Roi.
   - On parcourt les diagonales à partir du roi.
   - On retourne la première pièce rencontrée.
   - Si cette pièce est une des pièces dangereuses respectives (et peut atteindre le roi), alors échec.

3. **Check en L :**
   - Pièce à vérifier : Cavalier.
   - On vérifie les cases en forme de "L" autour du roi.
   - Si un cavalier menace le roi, on retourne cette pièce.
   - Remarque : Il ne peut y avoir qu’un seul cavalier menaçant le roi simultanément.

**NOTE 1 :** Après discussion avec les assistants, il a été décidé que ce système était trop rigide. Nous avons donc simplement fait en sorte de faire un `tryMove()` pour chaque pièce ennemie sur le roi. Ainsi, on peut utiliser la liaison dynamique pour facilement.

**NOTE 2 :** Cette fonction n'est pas utilisée uniquement pour le roi comme prévu initialement, mais à chaque fois que l'on veut tester l'accessibilité d'une case.

###### Résultat
- Si une pièce est retournée par les vérifications ci-dessus, alors le roi est en **échec**.

###### Scénarios
- Si le joueur vient de jouer et met son propre roi en échec :
  - **ILLEGAL MOVE** : On annule le mouvement et demande au joueur de rejouer.
- Si l’ennemi vient de jouer et met le roi en échec :
  - On teste si c'est un mat.

###### Étapes de vérification du mat

1. **Déplacement du Roi :**
   - Pour chaque case adjacente au roi, on tente un déplacement avec `TryMove` (qui utilise `CHECKCHECK`).
   - Si au moins une case est "safe", alors **ce n’est pas un mat**.

2. **Pièce dangereuse :**
   - On identifie la pièce qui met le roi en échec.
   - Si la pièce est une Dame, une Tour, un Fou, un Pion (à portée) ou un Cavalier :
     - Pour chaque case sur la trajectoire entre la pièce dangereuse et le roi (incluant la case de la pièce dangereuse) :
       - On vérifie si une pièce alliée peut atteindre cette case (en utilisant `CHECKCHECK`).
     - Si la pièce dangereuse est un Cavalier :
       - On vérifie uniquement la case du cavalier ennemi.
     - Si au moins une pièce alliée peut bloquer ou capturer, alors **ce n’est pas un mat**.
     - Si aucune pièce alliée ne peut intervenir pour sauver le roi, alors **c'est un mat**.


---

## Implémentation finale

### 1. Création des pièces dans *ChessGame*
Dans la classe `ChessGame`, la méthode `setPieces()` initialise le plateau en créant toutes les pièces via :

```java
board.addPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);  

board.addPiece(PieceType.PAWN, PlayerColor.WHITE, i, 1);  
```

Le placement standard est effectué pour les Blancs et les Noirs. On utilise pour cela la méthode `addPiece(...)` du `Board`, qui va à son tour faire appel à la factory.

### 2. Board : Structure et Logique

#### 2.1 Stockage des pièces

- **Tableau 2D** (`Piece[][] grid`) pour représenter les 64 cases.  
- **Listes de pièces** (`ArrayList<ArrayList<Piece>> pieces`) pour accéder rapidement aux pièces par couleur.  
- **Références directes aux rois** (`Piece[] kings`) pour détecter rapidement s’ils sont menacés.

Lors de l’ajout, on crée la pièce via la **factory**, on la place dans `grid[x][y]` et on l’ajoute à la liste des pièces de la bonne couleur.  
Lors de la suppression, on met la case à `null` et on retire la pièce de la liste correspondante.

#### 2.2 Fonction *move(...)*
Pour déplacer une pièce :

1. On récupère la pièce source.  
2. On vérifie si c’est bien son tour et si `tryMove(...)` est valide.  
3. On **simule** le mouvement : on place la pièce en `(x2, y2)` et on vide `(x1, y1)`.  
4. On appelle `checkCheck(...)` pour voir si le roi du joueur est en échec. En cas d’échec illégal, on “revert” (on annule) le coup.  
5. Si tout est bon, on met à jour l’interface et on incrémente le tour.

#### 2.3 Fonction *checkCheck(...)*
Elle parcourt toutes les pièces adverses et appelle `tryMove(kingX, kingY)` pour voir si elles peuvent capturer la case du roi. Si oui, le roi est en échec.

La fonction `checkcheck(...)` diffère légèrement de la conception initiale; nous avons enlevé les tests trop rigides, comme par exemple ```if(piece == fou) ...```, même si ceux-ci permettaient d'avoir plus de performances, car nous priorisons le côté orienté objet.  

On vérifie donc simplement pour chaque pièce qui n'est pas de la couleur du joueur actuel si elles peuvent accéder à la case demandée (souvent utilisé sur le roi pour vérifier s'il est en échec).

#### 2.4 Classe interne *PieceChoice*
Lors de la promotion d’un pion, on interroge l’utilisateur pour choisir la nouvelle pièce. Cette classe implémente `UserChoice` afin d’afficher le choix de promotion via l’interface (GUI/console).

### 3. PieceFactory
La classe `PieceFactory` centralise la création d’objets `Piece`. Lorsqu’on appelle `createPiece(...)`, un `switch` sur le `PieceType` choisit la sous-classe adéquate (Pion, Fou, Tour, etc.). Cela évite la prolifération de `new Pawn(...)` ou `new Rook(...)` dispersés dans le code.

### 4. Classe abstraite *Piece* & Différentes pièces

#### 4.1 Structure
- Coordonnées `x, y`.  
- Couleur `color`.  
- Référence à la classe `Board`.  
- Méthode abstraite `tryMove(int x, int y)`.

#### 4.2 Méthode *tryMove* et sous-classes
Chaque sous-classe (ex. `Rook`, `Knight`) implémente `tryMove(...)` pour vérifier la légalité d’un déplacement (mouvement en L, diagonale, etc.). Les fonctions d’aide (ex. `Tools.getDiag(...)`, `Tools.getLine(...)`) factorisent la logique de parcours, permettant à chaque pièce de se concentrer sur son pattern.

#### 4.3 Classe *Pion* et méthodes
##### 4.3.1 tryEnPassant
- Récupération de la cible : La méthode récupère la pièce située à la même colonne que le pion, mais à la ligne cible.
- Vérification des conditions : Elle vérifie si la pièce cible est un pion, si ce pion a été déplacé lors du dernier tour, et si ce pion est de la couleur opposée.
- Suppression de la cible : Si toutes les conditions sont remplies, la méthode supprime la pièce cible du plateau et retourne true.
- Retourne false : Si les conditions ne sont pas remplies, la méthode retourne false.

##### 4.3.2 checkPromoted
- Vérification des conditions de promotion : La méthode vérifie si le pion a atteint la dernière rangée (7 pour les blancs, 0 pour les noirs) et si le plateau n'est pas en mode simulation.
- Retour du résultat : Elle retourne true si le pion peut être promu, sinon elle retourne false.

### 5. Tools
La classe `Tools` propose des méthodes utilitaires (calcul de distance, vérification des limites, détection de la première pièce rencontrée, etc.). Elle renforce la modularité en centralisant la logique de parcours, évitant de dupliquer du code dans chaque sous-classe de pièce.

## Tests effectués
Les tests ont été effectués manuellement en utilisant l'interface. Nous avons couvert plusieurs scénarios affichés ci-dessous.
![](tests.png)

## Conclusion
Ce laboratoire nous a permis de mettre en application ce que nous avons appris dans notre cours de POO. Nous avons couvert notamment:  
- Records  
- Classes internes
- Enums
- Classe abstraite
- Héritage
- Liaison dynamique
