package hu.lboncz.series;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
@SpringBootTest(classes = { SeriesApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
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
		mockMvc.perform(get("/api/series")).andExpect(jsonPath("$[2].title").value("Psych"));
	}

	@Test
	public void testUpload() throws Exception {
		mockMvc.perform(get("/series.html")).andReturn();
		mockMvc.perform(get("/upload.html")).andExpect(MockMvcResultMatchers.view().name("upload"));
		mockMvc.perform(post("/upload.html").param("imdbUrl", "https://www.imdb.com/title/tt1439629/")).andReturn();
		mockMvc.perform(get("/api/series")).andExpect(jsonPath("$[4].title").value("Community"));
	}

	@Test
	public void testPostComment() throws Exception {
		mockMvc.perform(get("/series.html")).andReturn();
		mockMvc.perform(get("/3.html")).andExpect(MockMvcResultMatchers.view().name("seriesDetails"));
		mockMvc.perform(post("/3.html").param("author", "Joe").param("content", "Lorem Ipsum")).andReturn();
		mockMvc.perform(get("/api/3/comments")).andExpect(jsonPath("$[0].author").value("Joe"));
	}

}
