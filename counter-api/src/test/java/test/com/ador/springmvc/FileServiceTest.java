package test.com.ador.springmvc;

import org.junit.Test;

import com.ador.springmvc.service.FileService;
import com.ador.springmvc.service.FileServiceImpl;


public class FileServiceTest {

	@Test
	public void testReadFile(){
		FileService fs = new FileServiceImpl();
		String s = fs.getStringFromFileInClasspath("test.txt");
		System.out.println(s);
	}
}