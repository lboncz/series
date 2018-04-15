package hu.lboncz.series.view.model;

import org.springframework.core.io.AbstractResource;

public class SeriesView {
	
	private Long id;
	private String title;
	private AbstractResource poster;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public AbstractResource getPoster() {
		return poster;
	}
	public void setPoster(AbstractResource poster) {
		this.poster = poster;
	}

}
