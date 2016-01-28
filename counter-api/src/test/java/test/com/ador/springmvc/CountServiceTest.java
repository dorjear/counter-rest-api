package test.com.ador.springmvc;

import junit.framework.Assert;

import org.junit.Test;

import com.ador.springmvc.service.CountService;
import com.ador.springmvc.service.CountServiceImpl;


public class CountServiceTest {

	@Test
	public void testReadFile(){
		CountService cs = new CountServiceImpl();
		String paragraph = null;
		//paragraph is null
		Assert.assertNull(cs.getWordCount(paragraph));
		
		//both paragraph and words are null
		Assert.assertNull(cs.getWordCount(paragraph,null));
		String[] words = {"name","is","Tom"};
		
		//paragraph null, words not null
		Assert.assertNull(cs.getWordCount(paragraph,words));
		
		paragraph = "hello,my name is Tom,what is your name?he said:\"my name is John\"";
		String expected = "name|3\nis|3\nmy|2\ntom|1\nwhat|1\njohn|1\nhello|1\nyour|1\nsaid|1\nhe|1\n";
		cs.getWordCount(paragraph);
		//Normal case
		Assert.assertEquals(expected, cs.getWordCount(paragraph).toString());

		//paragraph not null, words null
		Assert.assertEquals(expected, cs.getWordCount(paragraph, null).toString());

		//Normal case
		expected = "name|3\nis|3\nTom|1\n";
		cs.getWordCount(paragraph, words);
		Assert.assertEquals(expected, cs.getWordCount(paragraph, words).toString());
		
		//words empty
		words = new String[]{};
		Assert.assertEquals("", cs.getWordCount(paragraph, words).toString());

	}
}