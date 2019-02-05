import static org.junit.Assert.*;

import org.junit.Test;


public class KeyScheduleTest {

	Word testVektor = new Word(new byte[] { 0x09, (byte) 0xcf, 0x4f, 0x3c });
	
	@Test
	public void subWordTest() {
		Word sncVektor = new Word(new byte[] { 0x01, (byte) ( 0x8a ), (byte) 0x84, (byte) 0xeb });
		KeySchedule ks = new KeySchedule();
		
		assertArrayEquals(ks.subWord(testVektor).getAllBytes(), sncVektor.getAllBytes());
	}
	
	@Test
	public void produceRoundKeyTest() {
		Block testKey = new Block(new byte[]{0x2b,0x7e,0x15,0x16,0x28,(byte) 0xae,(byte) 0xd2,(byte) 0xa6,(byte) 0xab,(byte) 0xf7,0x15,(byte) 0x88,0x09,(byte) 0xcf,0x4f,0x3c});
		Word[] wordsResult = KeySchedule.produceRoundKeys(testKey);
		
		assertArrayEquals("0 sıkıntılı",wordsResult[0].getAllBytes(), new byte[]{ 0x2b,0x7e,0x15,0x16 });
		assertArrayEquals("4 sıkıntılı",wordsResult[4].getAllBytes(), new byte[]{ (byte) 0xa0,(byte) 0xfa,(byte) 0xfe,0x17 });
//new byte[]{ (byte) 0x8a,(byte) 0x84,(byte) 0xeb,0x01 }
		assertArrayEquals("6 sıkıntılı",wordsResult[6].getAllBytes(), new byte[]{ 0x23,(byte) 0xa3,0x39,0x39 });

		assertArrayEquals("7 sıkıntılı",wordsResult[7].getAllBytes(), new byte[]{ 0x2a,(byte) 0x6c,0x76,0x05 });
		assertArrayEquals("13 sıkıntılı", wordsResult[13].getAllBytes(), new byte[]{ 0x47,(byte) 0x16,(byte) 0xfe,0x3e });
		assertArrayEquals("41 sıkıntılı",wordsResult[41].getAllBytes(), new byte[]{ (byte) 0xc9,(byte) 0xee,0x25,(byte) 0x89 });
		//assertArrayEquals("33 sıkıntılı",wordsResult[0].getAllBytes(), new byte[]{ 0x2b,0x7b,0x15,0x16 });

	}
}
