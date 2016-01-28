package com.ador.springmvc.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.ador.springmvc.model.WordCount;


@Service("countService")
public class CountServiceImpl implements CountService {
	public static final String DELIM = ", ?.!:\"\"''\n"; 
	
	@Override
	public WordCount getWordCount(String paragraph) {
		if(paragraph==null) return null;
		Map<String, Integer> map = buildMapFromString(paragraph);
		WordCount wordCount = buildWorldCount(map);
		return wordCount;
	}

	@Override
	public WordCount getWordCount(String paragraph, String[] words) {
		if(paragraph==null || words==null) return getWordCount(paragraph);
		List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>();
		Map<String, Integer> map = buildMapFromString(paragraph);
		for(String word : words){
			Integer c = map.get(word.toLowerCase());
			if(c==null) c=0; 
			Entry<String, Integer> entry = new SimpleEntry<String, Integer>(word, c);
			entryList.add(entry);
		}
		
		WordCount result = new WordCount();
		result.setCounts(entryList);
		return result;
	}

	private WordCount buildWorldCount(Map<String, Integer> map) {
		List<Entry<String, Integer>> infoIds = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(infoIds, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		}); // Order
		
		WordCount wordCount = new WordCount();
		wordCount.setCounts(infoIds);
		return wordCount;
	}
		
	private Map<String, Integer> buildMapFromString(String paragraph){
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringTokenizer token = new StringTokenizer(paragraph);
		while (token.hasMoreTokens()) {
			String word = token.nextToken(DELIM).toLowerCase();
			if (map.containsKey(word)) {
				int count = map.get(word);
				map.put(word, count + 1);
			} else
				map.put(word, 1);
		}
		return map;
	}

}
