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
      int max = 0;
      for(int i = 0; i < p; i++) {
          if(list[i] != null)
              if(list[i].size() > max)
                  max = list[i].size();
          }
      return max;
    }

    public double averageLoad() {
        int count = 0, avg = 0;
        for(int i = 0; i < p; i++) {
            if(list[i] != null) {
                count++;
                avg += list[i].size();
            }
        }
        return (double) avg / count;
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
        //increase the number of tuples by 1, and add the tuple to the hash table
        numTuples++;
        
        int key = t.getkey();
        System.out.println(key);
        long hash = h.hash(key);
        System.out.println(hash);
        if (list[(int) hash] == null) {
        	list[(int) hash] = new ArrayList<Tuple>();
        }
        list[(int) hash].add(t);
        
        // list[h.hash(t.getkey())].add(t);
        
        
        //if load factor is greater than 0.7, resize and rehash table
        if(loadFactor() > 0.7) {
            resize();
        }

    }

    public ArrayList<Tuple> search(int k) {
    	ArrayList<Tuple> tuples = new ArrayList<Tuple>();
    	
    	int hashedKey = h.hash(k).intValue();
    	if (list[hashedKey] != null) {
    		for (int j = 0; j < list[hashedKey].size(); j++) {
    			tuples.add((Tuple)list[hashedKey].get(j));
    		}
    	}
    	
        return tuples;
    }

    public void remove(Tuple t) {
        for(int i = 0; i < p; i++) {
            if(list[i] != null)
                if(list[i].contains(t)) {
                    list[i].remove(t);
                    break;
                }
        }
    }

    /**
     * Helper method, approximately doubles the size of the hash table
     */
    private void resize() {
        //find next largest prime number after 2 * size
        int temp = 2 * p;
        while(true) {
            temp++;
            if(isPrime(temp)) {
                p = temp;
                break;
            }
        }
        rehash();
    }

    /**
     * Helper method, rehashes all the elements of the hash table,
     * called from resize, after the size has been updated
     */
    private void rehash() {
        //create new hash table with updated size p
        ArrayList[] newList = new ArrayList[p];

        for(int i = 0; i < list.length; i++) {
            if(list[i] != null) {
                for(int j = 0; j < list[i].size(); j++) {

                    //create a new tuple from the given position of the iterations
                    Tuple t = (Tuple) list[i].get(j);

                    int key = t.getkey();
                    long hash = h.hash(key);
                    //rehash tuple, add to the new list
                    newList[(int) hash].add(t);
                }
            }
        }
        list = newList;
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
