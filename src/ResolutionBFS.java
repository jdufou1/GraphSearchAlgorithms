import java.util.ArrayList;
import java.util.Iterator;

public class ResolutionBFS implements IResolution<MapState,Solution>{

	private MapState initialState;
	
	private MapState finalState;
	
	private int nbNodeVisited;
	private int nbNodeDeveloped;
	
	
	@Override
	public Solution solve(MapState initialState, MapState finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
		double start = System.currentTimeMillis();
		
		nbNodeVisited = 0;
		nbNodeDeveloped =0;
		Solution solution = BFS(new Solution(initialState));
		double end = System.currentTimeMillis();
		System.out.println("[BFS] : Temps de résolution du graphe d'état : "+(end-start)+" ms");
		System.out.println("[BFS] : Nombre de noeuds visités : "+nbNodeVisited);
		System.out.println("[BFS] : Nombre de noeuds dévelopés : "+nbNodeDeveloped);
		return solution;
	}
	
	public Solution BFS(Solution sinit) {
		ArrayList<Solution> dejaDev = new ArrayList<>();
		ArrayList<Solution> frontiere = new ArrayList<>();
		frontiere.add(sinit);
		while(!frontiere.isEmpty()) {
			Solution n = frontiere.get(0);
			nbNodeDeveloped++;
			if(n.getState().equals(finalState)) {
				return n;
			}
			else {
				frontiere.remove(n);
				dejaDev.add(n);
				Iterator<MapState> lSucc = n.getState().getNextStates();
				while(lSucc.hasNext()) {
					nbNodeVisited++;
					MapState s_state = lSucc.next();
					if(!in(s_state,dejaDev) && !in(s_state,frontiere)) {
						Solution s = new Solution(s_state,n);
						frontiere.add(s);
					}
				}
			}
		}
		return null;
	}
	
	private boolean in(MapState state,ArrayList<Solution> ensemble) {
		for(Solution solution : ensemble) {
			if(solution.getState().equals(state))
				return true;
		}
		return false;
	}
	

}
