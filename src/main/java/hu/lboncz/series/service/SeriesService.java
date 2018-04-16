package hu.lboncz.series.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
    	URL posterurl =  new URL(imdb.select("div.poster").select("img").attr("src"));
    	URLConnection urlConn = posterurl.openConnection();
    	InputStream is = urlConn.getInputStream();
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int l = is.read(buffer);
        while(l >= 0) {
            outputStream.write(buffer, 0, l);
            l = is.read(buffer);
        }
    	String title;
    	Elements elem = imdb.select("div.originalTitle");
    	if (!elem.isEmpty()) {
    		elem.select("span").remove();
    		title = elem.text();
    	} else {
    		title = imdb.select("div.title_wrapper").select("h1").text();
    	}
    	seriesDao.save(new SeriesEntity(title, outputStream.toByteArray()));
	}

	public List<CommentView> getCommentsOfSeries(Long seriesId) {
		return commentTransformer.trasformCommentEntities(commentDao.findBySeries(seriesDao.findById(seriesId).get()));
	}

	public void saveComment(Long id, String author, String content) {
		commentDao.save(new CommentEntity(seriesDao.findById(id).get(), author, content));
	}

}
