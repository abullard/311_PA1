import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author nkarasch
 */
public class _NearestPointsTest {
    private static final String TEST_DATA_FILENAME = "./points.txt";
    private static final String NAIVE_FILENAME = "NaiveSolution.txt";
    private static final String HASH_FILENAME = "HashSolution.txt";
    private NearestPoints np;

    @Before
    public void setUp() throws Exception {
        if (np == null) {
            np = new NearestPoints(TEST_DATA_FILENAME);
        }
    }

    @Test
    public void nearestPoints() throws Exception {
        long startTime = System.nanoTime();
        System.out.println("\nComparing nearestPoints(p) across algorithms... ");

        np = new NearestPoints(TEST_DATA_FILENAME);
        ArrayList<Float> points = np.getPointList();
        boolean foundClosePoints = false;
        int numCompared = 0;
        int numGreaterThanOneClose = 0;
        ArrayList<Float> expected;
        ArrayList<Float> actual;

        // Seed with start time
        Random rand = new Random(startTime);
        System.out.println("Randomizer seed = " + startTime);

        // Choose 100 points at random from the test data to compare between the two algorithms.
        // If none of of those 100 points had a "close" point other than itself, we keep going.
        for (int i = 0; i < 100 || !foundClosePoints; i++) {
            int p = rand.nextInt(points.size());
            expected = np.naiveNearestPoints(p);
            actual = np.npHashNearestPoints(p);
            Collections.sort(expected);
            Collections.sort(actual);
            numCompared++;
            if (expected.size() > 1) {
                numGreaterThanOneClose++;
                if (!foundClosePoints) {
                    foundClosePoints = true;
                }
            }
            assertEquals(expected.size(), actual.size());
            for (int j = 0; j < expected.size(); j++) {
                assertEquals(0, Float.compare(expected.get(j), actual.get(j)));
            }
        }

        long endTime = System.nanoTime();
        double elapsed = (endTime - startTime) * 1.0e-9;
        System.out.printf("Done.\nLooked at %d points, %d of which had >1 close points.\n" +
                "Total elapsed time: %.3f seconds\n", numCompared, numGreaterThanOneClose, elapsed);
    }

    @Test
    public void allNearestPoints() throws Exception {
        // Run naive solution
        runSolution(false);
        // Run hash solution
        runSolution(true);
        // Compare the two files
        File naiv = new File(NAIVE_FILENAME);
        File hash = new File(HASH_FILENAME);
        assertNotEquals(0L, naiv.length());
        assertEquals(naiv.length(), hash.length());
    }

    private void runSolution(boolean isHashSolution) {
        long startTime = System.nanoTime();
        if (isHashSolution) {
            System.out.print("\nRunning allNearestPointsHash()... ");
            np.allNearestPointsHash();
        } else {
            File naiveSolutionFile = new File(NAIVE_FILENAME);
            if (naiveSolutionFile.exists()) {
                System.out.println("\nSkipping allNearestPointsNaive() since 'NaiveSolution.txt' already exists.\n" +
                        "This is to save time. To run allNearestPointsNaive(), delete the file and run again.");
                return;
            } else {
                System.out.print("\nRunning allNearestPointsNaive(). This may take a while... ");
                np.allNearestPointsNaive();
            }
        }
        long endTime = System.nanoTime();
        double elapsed = (endTime - startTime) * 1.0e-9;
        System.out.printf("Done.\nTotal elapsed time: %.3f seconds\n", elapsed);
    }
}