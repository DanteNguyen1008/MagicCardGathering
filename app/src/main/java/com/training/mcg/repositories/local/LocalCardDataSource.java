package com.training.mcg.repositories.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.training.mcg.models.AppDatabase;
import com.training.mcg.models.Card;
import com.training.mcg.repositories.repo.CardDataSource;

import java.util.List;

/**
 * Created by An Nguyen on 9/22/2017.
 */
public class LocalCardDataSource implements CardDataSource {
	private static final String DATABASE_NAME = "cards_db";
	private static LocalCardDataSource INSTANCE = null;
	private AppDatabase database;

	/**
	 * Instantiates a new Local card data source.
	 */
	private LocalCardDataSource(Context applicationContext) {
		this.database = Room.databaseBuilder(applicationContext, AppDatabase.class,
				DATABASE_NAME).build();
	}

	/**
	 * Gets instance.
	 *
	 * @param applicationContext the application context
	 *
	 * @return the instance
	 */
	public static LocalCardDataSource getInstance(Context applicationContext) {
		if (INSTANCE == null) {
			INSTANCE = new LocalCardDataSource(applicationContext);
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
			callback.onCardsLoaded(this.database.cardDao().getAll());
		} catch (Exception ex) {
			ex.printStackTrace();
			callback.onDataNotAvailable();
		}
	}

	@Override
	public void saveCards(List<Card> cards) {
		try {
			// TODO first version: clear then add
			this.database.cardDao().deleteAll(cards);
			this.database.cardDao().insertAll(cards);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void refreshCards() {

	}
}
