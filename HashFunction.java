import java.util.Random;

/**
 *  Class that represents a HashFunction
 *  @author Austin Bullard
 */
public class HashFunction {

    public static void main(String[] args) {
        HashFunction h = new HashFunction(3);
        h.hash(10);
    }


    private int p;
    private int a, b;

    public HashFunction(int range) {
        Random rand = new Random();

        while(true) {
            
            if(isPrime(range)) {
                int temp = range;
                p = temp;
                break;
            }
            range++;
        }

        int temp = p;
        a = rand.nextInt(temp);
        b = rand.nextInt(temp);
    }

    public int hash(int x) {
    	Long ax = (long)a*(long)x;
    	//System.out.println("ax: " + ax);
    	//System.out.println("p: " + p);
    	Long axb = ax + b;
    	//System.out.println("axb: " + axb);
    	Long finalVal = axb % (long)p;
    	//System.out.println("finalVal: " + finalVal);
    	if (finalVal < 0) finalVal += p;
    	//System.out.println("finalVal2: " + finalVal);
    	if (finalVal >= p) {
        	System.out.println("ax: " + ax);
        	System.out.println("p: " + p);
        	System.out.println("axb: " + axb);
        	System.out.println("finalVal: " + finalVal);
    	}
        return finalVal.intValue();
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getP() {
        return p;
    }

    public void setA(int x) {
        int temp = p;
        a = x % temp;
    }

    public void setB(int y) {
        int temp = p;
        b = y % temp;
    }

    public void setP(int x) {
        while(true) {
            if(isPrime(x)) {
                int temp = x;
                p = temp;
                break;
            }
            x++;
        }
    }

    /**
     * Returns true if
     * @param x
     *  number being checked
     * @return
     *  true if x is prime, false otherwise
     */
    private boolean isPrime(int x) {
    	if (x == 2)     { return true; }
        if (x % 2 == 0) { return false; }

        for(int i = 3; i * i <= x; i += 2) {
            if(x % i == 0) { return false; }
        }
        return true;
    }
}
