package com.training.mcg.repositories.local;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;
import com.training.mcg.models.Card_;
import com.training.mcg.repositories.repo.CardDataSource;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

/**
 * Created by An Nguyen on 9/22/2017.
 */
public class LocalCardDataSource implements CardDataSource {

	private static LocalCardDataSource INSTANCE = null;
	private BoxStore boxStore;
	private Box<Card> boxCard;

	/**
	 * Instantiates a new Local card data source.
	 *
	 * @param boxStore the box store
	 */
	private LocalCardDataSource(BoxStore boxStore) {
		this.boxStore = boxStore;
		this.boxCard = this.boxStore.boxFor(Card.class);
	}

	/**
	 * Gets instance.
	 *
	 * @param boxStore the box store
	 *
	 * @return the instance
	 */
	public static LocalCardDataSource getInstance(BoxStore boxStore) {
		if (INSTANCE == null) {
			INSTANCE = new LocalCardDataSource(boxStore);
		}

		return INSTANCE;
	}

	/**
	 * Destroy instance.
	 */
	public static void destroyInstance() {
		INSTANCE = null;
	}

	@Override
	public void getCards(@NonNull LoadCardsCallback callback) {
		try {
			Query<Card> queryAllCard = this.boxCard.query().order(Card_.id).build();
			List<Card> cards = queryAllCard.find();
			callback.onCardsLoaded(cards);
		} catch (Exception ex) {
			ex.printStackTrace();
			callback.onDataNotAvailable();
		}
	}

	@Override
	public void saveCards(List<Card> cards) {
		try {
			// TODO first version: clear then add
			this.boxCard.removeAll();
			this.boxCard.put(cards);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void refreshCards() {

	}
}
