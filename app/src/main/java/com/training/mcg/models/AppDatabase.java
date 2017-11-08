package com.training.mcg.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by An Nguyen on 11/7/2017.
 */
@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
	/**
	 * Card dao card dao.
	 *
	 * @return the card dao
	 */
	public abstract CardDao cardDao();
}
