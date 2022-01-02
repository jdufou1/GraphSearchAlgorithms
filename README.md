# Recherche de chemins dans un labyrinthe avec interface graphique
Ces implémentation ont pour but d'analyser les performances de différents algorithmes de recherche non informé BFS (Breadth-First Search) ,DFS (Depth-First Search),IDS (Iterative Deepening Search)et de recherche informé avec A* et RBA* (Bidirectionnal Search A*).\
Le labyrinthe est représenté par un état qui contient un tableau 2 dimensions avec le code couleur suivant: Gris pour les blocs infranchissable, Bleu pour la case destination, Vert pour la position actuelle et Rouge pour le chemin emprunté. Voici un exemple d'une execution de l'algorithme RBA* :

<p align="center">
  <img src="https://github.com/jdufou1/perso_graphSearchAlgorithms/blob/main/assets/rechercheLabyrinthe.gif" alt="animated" height="450px"/>
</p>

# Tableau des temps d'execution des algorithmes de recherche
Algorithme| Temps d'execution (ms) moyen sur 300 itérations | Cout du chemin
 :---: | :---: | :---:
DFS | 6.416 | 290
RBA* (Manhattan)| 8.853 | 36
RBA* (Euclidienne)| 12.27 | 36
A* (Manhattan)| 14.303 | 36
A* (Euclidienne)| 14.366 | 36
BFS | 16.606 | 36
IDS (p = 146) | 940.223 | 290

La solution optimale représente un chemin de cout 36, nous pouvons vérifier que les algorithmes RBA*, A* et BFS nous retourne bien des solutions optimales.
J'ai implémenté le calcul de la distance euclidienne et le calcul de la distance de Manhattan pour la fonction heuristique des algorithmes se basant sur la recherche informé. Nous pouvons voir que la distance de manhattan est meilleur en terme d'efficacité. Ces deux heuristiques rendent l'exploration optimale car elles sont une borne inférieur (elles minorent) la vrai valeur du trajet optimal.
