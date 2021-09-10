import java.util.ArrayList;
import java.util.Iterator;


/*
 * NON FONCTIONNEL
 * */
public class ResolutionIDA implements IResolution<MapState,Solution>{

	private MapState initialState;
	
	private MapState finalState;
	
	private int nbNodeVisited;
	private int nbNodeDeveloped;
	
	private double borneEchec;
	
	@Override
	public Solution solve(MapState initialState, MapState finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
		double start = System.currentTimeMillis();
		nbNodeVisited = 0;
		nbNodeDeveloped =0;

		
		Solution solution = IDA(new Solution(initialState));
		double end = System.currentTimeMillis();
		System.out.println("[IDA] : Temps de résolution du graphe d'état : "+(end-start)+" ms");
		System.out.println("[IDA] : Nombre de noeuds visités : "+nbNodeVisited);
		System.out.println("[IDA] : Nombre de noeuds dévelopés : "+nbNodeDeveloped);
		return solution;
	}

	public Solution IDA(Solution s) {
		double borne = s.getState().getH(finalState);
		boolean succes = false;
		boolean stop = false;
		ArrayList<Solution> dejaVu = new ArrayList<>();
		Solution res = null;
		while(!succes && !stop) {
			dejaVu.add(s);
			res = PHB(s,dejaVu,borne);
			if(res == null) {
				if(borneEchec > borne) {
					borne = borneEchec;
				}
				else {
					stop = true;
				}
			}
			else {
				succes = true;
			}
		}
		return res;
	}
	
	private Solution PHB(Solution s,ArrayList<Solution> dejaVu, double borne) {
		
		nbNodeDeveloped++;
		if(s.getF() > borne) {
			borneEchec = s.getF();
			return null;
		}
		if(s.getState().equals(finalState)) {
			return s;
		}
		else {
			
			double nouvelleBorne = (double)Integer.MAX_VALUE;
			Iterator<MapState> lSucc = s.getState().getNextStates();
			
			while(lSucc.hasNext()) {
				
				MapState s_state = lSucc.next();
				if(!in(s_state,dejaVu)) {
					nbNodeVisited++;
					dejaVu.add(s);
					double res = PHB(new Solution(s_state,s),dejaVu,borne);
					
					if(res == null) {
						
						nouvelleBorne = min(nouvelleBorne,borneEchec);
					}
					else {
						return s.getF() + res;
					}
				}
				else {
					
				}
			}
			borneEchec = nouvelleBorne;
			return null;
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
	
	private double min(double a,double b) {
		if(a>b)
			return b;
		else
			return a;
	}
}
















