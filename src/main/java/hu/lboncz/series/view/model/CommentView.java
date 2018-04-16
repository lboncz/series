package hu.lboncz.series.view.model;

public class CommentView {
	
	private Long id;
	private SeriesView seriesView;
	private String author;
	private String content;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public SeriesView getSeriesView() {
		return seriesView;
	}

	public void setSeriesView(SeriesView seriesView) {
		this.seriesView = seriesView;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
