import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author nkarasch
 */
public class _TupleTest {
    @Test
    public void equals() throws Exception {
        Tuple tup1 = new Tuple(3, (float) 3.3);
        Tuple tup2 = new Tuple(3, (float) 3.3);
        assertTrue(tup1.equals(tup2));
        assertTrue(tup2.equals(tup1));

        // Different values
        tup2 = new Tuple(3, (float) 3.0);
        assertFalse(tup1.equals(tup2));
        // Different keys
        tup2 = new Tuple(2, (float) 3.3);
        assertFalse(tup1.equals(tup2));
        // Different key and value
        tup2 = new Tuple(2, (float) 2);
        assertFalse(tup1.equals(tup2));
    }
}