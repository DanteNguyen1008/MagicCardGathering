package com.training.mcg.repositories.repo;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by An Nguyen on 9/18/2017.
 */
public class CardRepository implements CardDataSource {

	private static CardRepository INSTANCE = null;

	private final CardDataSource remoteDataSource;

	private final CardDataSource localDataSource;

	/**
	 * cached data for loaded cards - <Id, Card>
	 */
	Map<String, Card> cachedCards;

	/**
	 * flag to identify if the cache need to be refreshed
	 */
	boolean cacheIsDirty = false;

	/**
	 * Instantiates a new Card repository.
	 * <p/>
	 * Private for singleton
	 *
	 * @param remoteDataSource the remote data source
	 * @param localDataSource  the local data source
	 */
	private CardRepository(@NonNull CardDataSource remoteDataSource,
	                       @NonNull CardDataSource localDataSource) {
		this.remoteDataSource = remoteDataSource;
		this.localDataSource = localDataSource;
	}

	/**
	 * Gets insance.
	 *
	 * @param remoteDataSource the remote data source
	 * @param localDataSource  the local data source
	 *
	 * @return the insance
	 */
	public static CardRepository GetInstance(CardDataSource remoteDataSource,
	                                         CardDataSource localDataSource) {
		if (INSTANCE == null) {
			INSTANCE = new CardRepository(remoteDataSource, localDataSource);
		}

		return INSTANCE;
	}

	/**
	 * Destroy instance.
	 * <p/>
	 * Used to force create new instance at the next time this object get used
	 */
	public static void DestroyInstance() {
		INSTANCE = null;
	}

	@Override
	public void getCards(@NonNull final LoadCardsCallback callback) {
		// Response immediately if the cache is not dirty
		if (!this.cacheIsDirty && this.cachedCards != null) {
			callback.onCardsLoaded(new ArrayList<>(this.cachedCards.values()));
			return;
		}

		if (this.cacheIsDirty) {
			// Fetch new data if the cache is dirty
			this.getCardsFromRemoteDataSource(callback);
		} else {
			// Query the local storage if available. If not, query data from network
			this.localDataSource.getCards(new LoadCardsCallback() {
				@Override
				public void onCardsLoaded(List<Card> cards) {
					CardRepository.this.refreshCardsCache(cards);
					callback.onCardsLoaded(
							new ArrayList<>(CardRepository.this.cachedCards.values()));
				}

				@Override
				public void onDataNotAvailable() {
					CardRepository.this.getCardsFromRemoteDataSource(callback);
				}
			});
		}
	}

	@Override
	public void saveCards(List<Card> cards) {
		// No support save card for or from UseCase
	}

	@Override
	public void refreshCards() {
		this.cacheIsDirty = true;
	}

	/**
	 * Refresh cards cache.
	 *
	 * @param cards the cards
	 */
	private void refreshCardsCache(List<Card> cards) {
		if (this.cachedCards == null) {
			this.cachedCards = new LinkedHashMap<>();
		}

		this.cachedCards.clear();
		for (Card card : cards) {
			this.cachedCards.put(card.getId(), card);
		}

		// "Fresh" cache
		this.cacheIsDirty = false;
	}

	/**
	 * Refresh local data source.
	 *
	 * @param cards the cards
	 */
	private void refreshLocalDataSource(List<Card> cards) {
		this.localDataSource.saveCards(cards);
	}

	/**
	 * Gets cards from remote data source.
	 *
	 * @param callback the callback
	 */
	private void getCardsFromRemoteDataSource(final LoadCardsCallback callback) {
		this.remoteDataSource.getCards(new LoadCardsCallback() {
			@Override
			public void onCardsLoaded(List<Card> cards) {
				CardRepository.this.refreshCardsCache(cards);
				CardRepository.this.refreshLocalDataSource(cards);
				callback.onCardsLoaded(new ArrayList<>(CardRepository.this.cachedCards.values()));
			}

			@Override
			public void onDataNotAvailable() {
				callback.onDataNotAvailable();
			}
		});
	}


}
