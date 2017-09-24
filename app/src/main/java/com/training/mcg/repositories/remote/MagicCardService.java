package com.training.mcg.repositories.remote;

import com.training.mcg.models.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by An Nguyen on 9/24/2017.
 */

public interface MagicCardService {
	@GET("cards")
	Call<List<Card>> allCard();
}
