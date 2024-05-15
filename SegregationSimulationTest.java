
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegregationSimulationTest {

    @ Test
    void allSatisfied_True() {

        int[][] testMatrix = new int[2][2];

        testMatrix[0][0] = -1; testMatrix[0][1] = -1;
        testMatrix[1][0] = -1; testMatrix[1][1] = -1;

        assertEquals(true, SegregationSimulation.allSatisfied(testMatrix, 0.3)); // true if all agents and satisfied
    }

    @Test
    void countNeighboringFields_TrueOnCorner() {

        int[][] testMatrix = new int[2][2];
        testMatrix[0][0] = 1; // test agent
        testMatrix[0][1] = 1; testMatrix[1][0] = 1; testMatrix[1][1] = 1; // neighboring similar agents

        assertEquals(true, SegregationSimulation.countNeighboringFields(testMatrix, 0, 0, 0.3));
    }

    @Test
    void countNeighboringFields_FalseOnCorner() {

        int[][] testMatrix = new int[2][2];
        testMatrix[0][0] = 1; // test agent

        assertEquals(false, SegregationSimulation.countNeighboringFields(testMatrix, 0, 0, 0.3));
    }

    @Test
    void countNeighboringFields_FalseInCenter() {

        int[][] testMatrix = new int[3][3];
        testMatrix[1][1] = 1; // test agent

        assertEquals(false, SegregationSimulation.countNeighboringFields(testMatrix, 1, 1, 0.3));
    }


    @ Test
    void moveAgent_PicksEmptySpot() {

        int[][] testMatrix = new int[2][2];
        // fill all spaces with agents except one
        testMatrix[0][0] = 1; // agent to be moved
        testMatrix[0][1] = -1; testMatrix[1][0] = -1; // all filled w/ un-similar agents
        testMatrix[1][1] = 0; // one empty spot

        SegregationSimulation.moveAgent(testMatrix, 0, 0);
        assertEquals(1, testMatrix[1][1]); // true if agent 1 was successfully moved to [1, 1]
    }

}