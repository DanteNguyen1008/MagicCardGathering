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
	 * Gets card detail.
	 *
	 * @param callback the callback
	 */
	void getCardDetail(@NonNull LoadCardDetailCallback callback);

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

	/**
	 * The interface Load card detail callback.
	 */
	interface LoadCardDetailCallback {
		/**
		 * On card loaded.
		 *
		 * @param card the card
		 */
		void onCardLoaded(Card card);

		/**
		 * On data not available.
		 */
		void onDataNotAvailable();
	}
}
