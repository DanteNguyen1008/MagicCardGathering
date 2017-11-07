package com.training.mcg.domain.filters;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by An Nguyen on 10/24/2017.
 *
 * @param <T> the type parameter to filter
 */
public interface Filter<T> {

	/**
	 * Filter list.
	 *
	 * @param inputList the input list
	 * @param params    the params
	 *
	 * @return the filtered list
	 */
	@Nullable
	List<T> filter(List<T> inputList, Object... params);
}
