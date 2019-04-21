package com.segeg.avin;

import com.segeg.avin.model.AppYaml;
import com.segeg.avin.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvinApplicationTests {
	@Autowired
	private ImageService imageService;

	@Autowired
	private AppYaml app;

	@Test
	public void contextLoads() throws Exception{
		String name = "edd 202";
		URLDecoder.decode(name, "UTF-8");
		System.out.println(URLEncoder.encode(name, "UTF-8"));
		System.out.println(imageService.getImage(URLEncoder.encode(name, "UTF-8")));
	}

}
