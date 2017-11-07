package com.training.mcg.domain.usecase;

import android.support.annotation.NonNull;

import com.training.mcg.domain.Error;
import com.training.mcg.domain.UseCase;
import com.training.mcg.models.Card;
import com.training.mcg.repositories.repo.CardDataSource;
import com.training.mcg.repositories.repo.CardRepository;

import java.util.List;

/**
 * Created by An Nguyen on 10/23/2017.
 */
public class GetCard extends UseCase<GetCard.RequestValue, GetCard.ResponseValue> {

	private final CardRepository repository;

	/**
	 * Instantiates a new Get card.
	 *
	 * @param cardRepository the card repository
	 */
	public GetCard(@NonNull CardRepository cardRepository) {
		this.repository = cardRepository;
	}

	@Override
	protected void executeUseCase(final RequestValue requestValue) {
		if (requestValue.forceUpdate) {
			this.repository.refreshCards();
		}

		this.repository.getCards(new CardDataSource.LoadCardsCallback() {
			@Override
			public void onCardsLoaded(List<Card> cards) {
				getUseCaseCallBack().onSuccess(new ResponseValue(cards));
			}

			@Override
			public void onDataNotAvailable() {
				getUseCaseCallBack().onError(new Error("Data not available", Error.eError.NO_DATA));
			}
		});
	}

	/**
	 * The type Request value.
	 */
	public static final class RequestValue implements com.training.mcg.domain.RequestValue {
		/**
		 * The Force update.
		 */
		final boolean forceUpdate;

		/**
		 * Instantiates a new Request value.
		 *
		 * @param forceUpdate the force update
		 */
		public RequestValue(boolean forceUpdate) {
			this.forceUpdate = forceUpdate;
		}
	}

	/**
	 * The type Response value.
	 */
	public static final class ResponseValue implements com.training.mcg.domain.ResponseValue {
		/**
		 * The Cards.
		 */
		private final List<Card> cards;

		/**
		 * Instantiates a new Response value.
		 *
		 * @param cards the cards
		 */
		public ResponseValue(@NonNull List<Card> cards) {
			this.cards = cards;
		}

		/**
		 * Gets cards.
		 *
		 * @return the cards
		 */
		public List<Card> getCards() {
			return this.cards;
		}
	}
}
