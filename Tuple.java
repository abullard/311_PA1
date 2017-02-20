/**
 * Created by Austin Bullard on 2/17/17.
 *
 */
public class Tuple {

    private int keyP;
    private float valueP;

    public Tuple(int keyP, float valueP) {
        this.keyP = keyP;
        this.valueP = valueP;
    }

    public int getkey() {
        return keyP;
    }

    public float getValue() {
        return valueP;
    }

    public boolean equals(Tuple t) {
        return t.keyP == this.keyP && t.valueP == this.valueP;

    }
}
