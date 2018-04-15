package hu.lboncz.series.repository.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.lboncz.series.repository.domain.SeriesEntity;

@Repository
public interface SeriesDao extends CrudRepository<SeriesEntity, Long> {

	@Query("SELECT s FROM series AS s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :title, '%'))")
	Iterable<SeriesEntity> findSeries(@Param("title") String title);
	
}
