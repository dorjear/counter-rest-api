package com.ador.springmvc.service;

import com.ador.springmvc.model.WordCount;

public interface CountService {
	
	WordCount getWordCount(String paragraph);
	
	WordCount getWordCount(String paragraph, String[] words);
	
}
