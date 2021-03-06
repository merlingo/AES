import static org.junit.Assert.*;

import org.junit.Test;


public class AES128EncryptionTest {
	
	AES128Encryption aes ;
	@Test
	public void subByteTest() {

		Block state;
	 byte[] bytes = { 0x19, 0x3d, (byte) 0xe3, (byte) 0xbe, (byte) 0xa0, (byte) 0xf4, (byte) 0xe2, (byte) 0x2b ,(byte) 0x9a, (byte) 0xc6, (byte) 0x8d, 0x2a, (byte) 0xe9, (byte) 0xf8, (byte) 0x48, 0x08 };
	 state = new Block(bytes);
		Block state2 = AES128Encryption.subByte(state);
		assertArrayEquals(state2.getWord(0).getAllBytes(), new byte[]{(byte) 0xd4,0x27,0x11,(byte) 0xae });
	}
	
	@Test
	public void shiftRowTest() {
		Block state;
		 byte[] bytes = { 0x19, 0x3d, (byte) 0xe3, (byte) 0xbe, (byte) 0xa0, (byte) 0xf4, (byte) 0xe2, (byte) 0x2b ,(byte) 0x9a, (byte) 0xc6, (byte) 0x8d, 0x2a, (byte) 0xe9, (byte) 0xf8, (byte) 0x48, 0x08 };
		 state = new Block(bytes);
			Block state2 = AES128Encryption.subByte(state);
			assertArrayEquals((AES128Encryption.shiftRow(state2)).getWord(0).getAllBytes(), new byte[]{(byte) 0xd4,(byte) 0xbf,(byte) 0x5d,(byte) 0x30 });
			}
		
	
	@Test
	public void mixColumnTest() {

		Block state;
	 byte[] bytes = { 0x19, 0x3d, (byte) 0xe3, (byte) 0xbe, (byte) 0xa0, (byte) 0xf4, (byte) 0xe2, (byte) 0x2b ,(byte) 0x9a, (byte) 0xc6, (byte) 0x8d, 0x2a, (byte) 0xe9, (byte) 0xf8, (byte) 0x48, 0x08 };
	 state = new Block(bytes);
		Block state2 = AES128Encryption.subByte(state);
		assertArrayEquals(AES128Encryption.mixColumn(AES128Encryption.shiftRow(state2)).getWord(0).getAllBytes(), new byte[]{(byte) 0x04,0x66,(byte) 0x81,(byte) 0xe5 });	
		}
	
	@Test
	public void multiply02Test() {
		byte[] word = new byte[]{(byte) 0xd4,0x27,0x11,(byte) 0xae };
		assertEquals(AES128Encryption.multiply02((byte) 0xd4), (byte) 0xb3);
	}
	
	@Test
	public void roundTest() {
		Block rKey = new Block(new byte[]{(byte) 0xa0,(byte) 0xfa,(byte) 0xfe,0x17,(byte) 0x88,0x54,0x2c,(byte) 0xb1,0x23,(byte) 0xa3,0x39,0x39,0x2a,0x6c,0x76,0x05});
		Block state;
		 byte[] bytes = { 0x19, 0x3d, (byte) 0xe3, (byte) 0xbe, (byte) 0xa0, (byte) 0xf4, (byte) 0xe2, (byte) 0x2b ,(byte) 0x9a, (byte) 0xc6, (byte) 0x8d, 0x2a, (byte) 0xe9, (byte) 0xf8, (byte) 0x48, 0x08 };
		 state = new Block(bytes);
		 assertArrayEquals((new AES128Encryption(rKey)).round(state, rKey).toByteArray(),new byte[]{(byte) 0xa4,(byte) 0x9c,0x7f,(byte) 0xf2,0x68,(byte) 0x9f,0x35,0x2b,0x6b,0x5b,(byte) 0xea,0x43,0x02,0x6a,0x50,0x49});
	}
	@Test
	public void execTest() {
		 byte[] bytes = { 0x2b, 0x7e, (byte) 0x15, (byte) 0x16, (byte) 0x28, (byte) 0xae, (byte) 0xd2, (byte) 0xa6 ,(byte) 0xab, (byte) 0xf7, (byte) 0x15, (byte) 0x88, (byte) 0x09, (byte) 0xcf, (byte) 0x4f, 0x3c };
		Block Key =  new Block(bytes);
		Block state;
		 state = new Block(new byte[]{ 0x32, 0x43, (byte) 0xf6, (byte) 0xa8, (byte) 0x88, (byte) 0x5a, (byte) 0x30, (byte) 0x8d ,(byte) 0x31, (byte) 0x31, (byte) 0x98, (byte) 0xa2, (byte) 0xe0, (byte) 0x37, (byte) 0x07, 0x34 });
		 aes = new AES128Encryption(Key);
		 assertArrayEquals(aes.exec(state).toByteArray(),new byte[]{(byte) 0x39,(byte) 0x25,(byte) 0x84,(byte) 0x1d,0x02,(byte) 0xdc,0x09,(byte) 0xfb,(byte) 0xdc,0x11,(byte) 0x85,(byte) 0x97,0x19,0x6a,0x0b,0x32});
	
	}

}
