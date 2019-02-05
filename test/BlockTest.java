
import static org.junit.Assert.*;

import org.junit.Test;


public class BlockTest {

	Block b;
	static byte[] bytes = { 0x63, 0x7c, 0x77, 0x7b, (byte) 0xf2, 0x6b, 0x6f, (byte) 0xc5 ,0x30, 0x01, 0x67, 0x2b, (byte) 0xfe, (byte) 0xd7, (byte) 0xab, 0x76 };
	@Test
	public void addElementTest() {
		b = new Block(bytes);
		b.addElement((byte) 0x12, 1, 2);
		assertEquals(b.getElement(1, 2), 0x12);
	}
	@Test
	public void fillKeyTest() {
		b = new Block(bytes);
		b.fill(new byte[] { (byte) 0x8d, 0x01, 0x02, 0x04,    0x08, 0x10, 0x20, 0x40,    (byte) 0x80, 0x1b, 0x36, 0x6c,    (byte) 0xd8, (byte) 0xab, 0x4d, (byte) 0x9a });
		assertArrayEquals(b.toByteArray(), new byte[] { (byte) 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, (byte) 0x80, 0x1b, 0x36, 0x6c, (byte) 0xd8, (byte) 0xab, 0x4d, (byte) 0x9a });
		
	}
	@Test
	public void changeElementTest() {
		b = new Block(bytes);
		
		int[] i1 = {2,3};
		int[] i2 = {0,1};
		byte bnew = b.getElement(i2[0], i2[1]);
		try {
			b.changeElement(i1, i2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals( b.getElement(i1[0], i1[1]),bnew );
	}
	@Test
	public void shiftRowToRightTest() {
		b = new Block(bytes);
		Block c = new Block(bytes);
		b.shiftRowToRight(0);
		assertEquals(b.getElement(0, 0),c.getElement(0, 1));
		
	}
	@Test
	public void getWordTest() {
		b = new Block(new byte[] { (byte) 0x8d, 0x01, 0x02, 0x04,    0x08, 0x10, 0x20, 0x40,    (byte) 0x80, 0x1b, 0x36, 0x6c,    (byte) 0xd8, (byte) 0xab, 0x4d, (byte) 0x9a });
		
		assertArrayEquals(b.getWord(0).getAllBytes(), new byte[]{(byte) 0x8d, 0x01, 0x02, 0x04});
	}
	@Test
	public void changeWordTest() {
		b = new Block(bytes);
		byte[] word = new byte[]{(byte) 0x8d, 0x08, (byte) 0x80,(byte) 0xd8};
		b.changeWord(0, word);
		assertArrayEquals(b.getWord(0).getAllBytes(), new byte[]{(byte) 0x8d, 0x08, (byte) 0x80,(byte) 0xd8});
	}

}
