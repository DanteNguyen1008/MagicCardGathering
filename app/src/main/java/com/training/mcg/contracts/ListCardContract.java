package com.training.mcg.contracts;

import com.training.mcg.base.BaseController;
import com.training.mcg.base.BaseView;
import com.training.mcg.models.Card;

import java.util.List;

/**
 * Created by An Nguyen on 10/23/2017.
 */

public interface ListCardContract {

	interface View extends BaseView<Controller> {
		void showLoadingUI();

		void hideLoadingUI();

		void displayError(String errorMessage);

		boolean isActive();

		void showCards(List<Card> cards);
	}

	interface Controller extends BaseController<View> {
		void onRequestDataOnLoad();

		void loadCard(boolean forceUpdate);
	}
}
