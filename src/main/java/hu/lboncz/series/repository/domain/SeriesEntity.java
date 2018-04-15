package hu.lboncz.series.repository.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "series")
public class SeriesEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@Lob
	private byte[] poster;

	
	public SeriesEntity() {
	}

	public SeriesEntity(String title, byte[] poster) {
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

	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}
	
}
