package com.training.mcg.repositories.repo;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;

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
	public void getCards(@NonNull LoadCardsCallback callback) {
		// Response immediately if the cache is not dirty
		if (!this.cacheIsDirty && this.mCachedCards)
	}

	@Override
	public void getCardDetail(@NonNull LoadCardDetailCallback callback) {

	}
}
