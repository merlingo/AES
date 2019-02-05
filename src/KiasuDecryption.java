
public class KiasuDecryption extends AES128Decryption {

	protected Tweak tweak;
	public KiasuDecryption(Block key){
		super(key);
		this.tweak=null;
	}
	public KiasuDecryption(Block key, Tweak tweak) {
		
		super(key);
		this.tweak = tweak;
		// TODO Auto-generated constructor stub
	}
	public void setTweak(Tweak t)
	{
		tweak = t;
	}

}
