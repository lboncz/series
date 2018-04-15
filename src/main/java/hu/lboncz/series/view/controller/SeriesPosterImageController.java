package hu.lboncz.series.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.lboncz.series.service.SeriesService;

@Controller
public class SeriesPosterImageController {
	
	@Autowired
	SeriesService seriesService;
	
	@RequestMapping(value = "/{id}.jpg", method = RequestMethod.GET)
	@ResponseBody
	public AbstractResource downloadPoster(@PathVariable("id") Long id) {
		return seriesService.findById(id).getPoster();
	}

}
