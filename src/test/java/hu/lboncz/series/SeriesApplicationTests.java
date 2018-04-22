package hu.lboncz.series;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SeriesApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeriesApplicationTests {
	
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

	@Test
	public void testSeries() throws Exception {
		mockMvc.perform(get("/series.html")).andExpect(MockMvcResultMatchers.view().name("series"));
		mockMvc.perform(get("/search.html")).andExpect(MockMvcResultMatchers.view().name("search"));
		mockMvc.perform(get("/upload.html")).andExpect(MockMvcResultMatchers.view().name("upload"));
	}

}
