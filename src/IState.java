import java.util.Iterator;

public interface IState<State> {

	@Override
	public String toString();
	
	@Override
	public boolean equals(Object state);
	
	public Iterator<State> getNextStates();
	
	public double getH(State state);
	
	public double getCost(State state);
}
