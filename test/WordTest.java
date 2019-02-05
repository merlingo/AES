import static org.junit.Assert.*;

import org.junit.Test;


public class WordTest {

	Word w;
	@Test
	public void updateByteTest() {
		w = new Word(new byte[]{ 0x31, 0x01, 0x02, 0x04});
		w.updateByte(1, (byte) 0x11);
		assertEquals(w.getByte(1), 17);
	}
	@Test
	public void rotWordTest() {
		w =  new Word(new byte[]{ 0x31, 0x01, 0x02, 0x04});
		w.rotWord();
		assertEquals(w.getByte(0), 0x01);
	}
	@Test
	public void exorTest() {
		w =  new Word(new byte[]{ 0x2b, 0x01, 0x02, 0x04});
		Word we = w.exor(new Word(new byte[]{ (byte) 0x8a, 0x01, 0x01, 0x08 })).exor(new Word(new byte[]{ 0x01, 0x01, 0x01, 0x08 }));
		assertArrayEquals(we.getAllBytes(), new byte[] { (byte) 0xa0, 0x01, 0x02, 0x04});
	}
	
	

}
