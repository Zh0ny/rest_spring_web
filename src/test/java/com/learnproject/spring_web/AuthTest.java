package com.learnproject.spring_web;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarSucessoQuandoCredenciaisValidas() throws Exception {
        URI uri = new URI("/auth/signin");

        String content = "{\"username\":\"perryque\",\"password\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().is(200));
            
    }

    @Test
    public void deveRetornarErroQuandoCredenciaisInvalidas() throws Exception {
        URI uri = new URI("/auth/signin");

        String content = "{\"username\":\"perryque\",\"password\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.content().string("UserDetailsService returned null, which is an interface contract violation"));
            
    }

}
