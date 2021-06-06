import java.util.ArrayList;
import java.util.Iterator;



/*
 * Développé par Jérémy DUFOURMANTELLE
 * Implémentation d'un algorithme de recherche à profondeur incrémentale
 * Iterative Deepening Search (IDS)
 * 
 * 
 * Implémentation d'un algorithme de recherche à profondeur bornée avec détéction de cycle
 * Bounded Depth-First Search (BDFS)
 * */
public class ResolutionIDS implements IResolution<MapState,Solution>{

	private MapState initialState;
	
	private MapState finalState;
	
	private int nbNodeVisited;
	private int nbNodeDeveloped;
	private int borne;
	
	@Override
	public Solution solve(MapState initialState, MapState finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
		double start = System.currentTimeMillis();
		borne = 0;
		nbNodeVisited = 0;
		nbNodeDeveloped =0;
		Solution solution = IDS(new Solution(initialState));
		double end = System.currentTimeMillis();
		System.out.println("[IDS] : Temps de résolution du graphe d'état : "+(end-start)+" ms");
		System.out.println("[IDS] : Nombre de noeuds visités : "+nbNodeVisited);
		System.out.println("[IDS] : Nombre de noeuds dévelopés : "+nbNodeDeveloped);
		System.out.println("[IDS] : Profondeur atteinte : "+borne);
		return solution;
	}
	
	public Solution IDS(Solution s) {
		Solution res = null;
		do {
			res = BDFS(s,new ArrayList<>(),borne);
			borne++;
		}while(res == null);
		return res;
	}
	
	public Solution BDFS(Solution s,ArrayList<Solution> dejaVu, int prof) {
		nbNodeDeveloped++;
		if(s.getState().equals(finalState)) {
			return s;
		}
		else if(prof == 0){
			return null;
		}
		else {
			Iterator<MapState> lSucc = s.getState().getNextStates();
			while(lSucc.hasNext()) {
				nbNodeVisited++;
				MapState s_state = lSucc.next();
				if(!in(s_state,dejaVu)) {
					dejaVu.add(s);
					Solution res = BDFS(new Solution(s_state,s),dejaVu, prof-1);
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
