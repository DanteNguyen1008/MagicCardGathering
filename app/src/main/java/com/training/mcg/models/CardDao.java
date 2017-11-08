package com.training.mcg.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by An Nguyen on 11/7/2017.
 */

@Dao
public interface CardDao {
	@Query("SELECT * FROM Card")
	List<Card> getAll();

	@Insert
	void insertAll(List<Card> cards);

	@Delete
	void delete(Card card);

	@Delete
	void deleteAll(List<Card> cards);
}
