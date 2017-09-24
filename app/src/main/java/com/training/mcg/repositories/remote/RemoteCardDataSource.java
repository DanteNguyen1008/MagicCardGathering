package com.training.mcg.repositories.remote;

import android.support.annotation.NonNull;

import com.training.mcg.models.Card;
import com.training.mcg.repositories.repo.CardDataSource;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by An Nguyen on 9/22/2017.
 */
public class RemoteCardDataSource implements CardDataSource {

	private MagicCardService service;

	public RemoteCardDataSource(String url) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		this.service = retrofit.create(MagicCardService.class);
	}

	@Override
	public void getCards(@NonNull LoadCardsCallback callback) {
		this.service.allCard();
	}

	@Override
	public void saveCards(List<Card> cards) {

	}
}
