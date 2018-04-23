package hu.lboncz.series.view.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.lboncz.series.service.SeriesService;
import hu.lboncz.series.view.model.SeriesView;

@Controller
public class SeriesController {

	@Autowired
	private SeriesService seriesService;

	@ModelAttribute("seriesList")
	public List<SeriesView> getSeries() throws IOException {
		List<SeriesView> seriesList = seriesService.getSeries();
		if (seriesList.isEmpty()) {
			seriesService.saveSeries("Suits", "1.jpg");
			seriesService.saveSeries("Married with Children", "2.jpg");
			seriesService.saveSeries("Psych", "3.jpg");
			seriesService.saveSeries("Breaking Bad", "4.jpg");
			seriesService.saveComment(1L, "Harvey", "My favorite TV show!");
			seriesService.saveComment(1L, "John", "Not bad.");
			seriesService.saveComment(2L, "John", "Al Bundy rules!");
			seriesList = seriesService.getSeries();
		}
		return seriesList;
	}

	@RequestMapping(value = "/series.html", method = RequestMethod.GET)
	public String showSeries() {
		return "series";
	}

}
