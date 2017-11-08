package com.training.mcg.repositories.remote;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;
import com.training.mcg.repositories.repo.CardDataSource;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.CardAPI;

/**
 * Created by An Nguyen on 9/22/2017.
 */
public class RemoteCardDataSource implements CardDataSource {

	/**
	 * Instantiates a new Remote card data source.
	 */
	public RemoteCardDataSource() {

	}

	@Override
	public void getCards(@NonNull final LoadCardsCallback callback) {
		List<String> filters = new ArrayList<>();
		filters.add("page=");
		filters.add("1");
		filters.add("pageSize=");
		filters.add("10");
		List<io.magicthegathering.javasdk.resource.Card> allCards = CardAPI.getAllCards(filters);

		if (allCards == null || allCards.size() == 0) {
			callback.onDataNotAvailable();
		} else {
			List<Card> cards = new ArrayList<>();
			for (io.magicthegathering.javasdk.resource.Card card : allCards) {
				cards.add(new Card(card.getId(), card.getType(), card.getName()));
			}
			callback.onCardsLoaded(cards);
		}
	}

	@Override
	public void saveCards(List<Card> cards) {
		// No implementation for add new card to remote
	}

	@Override
	public void refreshCards() {

	}
}
