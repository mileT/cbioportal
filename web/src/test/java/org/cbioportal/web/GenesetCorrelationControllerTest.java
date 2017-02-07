package org.cbioportal.web;

import org.cbioportal.service.GenesetCorrelationService;
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
public class GenesetCorrelationControllerTest {
	
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private GenesetCorrelationService genesetCorrelationService;
    private MockMvc mockMvc;

    @Bean
    public GenesetCorrelationService genesetCorrelationService() {
        return Mockito.mock(GenesetCorrelationService.class);
    }

    @Before
    public void setUp() throws Exception {

        Mockito.reset(genesetCorrelationService);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void dummyTest() throws Exception {
    	//no real logic in the controller layer, so skipping tests here...
    }
}