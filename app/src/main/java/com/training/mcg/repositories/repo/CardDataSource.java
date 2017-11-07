package com.training.mcg.repositories.repo;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;

import java.util.List;

/**
 * Created by An Nguyen on 9/18/2017.
 */
public interface CardDataSource {
	/**
	 * Gets cards.
	 *
	 * @param callback the callback
	 */
	void getCards(@NonNull LoadCardsCallback callback);

	/**
	 * Save cards.
	 *
	 * @param cards the cards
	 */
	void saveCards(List<Card> cards);

	/**
	 * Refresh cards.
	 */
	void refreshCards();

	/**
	 * The interface Load cards callback.
	 */
	interface LoadCardsCallback {
		/**
		 * On cards loaded.
		 *
		 * @param cards the cards
		 */
		void onCardsLoaded(List<Card> cards);

		/**
		 * On data not available.
		 */
		void onDataNotAvailable();
	}
}
