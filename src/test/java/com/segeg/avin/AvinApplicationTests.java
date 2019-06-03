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
		String name = "kavr 00025";
		URLDecoder.decode(name, "UTF-8");
		System.out.println(URLEncoder.encode(name, "UTF-8"));
		System.out.println(imageService.getImage(URLEncoder.encode(name, "UTF-8")));
	}

	@Test
    public void getManga() throws Exception {
	    imageService.getManga();
    }

    @Test
    public void getPath() throws Exception {
        String name = "kavr 025";
        System.out.println(URLEncoder.encode(name, "UTF-8").replaceAll("\\+",""));
        /*imageService.getDetailImage(URLEncoder.encode(name, "UTF-8"));*/
        /*String url = "https://pics.dmm.co.jp/digital/video/kavr00025/kavr00025-1.jpg";
        int i = url.lastIndexOf("-");
        System.out.println(url.replace(url.substring(i), "jp"+url.substring(i)));*/


    }
}
