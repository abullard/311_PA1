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


    private Long p;
    private int a, b;

    public HashFunction(int range) {
        Random rand = new Random();

        while(true) {
            range++;
            if(isPrime(range)) {
                long temp = range;
                p = temp;
                break;
            }
        }

        int temp = Integer.parseInt(p.toString());
        a = rand.nextInt(temp);
        b = rand.nextInt(temp);
    }

    public Long hash(int x) {
    	System.out.println("a: " + a);
    	System.out.println("x: " + x);
    	System.out.println("b: " + b);
    	System.out.println("p: " + p);
        return (a*x + b) % p;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public Long getP() {
        return p;
    }

    public void setA(int x) {
        int temp = Integer.parseInt(p.toString());
        a = x % temp;
    }

    public void setB(int y) {
        int temp = Integer.parseInt(p.toString());
        b = y % temp;
    }

    public void setP(int x) {
        while(true) {
            x++;
            if(isPrime(x)) {
                long temp = x;
                p = temp;
                break;
            }
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
        if (x % 2 == 0) { return false; }

        for(int i = 3; i * i <= x; i += 2) {
            if(x % i == 0) { return false; }
        }
        return true;
    }
}
