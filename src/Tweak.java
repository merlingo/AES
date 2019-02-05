
public class Tweak extends Block {

	/*
	 * Tanım: bytelar 64 bit olarak alınır.
	 * Constructorda usülüne uygun 0'lar eklenir.
	 * super constructor(Block)'a öyle verilir.
	 */
	public Tweak(byte[] bytes) {
		
		super(bytes);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean fill(byte[] byts)
	{ 
		int size = 2;
		if(byts.length != 8)
		{
			try {
				throw new Exception("HATA: Tweak 64 bit olmalı");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
    else{
        for(int c=0;c<size;c++){
            for(int r=0;r<2;r++)
                addElement(byts[4*c+r],r,c);
            addElement((byte) 0,2,c);	
            addElement((byte) 0,3,c);
        }
        return true;
    }
	}
	

	
}
