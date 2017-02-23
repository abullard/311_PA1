import java.util.ArrayList;

/**
 * Created by Austin Bullard on 2/17/17.
 *
 */
public class HashTable {

    //Instance Fields
    private int numTuples;
    private int p;
    private HashFunction h;
    private ArrayList[] list;

    public HashTable(int size) {

        //find next largest prime number after size
        while(true) {
            size++;
            if(isPrime(size)) {
                p = size;
                break;
            }
        }

        //create hash function with p as range
        h = new HashFunction(p);

        //create Hash Table using ArrayList, of capacity p
        list = new ArrayList[p];

        //initalize list to null
        for(int i = 0; i < p; i++)
            list[i] = null;
    }


    public int maxLoad() {
        int count = 0;
        for(int i = 0; i < p; i++) {
            if(list[i] == null)
                continue;
            count += list[i].size();
        }
        return count;
    }

    public void averageLoad() {

    }

    public int size() {
        return p;
    }

    public int numElements() {
        return numTuples;
    }

    public int loadFactor() {
        return numElements()/size();
    }

    public void add(Tuple t) {
        numTuples++;

    }

    public ArrayList<Tuple> search(int k) {
        return new ArrayList<Tuple>();
    }

    public void remove(Tuple t) {

    }

    /**
     * Returns true if
     * @param x
     *  number being checked
     * @return
     *  true if x is prime, false otherwise
     */
    private boolean isPrime(int x) {
        if (x % 2 == 0) { return false; }

        for(int i = 3; i * i <= x; i += 2) {
            if(x % i == 0) { return false; }
        }
        return true;
    }
}
