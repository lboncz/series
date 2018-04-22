package hu.lboncz.series.repository.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "series")
public class SeriesEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String poster;

	public SeriesEntity() {
	}

	public SeriesEntity(String title, String poster) {
		this.title = title;
		this.poster = poster;
	}

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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

}
