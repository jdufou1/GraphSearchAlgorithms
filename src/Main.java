import java.awt.Point;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		
		int nbLine = 18;
		int nbCol = 18;
		
		String[][] mapInitial = {
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
				{"X","P"," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X","X","X","X","X","X","X"," ","X","X","X","X","X","X","X","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," ","X","X","X","X","X","X","X","X","X","X"," ","X","X","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"}
		};
		
		String[][] mapFinal = {
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," ","X"},
				{"X","X","X","X","X","X","X"," ","X","X","X","X","X","X","X","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"," ","X"},
				{"X"," ","X","X","X","X","X","X","X","X","X","X"," ","X","X","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," ","X"," "," ","P","X"},
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"}
		};
		
		
		
		
		
		String[][] test = {
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","P","X"},
				{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"}
		};
		
		
		MapState initialState = new MapState(nbLine,nbCol,mapInitial);
		MapState finalState = new MapState(nbLine,nbCol,mapFinal);
		MapState testState = new MapState(nbLine,nbCol,test);
		
		if(finalState.equals(testState)) {
			System.out.println("ok");
		}
		GUI view = new GUI("Recherche A*");

		Thread launcher = new Thread(new MapStateThread(initialState,finalState,view));
		launcher.start();
	}
}


class MapStateThread extends Thread{
	
	MapState initialState;
	MapState finalState;
	Solution solution;
	ArrayList<Point> positions;
	GUI view;
	
	public MapStateThread(MapState initialState, MapState finalState, GUI view) {
		this.initialState = initialState;
		this.finalState = finalState;
		this.view = view;
		positions = new ArrayList<>();
		
		ResolutionIDA solver = new ResolutionIDA();
		
		view.update(initialState,positions);
		view.setFinalMap(finalState);
		view.show();
		/* Resolution A* */
		solution = solver.solve(initialState, finalState).solutionPath();	
	}
	
	
	@Override
	public void run() {
		try {
			
			while(!initialState.equals(finalState)) {
				
				positions.add(new Point(initialState.positionPlayer().x , initialState.positionPlayer().y ));
				Thread.sleep(500);
				initialState = solution.nextMove();
				solution = solution.getFather();
				view.update(initialState,positions);
				view.repaint();
				
			}
			
			System.out.println("Parcours terminé !");
		}
		catch(Exception e) {	
			e.printStackTrace();
		}
	}
	
}

