package hu.lboncz.series.view.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.lboncz.series.repository.domain.CommentEntity;
import hu.lboncz.series.view.model.CommentView;

@Component
public class CommentTransformer {
	
	@Autowired
	private SeriesTransformer seriesTransformer;
	
	public List<CommentView> trasformCommentEntities(Iterable<CommentEntity> commentEntities) {
		List<CommentView> commentViews = new ArrayList<>();
		for (CommentEntity commentEntity: commentEntities) {
			commentViews.add(transformCommentEntity(commentEntity));
		}
		return commentViews;
	}

	private CommentView transformCommentEntity(CommentEntity commentEntity) {
		CommentView commentView = new CommentView();
		commentView.setId(commentEntity.getId());
		commentView.setSeriesView(seriesTransformer.transformSeriesEntity(commentEntity.getSeries()));
		commentView.setAuthor(commentEntity.getAuthor());
		commentView.setContent(commentEntity.getContent());
		return commentView;
	}
	
}
