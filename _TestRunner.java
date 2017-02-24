import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runs all unit tests.
 * Be sure to add the following external libraries to your classpath:
 * - hamcrest-core-1.3.jar
 * - junit-4.12.jar
 */
public class _TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(_TestSuite.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("\nPASS - All tests passed!");
        } else {
            System.out.println("\nFAIL - One or more tests failed.");
        }
    }
}
