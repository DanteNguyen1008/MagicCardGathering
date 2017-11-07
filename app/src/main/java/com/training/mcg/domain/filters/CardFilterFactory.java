package com.training.mcg.domain.filters;

/**
 * Created by An Nguyen on 10/24/2017.
 */
public class CardFilterFactory extends FilterFactory {

	/**
	 * Instantiates a new Card filter factory.
	 */
	public CardFilterFactory() {
		filters.put(FilterType.ALL_CARD, new FilterAllCardFilter());
		filters.put(FilterType.CARD_BY_TYPE, new FilterCardByType());
	}
}
