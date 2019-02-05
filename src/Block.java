/**
 * Created by yiğido on 22.12.2014.
 */
public class  Block implements Keyable, Cryptable  {
    protected byte[][] byteList;


    private int size;

    public Block()
    {
    }
    
    public Block(byte[] bytes){
        size=4;
        byteList = new byte[4][size];
        fill(bytes);
    }
    public Block(Word[] words){
    	 size=4;
         byteList = new byte[4][size];
         byte[] bytes = new byte[16];
         byte[] ws = new byte[4];
         for(int i=0;i<4;i++){
        	 ws = words[i].getAllBytes();
        	 for(int j = 0; j<4;j++){
        		 bytes[(4*i)+j] = ws[j];
        	 }
         }
         
    fill(bytes);
    }
    
    public Block(int s,byte[] bytes){
        size = s;
        byteList = new byte[4][s];
        fill(bytes);

    }
    
    public int getSize(){
        return size;
    }
    protected void addElement(Byte b, int row, int column){
        byteList[row][column] = b;
    }
    @Override
    public boolean fill(byte[] byts){
        if(byts.length != 4*size)
            return false;
        else{
            for(int c=0;c<size;c++){
                for(int r=0;r<4;r++)
                    addElement(byts[4*c+r],r,c);

            }
            return true;
        }
    }
    public byte getElement(int row, int column){

        return byteList[row][column];
    }

    public int changeElement(int[] indx1, int[] indx2) throws Exception{//yanl��
        //indx1 i alcak, tempe koycak, index 2 dekini 1 e koycak temp i de indx 2 ye koycak
        Byte temp;
        if(indx1.length!=2 || indx2.length!=2)
        {
            throw new Exception("HATA 'changeElement()': indeksler arraylerinin eleman sayısı 2 değildir.");

        }
        temp = getElement(indx1[0],indx1[1]);
        addElement(getElement(indx2[0],indx2[1]), indx1[0], indx1[1]);
        addElement(temp, indx2[0], indx2[1]);
        return 1;
    }
    public byte[] toByteArray()
    {
        byte[] bArray = new byte[16];
        for(int c=0;c<size;c++){
            for(int r=0;r<4;r++)
                bArray[4*c+r] = getElement(r,c);
        }
        return bArray;
    }
    public void shiftRowToRight(int row){
        Byte temp = byteList[row][3];
        byteList[row][3] = byteList[row][0];
        byteList[row][0]=byteList[row][1];
        byteList[row][1] = byteList[row][2];
        byteList[row][2] = temp;
    }
    public Word getWord(int cn){
        byte[] wordb = new byte[4];
        for(int i=0;i<4;i++){
            wordb[i] = byteList[i][cn];
        }
        return new Word(wordb);
    }
    public void changeWord(int cn, byte[] word){
        for(int i=0;i<4;i++){
             byteList[i][cn]=word[i] ;
        }
    }
    public Block exor(Block other)
    {
    	
    	byte[] bs = new byte[16];
    	byte[] bytes = toByteArray();
    	byte[] otherBytes=other.toByteArray();
		for(int i=0;i<16;i++)
			bs[i] =(byte) ((byte) bytes[i] ^ otherBytes[i]);
		
		
    	return new Block(bs);
    }
}
