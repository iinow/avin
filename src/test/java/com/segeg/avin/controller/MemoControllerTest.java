package com.segeg.avin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.avin.AvinApplication;
import com.avin.api.controller.MemoController;
import com.avin.dto.MemoDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvinApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MemoControllerTest {
	
	@Autowired
	private MemoController memoController;

	@Test
	public void postMemo() {
		MemoDTO dto = new MemoDTO();
		dto.setContent("Hi");
		memoController.postMemo(dto);
	}
	
	@Test
	public void dd() {
		System.out.println("Hello world1");
	}
}
