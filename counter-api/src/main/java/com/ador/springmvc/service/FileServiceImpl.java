package com.ador.springmvc.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;


@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Override
	public String getStringFromFileInClasspath(String fileName){
        StringBuffer sb = new StringBuffer();
        URL url = this.getClass().getClassLoader().getResource(fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(url.getFile()));
            String s;
            while ((s = br.readLine()) != null) {
                sb.append("\n").append(s);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File read exception");
        }
        return sb.toString();
	}
}
