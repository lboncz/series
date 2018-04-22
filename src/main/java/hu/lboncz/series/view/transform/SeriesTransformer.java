package hu.lboncz.series.view.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hu.lboncz.series.repository.domain.SeriesEntity;
import hu.lboncz.series.view.model.SeriesView;

@Component
public class SeriesTransformer {

	public List<SeriesView> transformSeriesEntities(Iterable<SeriesEntity> seriesEntites) {
		List<SeriesView> seriesViews = new ArrayList<>();
		for (SeriesEntity seriesEntity : seriesEntites) {
			seriesViews.add(transformSeriesEntity(seriesEntity));
		}
		return seriesViews;
	}

	public SeriesView transformSeriesEntity(SeriesEntity seriesEntity) {
		SeriesView seriesView = new SeriesView();
		seriesView.setId(seriesEntity.getId());
		seriesView.setTitle(seriesEntity.getTitle());
		seriesView.setPoster(seriesEntity.getPoster());
		return seriesView;
	}

}
