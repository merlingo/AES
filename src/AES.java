/**
 * Created by yiğido on 22.12.2014.
 */
public abstract class AES extends BlockCipher {


    public AES(Block key) {
        super(key);
    }
    Encryption encryption;
    Decryption decryption;


    @Override
    public Cryptable encrypt(Cryptable message) {
        return encryption.exec((Block)message);
    }
    @Override
    public Block decrypt(Cryptable cipher) {
        return decryption.exec((Block)cipher);
    }


}
