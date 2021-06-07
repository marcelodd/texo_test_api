package com.teste.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:data/movielist.csv")
    Resource csvFile;

    @Test
    void moviesCsvImport() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "movielist.csv", "text/plain", csvFile.getInputStream());
        mockMvc.perform(multipart("/v1/movies/csv/import")
                .file(multipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void listMaxAndMinWinnerMovies() throws Exception {
        mockMvc.perform(get("/v1/movies/max-min-interval-win"))
                .andExpect(status().isOk());
    }

}
