import java.util.ArrayList;

/**
 * @author Erin Elsbernd, Austin Bullard
 *
 */
public class HashTable {

    //Instance Fields
    private int numTuples;
    private int numBuckets = 0;
    private int p;
    private HashFunction h;
    private ArrayList[] list;

    public HashTable(int size) {

        //find next largest prime number after size
        while(true) {
            
            if(isPrime(size)) {
                p = size;
                break;
            }
            size++;
        }
        //System.out.println("argument size inConstructor: " + size);
        //System.out.println("argument p inConstructor: " + p);

        //create hash function with p as range
        h = new HashFunction(p);

        //create Hash Table using ArrayList, of capacity p
        list = new ArrayList[p];
        System.out.println("HT listSize inConstructor: " + list.length);

        //initialize list to null
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

    public float averageLoad() {
        /** int count = 0, avg = 0;
        for(int i = 0; i < p; i++) {
            if(list[i] != null) {
                count++;
                avg += list[i].size();
            }
        }
        return (float) avg / (float) count; 
        */
    	return (float) numTuples / (float) numBuckets;
    }

    public int size() {
        return p;
    }

    public int numElements() {
        return numTuples;
    }

    public float loadFactor() {
        return (float)numElements()/(float)size();
    }

    public void add(Tuple t) {
        //increase the number of tuples by 1, and add the tuple to the hash table
        numTuples++;
        
        int key = t.getKey();
        int hash = hash(key);
        try {
	        if (list[hash] == null) {
	        	list[hash] = new ArrayList<Tuple>();
	        	numBuckets++;
	        }
	        list[hash].add(t);
        } catch (Exception e) {
        	e.printStackTrace();
        	
        }
        
        
        
        //if load factor is greater than 0.7, resize and rehash table
        if(loadFactor() > 0.7) {
            resize();
        }

    }

    public ArrayList<Tuple> search(int k) {
    	ArrayList<Tuple> tuples = new ArrayList<Tuple>();
    	
    	int hashedKey = hash(k);
    	//System.out.println("hashedKey: " + hashedKey);
    	if (list[hashedKey] != null) {
    		for (int j = 0; j < list[hashedKey].size(); j++) {
    			Tuple t = (Tuple)list[hashedKey].get(j);
    	    	//System.out.println("Tuple" + j + ": " +  hashedKey + " " +t.getKey() + " " + t.getValue());
    			if (k == t.getKey())
    				tuples.add(t);
    		}
    	}
    	
        return tuples;
    }

    public boolean remove(Tuple t) {
    	boolean result = false;
    	int hash = hash(t.getKey());
    	if (list[hash] != null) {
    		for (int j = 0; j < list[hash].size(); j++) {
    			Tuple tupsMcGee = (Tuple) list[hash].get(j);
    	    	if (tupsMcGee.getValue() == t.getValue()) {
    	    		list[hash].remove(j);
    	    		if (list[hash].size() == 0) {
    	    			list[hash] = null;
    	    			numBuckets--;
    	    		}
    	    		numTuples--;
    	    		result = true;
    	    		break;
    	    		
    	    	}
    		}
    	}
      
        return result;
    }

    /**
     * Helper method, approximately doubles the size of the hash table
     */
    private void resize() {
        //find next largest prime number after 2 * size
        int temp = 2 * p;
        while(true) {
        	//temp++;
            if(isPrime(temp)) {
                p = temp;
                break;
            }
            temp++;
        }
        rehash();
    }

    
    /**
     * helper method to update hash function
     */
    protected void updateHashFunction(int x) {
    	h = new HashFunction(x);
    }
    
    protected int hash(int x) {
        return h.hash(x);
    }
    
    /**
     * Helper method, rehashes all the elements of the hash table,
     * called from resize, after the size has been updated
     */
    private void rehash() {
        //create new hash table with updated size p
        ArrayList[] newList = new ArrayList[p];
        updateHashFunction(p); 
        numBuckets = 0;
        
        for(int i = 0; i < list.length; i++) {
            if(list[i] != null) {
                for(int j = 0; j < list[i].size(); j++) {

                    //create a new tuple from the given position of the iterations
                    Tuple t = (Tuple) list[i].get(j);

                    int key = t.getKey();
                    int hash = hash(key);
                    //rehash tuple, add to the new list
                    if (newList[hash] == null) {
                    	newList[hash] = new ArrayList<Tuple>();
                    	numBuckets++;
                    }
                    newList[hash].add(t); 
                }
            }
        }
        list = newList.clone();
    }

    /**
     * helper method to set the hash function
     */
    public void setHashFunction(int a, int b) {
    	h.setA(a);
    	h.setB(b);
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
