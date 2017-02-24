/**
 * This class is used for unit testing the HashTable class.
 * It lets us set the values of A, B, P in the hash function for repeatability.
 *
 * @author nkarasch
 */
public class _MockHashTable extends HashTable {
    public HashFunction mockHashFunction;

    public _MockHashTable(int size) {
        super(size);
        mockHashFunction = new HashFunction(size);
    }

    // This is a protected function in HashTable that just calls hashFunction.hash(x).
    // Since the hashFunction is a private variable, we create this helper function
    // to call hash on the hashFunction. That way, the hashFunction variable isn't
    // exposed, and anyone wanting to extend HashTable with a custom hash function
    // can do so like this.
    // For this mock test class, we have our own hash function to exercise some
    // control over repeatable results.
    //@Override
    protected int hash(int x) {
        return mockHashFunction.hash(x);
    }

    // This is a protected function in HashTable that just calls hashFunction.setP(p).
    // It gets called when the table size doubles and updates the hash function.
    // Again, this functionality is abstracted so that extensions of the class can
    // implement as needed.
    // For this mock test class, we want a and b to remain the same, even if the
    // table size doubles. Normally a and b are randomly generated when p is set
    // in the hash function.
    //@Override
    protected void updateHashFunction(int p) {
        int a = mockHashFunction.getA();
        int b = mockHashFunction.getB();
        mockHashFunction.setP(p);
        // Maintain the same a and b as before (for testing purposes)
        mockHashFunction.setA(a);
        mockHashFunction.setB(b);
    }
}
