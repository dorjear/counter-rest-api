package com.ador.springmvc.controller;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ador.springmvc.form.SearchCondition;
import com.ador.springmvc.model.WordCount;
import com.ador.springmvc.service.CountService;
import com.ador.springmvc.service.FileService;

@RestController
public class CountWordsRestController {
	private final Log countWordsAuditor = LogFactory.getLog("countWordsAuditor."+this.getClass().getName());

	@Autowired
	CountService countService;
	@Autowired
	FileService fileService; 

	
	@RequestMapping(value = "/top/{topX}", method = RequestMethod.GET, produces = "text/csv")
	public ResponseEntity<String> getTop(@PathVariable("topX") int topX) {
		countWordsAuditor.info("Get Top " + topX );
		String result="";
		String paragraph = fileService.getStringFromFileInClasspath("test.txt");
		WordCount wordCount = countService.getWordCount(paragraph);
		if(wordCount==null)	{
			return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
		}
		result = wordCount.getTopXString(topX);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	

	@RequestMapping(value = "/search/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WordCount> search(@RequestBody SearchCondition searchInput, UriComponentsBuilder ucBuilder) {

		String[] words = searchInput.getSearchText();
		if(words!=null){
			countWordsAuditor.info(Arrays.asList(searchInput.getSearchText()));
		}else{
			countWordsAuditor.info("Search text is null");
		}
		String paragraph = fileService.getStringFromFileInClasspath("test.txt");
		WordCount result = countService.getWordCount(paragraph, words);
		if(result==null)	{
			return new ResponseEntity<WordCount>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WordCount>(result, HttpStatus.OK);
	}

	
	

}
