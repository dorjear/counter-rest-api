package test.com.ador.springmvc;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.Test;

import com.ador.springmvc.model.WordCount;


public class WordCountTest {

	@Test
	public void testToString(){

		WordCount wc = new WordCount();
		List<Entry<String, Integer>> counts = new ArrayList<Entry<String, Integer>>();
		

		counts.add(new SimpleEntry<String, Integer>("abc", 12));
		counts.add(new SimpleEntry<String, Integer>("def", 13));
		counts.add(new SimpleEntry<String, Integer>("gh", 14));
		counts.add(new SimpleEntry<String, Integer>("ijk", 15));

		wc.setCounts(counts);

		String expected = "abc|12\ndef|13\ngh|14\nijk|15\n";
		Assert.assertEquals(expected, wc.toString());
		
		expected = "abc|12\ndef|13\ngh|14\n";
		Assert.assertEquals(expected, wc.getTopXString(3));

	}
}