package com.training.mcg.domain.filters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by An Nguyen on 10/24/2017.
 */
public abstract class FilterFactory {

	/**
	 * The Filters.
	 */
	protected static final Map<FilterType, Filter> filters = new HashMap<>();

	/**
	 * Create filter.
	 *
	 * @param filterType the filter type
	 *
	 * @return the filter
	 */
	public Filter create(FilterType filterType) {
		return filters.get(filterType);
	}
}
