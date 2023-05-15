package com.education.webservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = IndexController.class)
class indexControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("컨트롤러가 정상 호출된다")
    void indexTest() throws Exception{
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }


}