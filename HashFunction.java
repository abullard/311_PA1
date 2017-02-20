import java.util.Random;

/**
 *  Class that represents a HashFunction
 *  @author Austin Bullard
 */
public class HashFunction {
    private int a, b, p;

    public HashFunction(int range) {
        Random rand = new Random();

        while(true) {
            range++;
            if(isPrime(range)) {
                p = range;
                break;
            }
        }

        a = rand.nextInt(p);
        b = rand.nextInt(p);
    }

    public float hash(int x) {
        return (a*x + b) % p;
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
        a = x % p;
    }

    public void setB(int y) {
        b = y % p;
    }

    public void setP(int x) {
        while(true) {
            x++;
            if(isPrime(x)) {
                p = x;
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
