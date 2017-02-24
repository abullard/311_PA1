import static junit.framework.Assert.*;

/**
 * Unit tests for HashFunction class.
 *
 * @author nkarasch
 */
public class _HashFunctionTest {
    private static final int[] somePrimes = {3163, 3167, 3169, 3181, 3187, 3191, 3203, 3209, 3217, 1299827};

    @org.junit.Test
    public void hash() throws Exception {
        // 3217 is prime, so p = 3217
        HashFunction hf = new HashFunction(3217);
        hf.setA(100);
        hf.setB(99);
        // hash(int x) returns (a*x + b) % p
        assertEquals(1299, hf.hash(12));
        // tests ints that partially calculate larger than max int
        assertEquals(1828, hf.hash(32123789));
        // Negative numbers
        assertEquals(1616, hf.hash(-17));
    }

    @org.junit.Test
    public void setA() throws Exception {
        // 101 is prime, so p = 101
        HashFunction hf = new HashFunction(101);
        hf.setA(5);
        assertEquals(5, hf.getA());
        hf.setA(105);
        assertEquals(4, hf.getA());
    }

    @org.junit.Test
    public void setB() throws Exception {
        // 101 is prime, so p = 101
        HashFunction hf = new HashFunction(101);
        hf.setB(6);
        assertEquals(6, hf.getB());
        hf.setB(106);
        assertEquals(5, hf.getB());
    }

    @org.junit.Test
    public void setP() throws Exception {
        // This test also tests functionality in the constructor (finding a prime)
        HashFunction hf = new HashFunction(2);
        assertEquals(2, hf.getP());
        for (int i = 1; i < somePrimes.length; i++) {
            hf.setP(somePrimes[i] - 1);
            assertEquals(somePrimes[i], hf.getP());
            hf.setP(somePrimes[i]);
            assertEquals(somePrimes[i], hf.getP());
            hf.setP(somePrimes[i] + 1);
            assertFalse(somePrimes[i] == hf.getP());
        }
    }
}