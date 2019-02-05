/**
 * Created by yiğido on 13.1.2015.
 */
public class Word {
   private byte[] bytes;

    public Word(byte[] bs){
    	bytes = new byte[4];
    	int i = 0;
    	for(byte b : bs)
        bytes[i++] = b;
    }
    public Word() {
		// TODO Auto-generated constructor stub
    	bytes = new byte[4];
	}
	
    public void updateByte(int i, byte b){
        bytes[i] = b;
    }
    public byte getByte(int i){
        return bytes[i];
    }
    public void rotWord(){
        byte temp = bytes[0];
        for(int i = 0;i<3;i++)
            bytes[i] = bytes[i+1];
        bytes[3] = temp;

    }
	public Word exor(Word word) {
		// TODO Auto-generated method stub
		byte[] bs = new byte[4];
		for(int i=0;i<4;i++)
			bs[i] =(byte) ((byte) bytes[i] ^ word.getByte(i));
		
		return new Word(bs);
	}
	public byte[] getAllBytes(){
		return bytes;
	}
}
