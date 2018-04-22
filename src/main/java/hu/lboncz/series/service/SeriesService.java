package hu.lboncz.series.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lboncz.series.repository.dao.CommentDao;
import hu.lboncz.series.repository.dao.SeriesDao;
import hu.lboncz.series.repository.domain.CommentEntity;
import hu.lboncz.series.repository.domain.SeriesEntity;
import hu.lboncz.series.view.model.CommentView;
import hu.lboncz.series.view.model.SeriesView;
import hu.lboncz.series.view.transform.CommentTransformer;
import hu.lboncz.series.view.transform.SeriesTransformer;

@Service
public class SeriesService {

	private static final String LOCATION = "src\\main\\resources\\static\\images\\";

	@Autowired
	private SeriesDao seriesDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private SeriesTransformer seriesTransformer;

	@Autowired
	private CommentTransformer commentTransformer;

	public List<SeriesView> getSeries() {
		return seriesTransformer.transformSeriesEntities(seriesDao.findAll());
	}

	public SeriesView findById(Long id) {
		return seriesTransformer.transformSeriesEntity(seriesDao.findById(id).get());
	}

	public List<SeriesView> findSeries(String title) {
		return seriesTransformer.transformSeriesEntities(seriesDao.findSeries(title));
	}

	public void save(String newseries) throws IOException {
		Document imdb = Jsoup.connect(newseries).get();
		Elements elem = imdb.select("div.originalTitle");
		String title = "";
		if (!elem.isEmpty()) {
			elem.select("span").remove();
			title = elem.text();
		} else {
			title = imdb.select("div.title_wrapper").select("h1").text();
		}

		String poster = imdb.select("div.poster").select("img").attr("src");
		URL posterUrl = new URL(poster);
		URLConnection urlConn = posterUrl.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		String[] splitted = poster.split("/");
		String posterFileName = splitted[splitted.length - 1];
		Files.copy(inputStream, Paths.get(LOCATION + posterFileName), StandardCopyOption.REPLACE_EXISTING);

		seriesDao.save(new SeriesEntity(title, posterFileName));
	}

	public void saveSeries(String title, String poster) {
		seriesDao.save(new SeriesEntity(title, poster));
	}

	public List<CommentView> getCommentsOfSeries(Long seriesId) {
		return commentTransformer.trasformCommentEntities(commentDao.findBySeries(seriesDao.findById(seriesId).get()));
	}

	public void saveComment(Long id, String author, String content) {
		commentDao.save(new CommentEntity(seriesDao.findById(id).get(), author, content));
	}

}
