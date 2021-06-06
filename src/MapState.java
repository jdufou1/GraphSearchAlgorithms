import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class MapState implements IState<MapState> {

	private int sizeL;
	private int sizeC;
	private String[][] map; 
	
	public MapState(int sizeL, int sizeC, String[][] map) {
		this.sizeL = sizeL;
		this.sizeC = sizeC;
		this.map = map;
	}
	
	
	@Override
	public Iterator<MapState> getNextStates() {
		Point player = positionPlayer();
		ArrayList<MapState> l = getSuccessor(player);
		return l.iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0 ; i < sizeL; i ++) {
			for(int j = 0 ; j < sizeL; j ++) {
				str.append(map[i][j]);
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	@Override 
	public boolean equals(Object state) {
		if(!(state instanceof MapState))
			return false;
		MapState ms = (MapState)state;
		for(int i = 0 ; i < sizeL; i ++)
			for(int j = 0 ; j < sizeL; j ++)
				if(map[i][j] != ms.getMap()[i][j])
					return false;
		return true;
	}

	public int getSizeL() {
		return sizeL;
	}

	public void setSizeL(int sizeL) {
		this.sizeL = sizeL;
	}

	public int getSizeC() {
		return sizeC;
	}

	public void setSizeC(int sizeC) {
		this.sizeC = sizeC;
	}

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}
	
	public Point positionPlayer() {
		Point player = null;
		for(int i = 0 ; i < sizeL; i ++) {
			for(int j = 0 ; j < sizeL; j ++) {
				if(map[i][j].contains("P") )
					player = new Point(i,j);
			}
		}
		return player;		
	}
	 
	private ArrayList<MapState> getSuccessor(Point player){
		ArrayList<MapState> nextStates = new ArrayList<>();
		/* Deplacement verticaux et horizontaux */
		
		// vers le bas
		if(player.x < sizeL-1) {
			if(map[player.x+1][player.y].contains(" "))
				nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x+1,player.y))));
		}
		//vers le haut
		if(player.x > 0) {
			if(map[player.x-1][player.y].contains(" "))
				nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x-1,player.y))));
		}
		// vers la gauche
		if(player.y < sizeC-1) {
			if(map[player.x][player.y+1].contains(" "))
				nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x,player.y+1))));
		}
		// vers la droite
		if(player.y > 0) {
			if(map[player.x][player.y-1].contains(" "))
				nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x,player.y-1))));
		}
		
		// Déplacement en diagonale
		if(player.x < sizeL-1){
			// en bas a droite
			if(player.y < sizeC-1) {
				if(map[player.x+1][player.y+1].contains(" ")) {
					nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x+1,player.y+1))));
				}
			}
			
			//en bas a gauche
			if(player.y > 0) {
				if(map[player.x+1][player.y-1].contains(" ")) {
					nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x+1,player.y-1))));
				}
			}
		}
		if(player.x > 0) {
			// en haut a droite
			if(player.y < sizeC-1) {
				if(map[player.x-1][player.y+1].contains(" ")) {
					nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x-1,player.y+1))));
				}
			}
						
			//en haut a gauche
			if(player.y > 0) {
				if(map[player.x-1][player.y-1].contains(" ")) {
					nextStates.add(new MapState(sizeL, sizeC, movePlayer(player,new Point(player.x-1,player.y-1))));
				}
			}
		}
		return nextStates;
	}
	
	public String[][] movePlayer(Point currentPosition, Point destination){
		String[][] newMap = new String[sizeL][sizeC];
		for(int i = 0 ; i < sizeL; i ++) 
			for(int j = 0 ; j < sizeL; j ++)
				newMap[i][j] = map[i][j];
		newMap[currentPosition.x][currentPosition.y] = " ";
		newMap[destination.x][destination.y] = "P";
		return newMap;
	}


	@Override
	public double getH(MapState state) {
		Point a = positionPlayer();
		Point b = state.positionPlayer();
		return distanceManhattan(a,b);
	}

	/* Calcul d'heuristique */
	
	
	private double distanceEuclidienne(Point a, Point b) {
		
		return Math.sqrt( (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y) );
	}
	
	private double distanceManhattan(Point a, Point b) {
		return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
	}
	

	@Override
	public double getCost(MapState state) {
		return 1.0;
	}
}
