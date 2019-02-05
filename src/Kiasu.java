import java.util.BitSet;


public class Kiasu extends AES{

	protected static byte[] EPSILON = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 
	Block Tag;
	KiasuParameter associatedData;
	BitSet N;
	public Kiasu(Block key, Cryptable assData,BitSet N) {
		super(key);
		// TODO Auto-generated constructor stub
		  encryption = new KiasuEncryption(key);
	        decryption =  new KiasuDecryption(key);
	       
	        associatedData = (KiasuParameter) assData;
	        this.N=N;
	    Tag = new Block();
	}
	 @Override
	    public KiasuParameter encrypt(Cryptable message) {
		 KiasuParameter mes = ((KiasuParameter) message);
		Block auth = new Block(EPSILON);
		BitSet Init = new BitSet(3);
		Init.set(1);
		 for(int n=1;n<=associatedData.number;n++)
		 {
			  BitSet index = new BitSet(32);
			  index.or(BitSet.valueOf(new byte[]{  (byte)n  })); //HATA riski büyük denenmeli
			 ((KiasuEncryption)encryption).setTweak(new Tweak(bitBirlestir(Init,N,index)));
			 auth = auth.exor(encryption.exec( associatedData.getAsBlock(n-1))); //Tweak oluşturmak için birleştirme() fonksiyonuna ihtiyaç vardır
		 }
		 
		 Init.clear();//burda 000 olarak kullanılıyor
		 Block checkSum = new Block(EPSILON);
		KiasuParameter Cler = new KiasuParameter(mes.number);
		 for(int n=1;n<=mes.number;n++)
		 {
			 checkSum = checkSum.exor(mes.getAsBlock(n) );
			  BitSet index = new BitSet(32);
			  index.or(BitSet.valueOf(new byte[]{ (byte)n  } )); //HATA riski büyük denenmeli
			 ((KiasuEncryption)encryption).setTweak(new Tweak(bitBirlestir(Init,N,index)));
			 Cler.setBlock(n-1, encryption.exec( mes.getAsBlock(n-1))); //Tweak oluşturmak için birleştirme() fonksiyonuna ihtiyaç vardır
		 }
	     //NOT: son mesajın epsilon olmayacağı ve padlenmiş olacağı varsayılıyor.
		 checkSum=checkSum.exor(mes.getPaddedAsBlock());
		 Init.set(0);
		  BitSet index = new BitSet(32);
		  index.or(BitSet.valueOf(new byte[]{ (byte)mes.number  } ));
		
		 ((KiasuEncryption)encryption).setTweak(new Tweak(bitBirlestir(Init,N,index)));
		 Block Pad = encryption.exec(new Block(EPSILON));
		 Cler.setBlock(mes.number, (mes.getPaddedAsBlock().exor(Pad)));
		 Init.set(2);
		 ((KiasuEncryption)encryption).setTweak(new Tweak(bitBirlestir(Init,N,index)));
		 Block Final = encryption.exec(checkSum);
		 
		 Tag = Final.exor(auth);
		 return Cler;
	    }
	    @Override
	    public Block decrypt(Cryptable cipher) {
	        return null;
	    }
	    
	   
//bitleri birleştir byte yap
	    public byte[] bitBirlestir(BitSet Initbits, BitSet N, BitSet index)
	    {
	    	//tek bitset de birlestir, bitset i byte arraye çevir
	    	BitSet birlesik = new BitSet(64);
	    	for(int i=0;i<Initbits.size();i++)
	    		birlesik.set(i, Initbits.get(i));
	    	for(int i=0;i<N.size();i++)
	    		birlesik.set(i, N.get(i));
	    	for(int i=0;i<index.size();i++)
	    		birlesik.set(i, index.get(i));
	    	
	    	return birlesik.toByteArray();
	    }
}
