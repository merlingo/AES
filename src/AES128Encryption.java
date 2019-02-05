import javax.xml.crypto.KeySelector;

/**
 * Created by yiğido on 22.12.2014.
 */
public class AES128Encryption extends Encryption {
    public AES128Encryption(Block key) {
        super(key);
    }

     static Block subByte( Block stte){
        //use s-box for each element in sttle, s-box arrayinden veriyi al. 0 dan 256 ya kadar butun de�erlerin kar��l��� var
        byte b;
        int r,c;
    	 for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++){
            	int bb = stte.getElement(row,col) ;
            	bb = bb < 0 ? bb+256 : bb;
            	r = bb / 16;
            	c = bb % 16;
            	b = (byte)sbox[r][c];
                stte.addElement(b, row, col);
            }     
        return stte;
    }
     static Block shiftRow(Block stte){
        //ikinci row bir shift edilir -- 2. rowun birincisi 4e, 4 3e, 3 2ye, 2 1e
        for(int i=1;i<4;i++){
            for(int j=0; j<i;j++)// how many times row of state is shifted right
                stte.shiftRowToRight(i);
        }
        return stte;

    }


     static Block mixColumn(Block stte){
        // 02 ile çarpma ve 03 ile çarpma fonksiyonlarına ihtiyaç var
        // her column icin aynı islem uygulanacak
        byte[] shteWord = new byte[4];
        byte[] ws = new byte[4];
        for(int ColumnIndex=0;ColumnIndex<4;ColumnIndex++){
           Word word = stte.getWord(ColumnIndex);
           ws = word.getAllBytes();
            for(int wordIndex=0; wordIndex<4;wordIndex++ )
            {
                shteWord[wordIndex] =(byte)( multiply02(ws[wordIndex%4]) ^multiply03(ws[(wordIndex+1)%4])^ws[(wordIndex+2)%4]^ws[(wordIndex+3)%4]);
            }
            stte.changeWord(ColumnIndex,shteWord);
        }
        return stte;

    }
     static byte multiply02(byte b){
        //02 byte ile çarpma işlemi yani x ile çarpma
         int pos =  ((b >> 7) &  0x0001);
        if(pos ==1)
        {
            b = (byte)(b<<1);
            b = (byte)(b ^ 0x1b) ;
        }
        else
        {
            b = (byte)(b<<1);

        }
        return b;
    }
     static byte multiply03(byte b){
        return (byte)(b ^ multiply02(b));
    }
    //key schedule oluşturulmalı, key schedule içerisinde roundkeyler oluşturulmalı ve buralara verilmeli
     Block addRoundKey(Block stte, Block key)
    {
    	 for(int row=0;row<4;row++){
    		 for(int col=0;col<4;col++)
    		 stte.addElement((byte) (stte.getElement(row, col) ^ key.getElement(row, col)),row,col);
    		 
    	 }
        return stte;

    }
    
      Block round(Block st, Block rkey){
        Block resultSt = addRoundKey(mixColumn(shiftRow(subByte(st))), rkey);
        return resultSt;
    }
    @Override
    public  Block exec(Block message){
        Block state = message;
     //   Block key = new Block(keyArr);


        Word[] words ;//round keyler üretilmeli
        words = KeySchedule.produceRoundKeys(key);
        Block rKey = new Block(new Word[]{words[0], words[1], words[2], words[3]});
        addRoundKey(state, rKey);
        int i;
        for(i=0;i<9;i++){
        	rKey = new Block(new Word[]{words[4*(i+1)], words[4*(i+1)+1], words[4*(i+1)+2], words[4*(i+1)+3]});
            state=round(state,rKey);
        }
    	rKey = new Block(new Word[]{words[4*(i+1)], words[4*(i+1)+1], words[4*(i+1)+2], words[4*(i+1)+3]});
        state = addRoundKey(shiftRow(subByte(state)), rKey);

        return state;
    }


}
