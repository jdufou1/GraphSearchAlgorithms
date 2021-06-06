
public interface IResolution<State, Solution>{

	public Solution solve(State initialState, State finalState);
	
}
