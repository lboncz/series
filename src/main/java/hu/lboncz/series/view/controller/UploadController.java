package hu.lboncz.series.view.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.lboncz.series.service.SeriesService;

@Controller
public class UploadController {

	@Autowired
	private SeriesService seriesService;

	@RequestMapping(value = "/upload.html", method = RequestMethod.GET)
	public String showUpload() {
		return "upload";
	}

	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public String save(@RequestParam("imdbUrl") String imdbUrl) throws IOException {
		seriesService.save(imdbUrl);
		return "redirect:series.html";
	}

}
