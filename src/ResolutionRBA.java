import java.util.ArrayList;
import java.util.Iterator;

public class ResolutionRBA implements IResolution<MapState,Solution>{

	private int nbNodeVisited;
	private int nbNodeDeveloped;
	
	A algo1;
	A algo2;
	
	public ResolutionRBA() {
	}
	
	
	@Override
	public Solution solve(MapState initialState, MapState finalState) {
		
		algo1 = new A(initialState,finalState);
		algo2 = new A(finalState,initialState);
		
		
		double start = System.currentTimeMillis();
		while(!A.connexion(algo1, algo2)) {
			algo1.evalNode();
			if(A.connexion(algo1, algo2))
				break;
			else
				algo2.evalNode();
		}
		double end = System.currentTimeMillis();
		System.out.println("Solution trouvé !");
		Solution solution = A.buildSolution(algo1, algo2);
		System.out.println("[RBA*] : Temps de résolution du graphe d'état : "+(end-start)+" ms");
		
		System.out.println("[RBA* - Algo1] : Nombre de noeuds visités de l'algo1: "+algo1.getNbNodeVisited());
		System.out.println("[RBA* - Algo1] : Nombre de noeuds dévelopés de l'algo1: "+algo1.getNbNodeDeveloped());
		System.out.println("[RBA* - Algo2] : Nombre de noeuds visités de l'algo2: "+algo2.getNbNodeVisited());
		System.out.println("[RBA* - Algo2] : Nombre de noeuds dévelopés de l'algo2: "+algo2.getNbNodeDeveloped());
		System.out.println("[RBA*] : Nombre de noeuds visités au total: "+(algo1.getNbNodeVisited() + algo2.getNbNodeVisited()));
		System.out.println("[RBA*] : Nombre de noeuds dévelopés au total: "+(algo1.getNbNodeDeveloped() + algo2.getNbNodeDeveloped()));
		System.out.println("[RBA*] : Cout du chemin : "+solution.getF());
		return solution;
	}
}

class A{
	
	private int nbNodeVisited;
	private int nbNodeDeveloped;
	
	MapState initialState;
	MapState finalState;
	
	ArrayList<Solution> frontiere;
	ArrayList<Solution> dejaVu;
	
	public A(MapState initialState, MapState finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
		frontiere = new ArrayList<>();
		dejaVu = new ArrayList<>();
		
		Solution sinit = new Solution(initialState);
		frontiere.add(sinit);
		sinit.setF(sinit.getState().getH(finalState));
		
		
		nbNodeVisited = 0;
		nbNodeDeveloped =0;
	}
	
	public ArrayList<Solution> getFrontiere(){
		return frontiere;
	}
	
	/*
	 * Met a jour la frontiere
	 * */
	public void evalNode() {
		if(!frontiere.isEmpty()) {
			Solution n = choixFMin(frontiere);
			nbNodeDeveloped++;
			frontiere.remove(n);
			dejaVu.add(n);
			Iterator<MapState> states = n.getState().getNextStates();
			while(states.hasNext()) {
				nbNodeVisited++;
				MapState s_state = states.next();
				Solution s = in(s_state,dejaVu);
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
	
	public static boolean connexion(A algo1,A algo2) {
		for(Solution s1 : algo1.frontiere) {
			for(Solution s2 : algo2.frontiere) {
				if(s1.getState().equals(s2.getState())) {
					return true;
				}
			}
		}
		return false;
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
	
	public int getNbNodeDeveloped() {
		return nbNodeDeveloped;
	}
	
	public int getNbNodeVisited() {
		return nbNodeVisited;
	}
	
	public static Solution buildSolution(A algo1 , A algo2) {
		for(Solution s1 : algo1.frontiere) {
			for(Solution s2 : algo2.frontiere) {
				if(s1.getState().equals(s2.getState())) {
					System.out.println("taille de algo1 : " + s1.realCost());
					System.out.println("taille de algo2 : " + s2.realCost());
					return Solution.buildFromTheEnd(s1, s2);
				}
			}
		}
		return null;
	}
	
}
