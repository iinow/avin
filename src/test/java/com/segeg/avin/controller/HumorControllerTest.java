package com.segeg.avin.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.avin.AvinApplication;
import com.avin.api.controller.HumorController;
import com.avin.api.service.BoardService;
import com.avin.config.AppConfig;
import com.avin.dto.BoardHumorDto;
import com.avin.security.filter.TokenAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author BISHOP
 * @since 2019.11.01
 * Pageable g = PageRequest.of(0, 10, Sort.by(Order.desc("cdt")));
 * */
@EnableSpringDataWebSupport
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvinApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HumorControllerTest {

	@Autowired
	private HumorController controller;
	
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
	
	@Autowired
	private BoardService boardService;
	
	//jwt token
	private String token;
	
	@Before
	public void setUp() {
		this.document = document(
                "{class-name}/{method-name}", 
                preprocessResponse(prettyPrint())
        );
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();
		
		token = createToken((long)1);
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
	public void postHumor() throws Exception {
		BoardHumorDto dto = new BoardHumorDto();
		dto.setTitle("that is title..");
		dto.setContent("Hellldlldkdjfakd");
		String body = mapper.writeValueAsString(dto);
		
		standaloneSetup(this.controller)
			.apply(documentationConfiguration(this.restDocumentation))
			.addFilter(tokenFilter).build()
			.perform(
				post("/boards/humors")
					.content(body)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.header("Authorization", "Bearer "+token))
			.andDo(print())
			.andExpect(status().is(200));
	}
	
	@Test
	public void getHumor() throws Exception {
		long id = 1;
		
		standaloneSetup(this.controller)
			.apply(documentationConfiguration(this.restDocumentation))
			.addFilter(tokenFilter).build()
			.perform(
				get("/boards/humors/{id}", id)
					.header("Authorization", "Bearer "+token))
			.andDo(print())
			.andExpect(status().is(200));
	}
	
	@Test
	public void getHumors() throws Exception {
		this.mockMvc.perform(get("/boards/humors")
				.param("page", "0")
				.param("size", "2")
				.param("sort", "id,DESC"))
			.andDo(print())
			.andExpect(status().is(200));
	}
	
	@Test
	public void patchHumor() {
		
	}
	
	@Test
	public void deleteHumor() {
		
	}
}
