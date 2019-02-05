/**
 * Created by yiğido on 22.12.2014.
 */
public class AES128 extends AES {
    public AES128(Block key) {
        super(key);
        encryption = new AES128Encryption(key);
        decryption = new AES128Decryption(key);
    }

}
