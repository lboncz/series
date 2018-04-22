package hu.lboncz.series.view.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/images/{filename}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {
		Path image = Paths.get("src\\main\\resources\\static\\images\\", filename);
		Resource resource = new UrlResource(image.toUri());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
	}

}
