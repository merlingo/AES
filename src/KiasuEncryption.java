
public class KiasuEncryption extends AES128Encryption{

	protected Tweak tweak;
	public KiasuEncryption(Block key){
		super(key);
		this.tweak=null;
	}
	public KiasuEncryption(Block key, Tweak tweak) {
		super(key);
		this.tweak = tweak;
		// TODO Auto-generated constructor stub
	}
	public void setTweak(Tweak t)
	{
		tweak = t;
	}
	 Block addRoundKey(Block stte, Block key)
    {
		 for(int row=0;row<4;row++){
    		 for(int col=0;col<4;col++)
    		 stte.addElement(((byte) (stte.getElement(row, col) ^ key.getElement(row, col)^tweak.getElement(row, col))),row,col);
    		 
    	 }
        return stte;

    }
	 
}
