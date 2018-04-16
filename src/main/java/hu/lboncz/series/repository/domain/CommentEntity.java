package hu.lboncz.series.repository.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private SeriesEntity series;
	
	private String author;
	
	private String content;

	public CommentEntity() {
	}

	public CommentEntity(SeriesEntity series, String author, String content) {
		this.series = series;
		this.author = author;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SeriesEntity getSeries() {
		return series;
	}

	public void setSeries(SeriesEntity series) {
		this.series = series;
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
