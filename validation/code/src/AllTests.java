import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.graphioli.algorithms.BreadthFirstSearchTest;
import de.graphioli.algorithms.FindPathTest;
import de.graphioli.algorithms.PlanarityCheckTest;
import de.graphioli.gui.*;
import de.graphioli.model.EdgeTest;
import de.graphioli.model.GameBoardTest;
import de.graphioli.model.GraphConsistencyTest;
import de.graphioli.model.GridTest;
import de.graphioli.model.PlayerTests;

@RunWith(Suite.class)
@SuiteClasses({
	// algorithms
	BreadthFirstSearchTest.class,
	FindPathTest.class,
	PlanarityCheckTest.class,
	
	// model
	GameBoardTest.class,
	GraphConsistencyTest.class,
	GridTest.class,
	EdgeTest.class,
	PlayerTests.class,
	
	// gui
	DirectedEdgeTest.class,
	GraphColoringTest.class,
	TwixTTest.class,
	GameExplorerTest.class,
})

public class AllTests {
	// nothing
}