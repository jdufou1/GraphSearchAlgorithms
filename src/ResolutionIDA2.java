
public class ResolutionIDA2 implements IResolution<MapState,Solution>{

	@Override
	public Solution solve(MapState initialState, MapState finalState) {
		
		
		double bound = initialState.getH(finalState);
		Solution path = new Solution(initialState);
		
		
		
		
		
		return null;
	}
	
	
	
	

}
