
public class Solution implements ISolution<MapState>{

	private double f;
	
	private double g;
	
	private MapState state;
	
	private Solution father;
	
	public Solution() {
		state = null;
		father = null;
		f = 0;
		g = 0;
	}
	
	public Solution(MapState state) {
		this.state = state;
		father = null;
		f = 0;
		g = 0;
	}
	
	public Solution(MapState state, Solution father) {
		this.state = state;
		this.father = father;
		f = 0;
		g = 0;
	}
	

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public MapState getState() {
		return state;
	}

	public void setState(MapState state) {
		this.state = state;
	}

	public Solution getFather() {
		return father;
	}

	public void setFather(Solution father) {
		this.father = father;
	}
	
	
	public void afficherSolution() {
		
		System.out.println(state.toString());
		System.out.println("--------------");
		if(father != null) {	
			father.afficherSolution();
		}
	}
	
	public Solution solutionPath() {
		return father.aux_solutionPath(new Solution(state));
	}
	
	private Solution aux_solutionPath(Solution acc) {
		if(state == null)
			return acc;
		else if(father != null)
			return father.aux_solutionPath(new Solution(state, acc));
		else
			return new Solution(state, acc);
			
	}
	
	public MapState nextMove() {
		if(father != null)
			return father.getState();
		else
			return null;
	}
	
	public int realCost() {
		 if(father == null)
			 return 0;
		 else 
			 return father.realCost() + 1;
	}
	
	/*
	 * A utiliser pour la recherche bidirectionnelle
	 * S1 Solution : solution partant de l'etat initial
	 * S2 Solution : solution partant de l'etat but
	 * */
	public static Solution buildFromTheEnd(Solution s1, Solution s2) {
		Solution result = s1;
		Solution last = s2;
		while(last.father != null) {
			result = new Solution(last.getState(),result);
			result.setF(s1.realCost() + s2.realCost() - last.realCost());
			last = last.father;
		}
		result = new Solution(last.getState(),result);
		result.setF(s1.realCost() + s2.realCost() - last.realCost());
		return result;
	}
	
}
