package org.cbioportal.web;

import org.cbioportal.service.GenesetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/applicationContext-web.xml")
@Configuration
public class GenesetControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private GenesetService genesetService;
    private MockMvc mockMvc;

    @Bean
    public GenesetService genesetService() {
        return Mockito.mock(GenesetService.class);
    }

    @Before
    public void setUp() throws Exception {

        Mockito.reset(genesetService);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void dummyTest() throws Exception {
    	//no real logic in the controller layer, so skipping tests here...
    }
}