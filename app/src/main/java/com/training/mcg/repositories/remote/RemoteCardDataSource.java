package com.training.mcg.repositories.remote;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.training.mcg.models.Card;
import com.training.mcg.repositories.repo.CardDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by An Nguyen on 9/22/2017.
 */
public class RemoteCardDataSource implements CardDataSource {

	private MagicCardService service;

	/**
	 * Instantiates a new Remote card data source.
	 *
	 * @param url the url
	 */
	public RemoteCardDataSource(String url) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory
						.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
				.build();
		this.service = retrofit.create(MagicCardService.class);
	}

	@Override
	public void getCards(@NonNull final LoadCardsCallback callback) {
		Call<List<Card>> caller = this.service.allCard();
		caller.enqueue(new Callback<List<Card>>() {
			@Override
			public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
				callback.onCardsLoaded(response.body());
			}

			@Override
			public void onFailure(Call<List<Card>> call, Throwable t) {
				callback.onDataNotAvailable();
			}
		});
	}

	@Override
	public void saveCards(List<Card> cards) {
		// No implementation for add new card to remote
	}

	@Override
	public void refreshCards() {

	}
}
