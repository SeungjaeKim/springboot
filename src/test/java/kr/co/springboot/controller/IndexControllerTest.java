package kr.co.springboot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    @DisplayName("메인페이지_로딩")
    public void mainpageLoding(){
        //when 
        String body = this.restTemplate.getForObject("/", String.class);
        
        //then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
    }
}