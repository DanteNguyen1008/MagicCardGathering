package com.training.mcg.controllers;

import android.support.annotation.NonNull;

import com.training.mcg.contracts.ListCardContract;
import com.training.mcg.domain.Error;
import com.training.mcg.domain.UseCaseCallback;
import com.training.mcg.domain.UseCaseHandler;
import com.training.mcg.domain.usecase.GetCard;
import com.training.mcg.models.Card;
import com.training.mcg.views.ListCardViewFragment;

import java.util.List;

/**
 * Created by An Nguyen on 10/23/2017.
 */
public class ListCardController implements ListCardContract.Controller {

	private final GetCard getCardUseCase;
	private final UseCaseHandler useCaseHandler;
	private ListCardViewFragment view;
	private boolean mFirstLoad = true;

	/**
	 * Instantiates a new List card controller.
	 *
	 * @param view the view
	 */
	public ListCardController(@NonNull ListCardViewFragment view,
	                          @NonNull UseCaseHandler useCaseHandler,
	                          @NonNull GetCard getCardUseCase) {
		this.view = view;
		this.getCardUseCase = getCardUseCase;
		this.useCaseHandler = useCaseHandler;
		this.view.setController(this);
	}

	@Override
	public void start() {
		this.loadCard(false);
	}

	@Override
	public void onRequestDataOnLoad() {

	}

	@Override
	public void loadCard(boolean forceUpdate) {
		this.loadCard(forceUpdate || this.mFirstLoad, true);
		this.mFirstLoad = false;
	}

	/**
	 * Load card from data source
	 *
	 * @param forceUpdate     - force datasource to update from remote or not
	 * @param isShowLoadingUI - show the loading UI or not
	 */
	private void loadCard(boolean forceUpdate, final boolean isShowLoadingUI) {
		if (isShowLoadingUI) {
			this.view.showLoadingUI();
		}

		GetCard.RequestValue requestValue = new GetCard.RequestValue(forceUpdate);
		this.useCaseHandler.execute(this.getCardUseCase, requestValue,
				new UseCaseCallback<GetCard.ResponseValue>() {
					@Override
					public void onSuccess(GetCard.ResponseValue response) {
						List<Card> cards = response.getCards();
						if (!view.isActive()) {
							return;
						}

						if (isShowLoadingUI) {
							view.hideLoadingUI();
						}

						ListCardController.this.processCards(cards);
					}

					@Override
					public void onError(Error error) {
						if (isShowLoadingUI) {
							view.hideLoadingUI();
						}

						view.displayError(error.errorMessage);
					}
				});


	}

	/**
	 * Process cards list
	 *
	 * @param cards
	 */
	private void processCards(List<Card> cards) {
		if (cards.isEmpty()) {
			this.processEmptyCards();
		} else {
			this.view.showCards(cards);
		}
	}

	/**
	 * Process empty cards
	 */
	private void processEmptyCards() {

	}
}
