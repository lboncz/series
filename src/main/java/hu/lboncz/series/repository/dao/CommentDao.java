package hu.lboncz.series.repository.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.lboncz.series.repository.domain.CommentEntity;
import hu.lboncz.series.repository.domain.SeriesEntity;

@Repository
public interface CommentDao extends CrudRepository<CommentEntity, Long> {

	Iterable<CommentEntity> findBySeries(SeriesEntity seriesEntity);
}
