import static org.junit.Assert.*;

import org.junit.Test;


public class TweakTest {

	Tweak tweak;
//	@Test
//	public void testFill() {
//		 
//	}

	@Test
	public void testTweak() {
		byte[] bytes = { 0x19, 0x3d, (byte) 0xe3, (byte) 0xbe, (byte) 0xa0, (byte) 0xf4, (byte) 0xe2, (byte) 0x2b };

		tweak = new Tweak(bytes );
		assertArrayEquals("0lar eklenemedi, hata", new byte[]{ 0x19, 0x3d,0,0}, tweak.getWord(0).getAllBytes());
	}

}
