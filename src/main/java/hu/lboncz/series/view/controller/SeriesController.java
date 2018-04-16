package hu.lboncz.series.view.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.lboncz.series.service.SeriesService;
import hu.lboncz.series.view.model.CommentView;
import hu.lboncz.series.view.model.SeriesView;

@Controller
public class SeriesController {

	@Autowired
	private SeriesService seriesService;

	@ModelAttribute("seriesList")
	public List<SeriesView> getSeries() throws IOException {
		List<SeriesView> seriesList = seriesService.getSeries();
		if (seriesList.isEmpty()) {
			seriesService.save("http://www.imdb.com/title/tt1632701/");
			seriesService.saveComment(1L, "Harvey", "My favorite TV show! :)");
//			seriesService.save("http://www.imdb.com/title/tt0118480/");
//			seriesService.save("http://www.imdb.com/title/tt0092455/");
			seriesList = seriesService.getSeries();
		}
		return seriesList;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome() {
		return "home";
	}

	@RequestMapping(value = "/series.html", method = RequestMethod.GET)
	public String showSeries() {
		return "series";
	}

	@RequestMapping(value = "/upload.html", method = RequestMethod.GET)
	public String showUpload() {
		return "upload";
	}

	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public String save(@RequestParam("newseries") String newseries) throws IOException {
		seriesService.save(newseries);
		return "redirect:series.html";
	}

	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public String search(@RequestParam(value = "title", required = false) String title, Model model) {
		List<SeriesView> searchResult = seriesService.findSeries(title);
		model.addAttribute("searchResult", searchResult);
		return "search";
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET)
	public String showSeriesDetails(@PathVariable("id") Long id, Model model) {
		SeriesView series = seriesService.findById(id);
		model.addAttribute("series", series);
		List<CommentView> comments = seriesService.getCommentsOfSeries(id);
		model.addAttribute("comments", comments);
		return "seriesDetails";
	}
	
	@RequestMapping(value = "/{id}.html", method = RequestMethod.POST)
	public String postComment(@PathVariable("id") Long id, @RequestParam(value = "author") String author, @RequestParam(value = "content") String content) {
		seriesService.saveComment(id, author, content);
		return "redirect:{id}.html";
	}

}
