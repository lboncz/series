package hu.lboncz.series.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.lboncz.series.service.SeriesService;
import hu.lboncz.series.view.model.CommentView;
import hu.lboncz.series.view.model.SeriesView;

@Controller
public class SeriesDetailsController {

	@Autowired
	private SeriesService seriesService;

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET)
	public String showSeriesDetails(@PathVariable("id") Long id, Model model) {
		SeriesView series = seriesService.findById(id);
		model.addAttribute("series", series);
		List<CommentView> comments = seriesService.getCommentsOfSeries(id);
		model.addAttribute("comments", comments);
		return "seriesDetails";
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.POST)
	public String postComment(@PathVariable("id") Long id, @RequestParam(value = "author") String author,
			@RequestParam(value = "content") String content) {
		seriesService.saveComment(id, author, content);
		return "redirect:{id}.html";
	}

}
