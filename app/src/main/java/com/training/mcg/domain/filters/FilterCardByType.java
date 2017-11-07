package com.training.mcg.domain.filters;

import com.training.mcg.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by An Nguyen on 10/24/2017.
 */

public class FilterCardByType implements Filter<Card> {

	@Override
	public List<Card> filter(List<Card> inputList, Object... params) {
		if (params == null || params.length == 0) {
			return null;
		}

		String type = (String) params[0];
		ArrayList<Card> results = new ArrayList<>();
		for (Card card : inputList) {
			if (card.type.equals(type)) {
				results.add(card);
			}
		}

		return results;
	}
}
