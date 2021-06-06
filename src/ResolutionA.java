import java.util.ArrayList;
import java.util.Iterator;

public class ResolutionA implements IResolution<MapState,Solution> {

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
		Solution solution = A();
		double end = System.currentTimeMillis();
		System.out.println("[A*] : Temps de résolution du graphe d'état : "+(end-start)+" ms");
		System.out.println("[A*] : Nombre de noeuds visités : "+nbNodeVisited);
		System.out.println("[A*] : Nombre de noeuds dévelopés : "+nbNodeDeveloped);
		System.out.println("[A*] : Cout du chemin : "+solution.getF());
		return solution;
	}
	
	private Solution A() {
		ArrayList<Solution> dejaDev = new ArrayList<>();
		ArrayList<Solution> frontiere = new ArrayList<>();
		Solution sinit = new Solution(initialState);
		frontiere.add(sinit);
		sinit.setF(sinit.getState().getH(finalState));
		
		while(!frontiere.isEmpty()) {
			Solution n = choixFMin(frontiere);
			if(n.getState().equals(finalState)) {
				return n;
			}
			else {
				nbNodeDeveloped++;
				frontiere.remove(n);
				dejaDev.add(n);
				Iterator<MapState> states = n.getState().getNextStates();
				while(states.hasNext()) {
					nbNodeVisited++;
					MapState s_state = states.next();
					Solution s = in(s_state,dejaDev);
					if(s == null)
						s = in(s_state,frontiere);
					
					if(s == null) {
						s = new Solution(s_state);
						s.setFather(n);
						s.setG(n.getG() + n.getState().getCost(s.getState()));
						s.setF(s.getG() + s.getState().getH(finalState));
						frontiere.add(s);
					}
					else {
						if(s.getG() > n.getG() + n.getState().getCost(s.getState())) {
							s.setFather(n);
							s.setG(n.getG() + n.getState().getCost(s.getState()));
							s.setF(s.getG() + s.getState().getH(finalState));
						}
					}
				}
			}
		}
		return null;
	}
	
	
	private Solution choixFMin(ArrayList<Solution> ensemble) {
		Solution result = ensemble.get(0);
		double f = result.getF();
		for(Solution solution : ensemble) {
			double tempf = solution.getF();
			if(tempf < f) {
				f = tempf;
				result = solution;
			}
		}
		return result;
	}
	
	private Solution in(MapState state,ArrayList<Solution> ensemble) {
		for(Solution solution : ensemble) {
			if(solution.getState().equals(state))
				return solution;
		}
		return null;
	}


}
