package com.ador.springmvc.model;

import java.util.List;
import java.util.Map.Entry;

public class WordCount {

	private List<Entry<String, Integer>> counts;

	public List<Entry<String, Integer>> getCounts() {
		return counts;
	}

	public void setCounts(List<Entry<String, Integer>> counts) {
		this.counts = counts;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Entry<String, Integer> count : counts){
			sb.append(count.getKey()).append("|").append(count.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}

	public String getTopXString(int x){
		int i = 0;
		StringBuffer sb = new StringBuffer();
		for(Entry<String, Integer> count : counts){
			sb.append(count.getKey()).append("|").append(count.getValue());
			sb.append("\n");
			if(++i==x) break;
		}
		return sb.toString();
	}

}
