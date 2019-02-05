
public class KiasuParameter implements Cryptable{
	protected static byte[] EPSILON = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 

	byte[][] eachOne;
	int number; // kaç tane var
	int n;  //bir tanesi kaç byte - random 128 bit, 16 byte
	public KiasuParameter(byte[] par)
	{
		n=16;
	number = par.length/n;
	eachOne = parcalama(par);
	}
	public KiasuParameter(int size){
		eachOne = new byte[size][16];
		number =size;
		n=16;
	}
	public byte[] getOne(int n)
	{
		
		return eachOne[n];
	}
	public Block getAsBlock(int n)
	{
		return new Block(eachOne[n]);
	}
	public byte[] getPadded()
	{
		return eachOne[number-1]; //sonuncuyu döndürecek
	}
	public Block getPaddedAsBlock()
	{
		return new Block(eachOne[number-1]);
	}
	public byte[] toByteArray()
	{
		byte[] all = new byte[n*number];
		for(int i=0;i<number;i++)
			for(int j=0;j<n;j++)
				all[i*n+j] = eachOne[i][j];
		
		return all;
	}
	public Block[] toBlockArray()
	{
		Block[] bs = new Block[number];
		int i =0;
		for(byte[] bys : eachOne)
			{
			bs[i] = new Block(bys);
			i++;
			}

		return bs;
	}
	 protected byte[][] parcalama(byte[] A)
	    {
	    	int l = A.length;
	    	int numberOfA = l/16; // 128 bitlik kaç tane A olacak
	    	byte AA[][] = new byte[numberOfA+1][16];
	    	int i=0;
	    	for(i=0;i<l;i++)
	    		if(i/16<numberOfA)
	    			AA[i/16][i%16]=A[i];//A nın içindeki byteları 16 lık parçalara ayrılıyor
	    		else
	    			break; //son array doldurulmaz. Buraya padding yapılmış A* konulacaktır.
	    	byte[] A_Star= new byte[l-i+1];
	    	for(int k=i;k<l;k++)
	    		A_Star[k-i] = A[k]; 
	    	AA[numberOfA] = padding10(A_Star);
	    	return AA;
	    }
	    /*
	     * Tanım: X||1||0^(n-|X|-1)
	     */
		private byte[] padding10(byte[] aStar) {
			// TODO Auto-generated method stub
			if(aStar.length<=1)
				return EPSILON;//eğer astar yoksa  yani A parçalaması tam olarak gerçekleştiyse
			byte[] pA = new byte[16];
			int i=0;
			for(i =0;i<aStar.length;i++)
				pA[i]=aStar[i];
			pA[i]=(byte)1;
			for(int k=i+1;k<16;k++)
				pA[k]=0;
			return pA;
		}
		public void setBlock(int i, Block b)
		{
			eachOne[i] = b.toByteArray();
		}
}
