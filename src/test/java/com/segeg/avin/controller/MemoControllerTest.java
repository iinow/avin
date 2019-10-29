package com.segeg.avin.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.avin.AvinApplication;
import com.avin.api.controller.MemoController;
import com.avin.config.AppConfig;
import com.avin.dto.MemoDto;
import com.avin.security.filter.TokenAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvinApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class MemoControllerTest {
	
	@Autowired
	private MemoController memoController;
	
	@Autowired
	private AppConfig appConfig;
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private TokenAuthenticationFilter tokenFilter;
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private RestDocumentationResultHandler document;
	
	@Before
	public void setUp() {
		this.document = document(
                "{class-name}/{method-name}", 
                preprocessResponse(prettyPrint())
        );
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();
	}
	
	private String createToken(Long id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appConfig.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(id))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appConfig.getAuth().getTokenSecret())
                .compact();
    }
	
	@Test
	public void postMemo() throws Exception {
		MemoDto dto = new MemoDto();
		dto.setContent("Hi");
		String body = mapper.writeValueAsString(dto);
		String token = createToken((long)1);
		
		standaloneSetup(memoController)
			.apply(documentationConfiguration(this.restDocumentation))
			.addFilter(tokenFilter).build()
			.perform(
				post("/memos")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer "+token))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	public void dd() {
		System.out.println("Hello world1");
	}
}
