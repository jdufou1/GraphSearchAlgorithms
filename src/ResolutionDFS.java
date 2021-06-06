import java.util.ArrayList;
import java.util.Iterator;

public class ResolutionDFS implements IResolution<MapState,Solution>{

	
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
		Solution solution = DFS(new Solution(initialState),new ArrayList<>());
		double end = System.currentTimeMillis();
		System.out.println("[DFS] : Temps de r�solution du graphe d'�tat : "+(end-start)+" ms");
		System.out.println("[DFS] : Nombre de noeuds visit�s : "+nbNodeVisited);
		System.out.println("[DFS] : Nombre de noeuds d�velop�s : "+nbNodeDeveloped);
		return solution;
	}
	
	/* R�solution DFS avec d�tection de cycle */
	private Solution DFS(Solution s, ArrayList<Solution> dejaVu) {
		nbNodeDeveloped++;
		if(s.getState().equals(finalState)) {
			return s;
		}
		else {
			Iterator<MapState> lSucc = s.getState().getNextStates();
			while(lSucc.hasNext()) {
				nbNodeVisited++;
				MapState s_state = lSucc.next();
				if(!in(s_state,dejaVu)) {
					dejaVu.add(s);
					Solution res = DFS(new Solution(s_state,s),dejaVu);
					if(res != null) {
						return new Solution(s.getState(),res);
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
