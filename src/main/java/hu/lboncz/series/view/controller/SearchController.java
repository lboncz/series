package hu.lboncz.series.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.lboncz.series.service.SeriesService;
import hu.lboncz.series.view.model.SeriesView;

@Controller
public class SearchController {

	@Autowired
	private SeriesService seriesService;

	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public String search(@RequestParam(value = "title", required = false) String title, Model model) {
		List<SeriesView> searchResult = seriesService.findSeries(title);
		model.addAttribute("searchResult", searchResult);
		return "search";
	}

}
