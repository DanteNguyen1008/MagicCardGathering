package com.training.mcg.domain.filters;

import com.training.mcg.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by An Nguyen on 10/24/2017.
 */
public class FilterAllCardFilter implements Filter<Card> {

	@Override
	public List<Card> filter(List<Card> inputList, Object... params) {
		return new ArrayList<>(inputList);
	}
}
