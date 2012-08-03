import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.graphioli.algorithms.BreadthFirstSearchTest;
import de.graphioli.algorithms.FindPathTest;
import de.graphioli.algorithms.PlanarityCheckTest;
import de.graphioli.gui.*;
import de.graphioli.model.GameBoardTest;
import de.graphioli.model.GraphConsistencyTest;
import de.graphioli.model.GridTest;

@RunWith(Suite.class)
@SuiteClasses({
	// gui
	//GameExplorerTest.class,
	
	// algorithms
	BreadthFirstSearchTest.class,
	FindPathTest.class,
	PlanarityCheckTest.class,
	
	// model
	GameBoardTest.class,
	GraphConsistencyTest.class,
	GridTest.class})

public class AllTests {
	// nothing
}
