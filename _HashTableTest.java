import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for the HashTable class.
 *
 * @author nkarasch
 */
public class _HashTableTest {
    // 20 total items in the testData. a=3, b=7
    // Numbers in comments to the right show hash at p=11, 23, 47
    private static final Tuple[] testData = {
            // INITIAL: testData[0:6]
            new Tuple(1, 1.1f),    // 10, 10, 10
            new Tuple(2, 2.2f),    // 2, 13, 13
            new Tuple(3, 3.3f),    // 5, 16, 16
            new Tuple(3, 3.0f),    // 5, 16, 16
            new Tuple(4, 4.4f),    // 8, 19, 19
            new Tuple(5, 5.5f),    // 0, 22, 22
            new Tuple(6, 6.6f),    // 3, 2, 25
            // A:  testData[7]
            new Tuple(7, 7.7f),    // 6, 5, 28   // array grows to size=23 after this element is added
            // B:  testData[8:15]
            new Tuple(7, 7.0f),    // 6, 5, 28
            new Tuple(8, 8.8f),    // 9, 8, 31
            new Tuple(9, 9.9f),    // 1, 11, 34
            new Tuple(-17, -3.556666f),   // 0, 2, 3
            new Tuple(2000000, 13.69e7f), // 2, 20, 34
            new Tuple(20, 20.2f), // 1, 21, 20
            new Tuple(21, 21.21f), // 4, 1, 23
            new Tuple(22, 22.22f), // 7, 4, 26
            // C:  testData[16]
            new Tuple(23, 23.23f), // 10, 7, 29 // array grows to size=47 after this element is added
            // D:  testData[17:19]
            new Tuple(23, 23.0f),  // 10, 7, 29
            new Tuple(24, 24.24f), // 2, 10, 32
            new Tuple(25, 25.25f)  // 5, 13, 35
    };

    private _MockHashTable ht;

    @Before
    public void setUp() throws Exception {
        ht = new _MockHashTable(11);
        ht.mockHashFunction.setA(3);
        ht.mockHashFunction.setB(7);
        // Add the first 7 items
        for (int i = 0; i < 7; i++) {
            ht.add(testData[i]);
        }
    }

    @Test
    public void maxLoad() throws Exception {
        assertEquals(2, ht.maxLoad());
        
        ht.add(testData[7]);
        assertEquals(2, ht.maxLoad());
        
        for (int i = 8; i < 16; i++) {
            ht.add(testData[i]);
        }
        assertEquals(2, ht.maxLoad());
        
        ht.add(testData[16]);
        assertEquals(2, ht.maxLoad());
        
        for (int i = 17; i < 20; i++) {
            ht.add(testData[i]);
        }
        assertEquals(2, ht.maxLoad());
    }

    @Test
    public void averageLoad() throws Exception {
        assertEquals(0, Float.compare((float) 7 / 6, ht.averageLoad()));
        add('A');
        assertEquals(0, Float.compare((float) 8 / 7, ht.averageLoad()));
        add('B');
        assertEquals(0, Float.compare((float) 16 / 13, ht.averageLoad()));
        add('C');
        assertEquals(0, Float.compare((float) 17 / 14, ht.averageLoad()));
        add('D');
        assertEquals(0, Float.compare((float) 20 / 16, ht.averageLoad()));
    }

    @Test
    public void size() throws Exception {
        assertEquals(11, ht.size());
        add('A');
        assertEquals(23, ht.size());
        add('B');
        assertEquals(23, ht.size());
        add('C');
        assertEquals(47, ht.size());
        add('D');
        assertEquals(47, ht.size());
    }

    @Test
    public void numElements() throws Exception {
        assertEquals(7, ht.numElements());
        add('A');
        assertEquals(8, ht.numElements());
        add('B');
        assertEquals(16, ht.numElements());
        add('C');
        assertEquals(17, ht.numElements());
        add('D');
        assertEquals(20, ht.numElements());
    }

    @Test
    public void loadFactor() throws Exception {
    	float expected = 0.0f;
    	expected = (float) 7 / 11;
    	//assertEquals(expected, ht.loadFactor(), delta);
        assertTrue(0 == Float.compare(expected, ht.loadFactor()));
        add('A');
        assertTrue(0 == Float.compare((float) 8 / 23, ht.loadFactor()));
        add('B');
        assertTrue(0 == Float.compare((float) 16 / 23, ht.loadFactor()));
        add('C');
        assertTrue(0 == Float.compare((float) 17 / 47, ht.loadFactor()));
        add('D');
        assertTrue(0 == Float.compare((float) 20 / 47, ht.loadFactor()));
    }

    @Test
    public void search() throws Exception {
        add('A');
        add('B');
        add('C');

    	ArrayList<Tuple> arr;
        for (int i=0;i<18;i++) {
        	arr = ht.search(i);
            if (arr.size() > 2) {
            	System.out.println("Hey! It's 3 at index: " + i);
            }
        	//assertEquals(2, arr.size());
        }
        add('D');

        // Should return [ <3, 3.3>, <3, 3.0> ]
        //ArrayList<Tuple> 
        arr = ht.search(3);
        assertEquals(2, arr.size());
        Tuple tup = new Tuple(3, 3.3f);
        assertTrue(arr.get(0).equals(tup));
        tup = new Tuple(3, 3.0f);
        assertTrue(arr.get(1).equals(tup));

        // Should return [ <-17, -3.556666> ]
        arr = ht.search(-17);
        assertEquals(1, arr.size());
        tup = new Tuple(-17, -3.556666f);
        assertTrue(arr.get(0).equals(tup));

        // Should return [ <2000000, 13.69e7> ]
        arr = ht.search(2000000);
        assertEquals(1, arr.size());
        tup = new Tuple(2000000, 136900000f);
        assertTrue(arr.get(0).equals(tup));
    }

    @Test
    public void remove() throws Exception {
        assertEquals(7, ht.numElements());
        add('A');
        add('B');
        add('C');
        assertEquals(17, ht.numElements());
        
        ArrayList<Tuple> arr = ht.search(3);
        assertEquals(2, arr.size());

        // Remove one
        boolean success = ht.remove(new Tuple(2, 2.2f));
        assertTrue(success);

        // Remove another
        success = ht.remove(new Tuple(3, 3.3f));
        assertTrue(success);
        assertEquals(15, ht.numElements());
        arr = ht.search(3);
        assertEquals(1, arr.size());

        // Try to remove one that's already been removed
        success = ht.remove(new Tuple(3, 3.3f));
        assertFalse(success);
        assertEquals(15, ht.numElements());
    }

    private void add(char section) {
        switch (section) {
            case 'A':
                ht.add(testData[7]);
                break;
            case 'B':
                for (int i = 8; i < 16; i++) {
                    ht.add(testData[i]);
                }
                break;
            case 'C':
                ht.add(testData[16]);
                break;
            case 'D':
                for (int i = 17; i < 20; i++) {
                    ht.add(testData[i]);
                }
                break;
            default:
                break;
        }
    }
}