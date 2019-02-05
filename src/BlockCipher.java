/**
 * Created by yiğido on 22.12.2014.
 */
public abstract class  BlockCipher {
    private Block key;

    protected BlockCipher(Block key) {
        this.key = key;
    }
    public abstract Cryptable encrypt(Cryptable message);
    public abstract Block decrypt(Cryptable cipher);
}
