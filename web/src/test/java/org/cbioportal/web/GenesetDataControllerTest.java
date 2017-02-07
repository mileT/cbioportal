package org.cbioportal.web;

import org.cbioportal.service.GenesetDataService;
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
public class GenesetDataControllerTest {

	@Autowired
    private WebApplicationContext wac;

    @Autowired
    private GenesetDataService genesetDataService;
    private MockMvc mockMvc;

    @Bean
    public GenesetDataService genesetDataService() {
        return Mockito.mock(GenesetDataService.class);
    }
    
    @Before
    public void setUp() throws Exception {

        Mockito.reset(genesetDataService);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void dummyTest() throws Exception {
    	//no real logic in the controller layer, so skipping tests here...
    }

}