package hu.lboncz.series.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.lboncz.series.service.SeriesService;
import hu.lboncz.series.view.model.CommentView;
import hu.lboncz.series.view.model.SeriesView;

@RestController
public class SeriesRestController {
	
	@Autowired
	private SeriesService seriesService;
	
	@RequestMapping("/api/series")
	public List<SeriesView> getSeries() {
		return seriesService.getSeries();
	}
	
	@RequestMapping("/api/{id}/comments")
	public List<CommentView> getComments(@PathVariable("id") Long id) {
		return seriesService.getCommentsOfSeries(id);
	}
}
