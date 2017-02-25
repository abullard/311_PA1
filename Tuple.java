/**
 * @author Erin Elsbernd, Austin Bullard
 *
 */
public class Tuple {

    private int keyP;
    private float valueP;

    public Tuple(int keyP, float valueP) {
        this.keyP = keyP;
        this.valueP = valueP;
    }

    public int getKey() {
        return keyP;
    }

    public float getValue() {
        return valueP;
    }

    public boolean equals(Tuple t) {
        return t.keyP == this.keyP && t.valueP == this.valueP;

    }
}
