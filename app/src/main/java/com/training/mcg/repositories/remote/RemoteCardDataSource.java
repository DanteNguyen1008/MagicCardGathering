package com.training.mcg.repositories.remote;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;
import com.training.mcg.repositories.repo.CardDataSource;

import java.util.List;

/**
 * Created by An Nguyen on 9/22/2017.
 */
public class RemoteCardDataSource implements CardDataSource {
	@Override
	public void getCards(@NonNull LoadCardsCallback callback) {

	}

	@Override
	public void saveCards(List<Card> cards) {

	}
}
