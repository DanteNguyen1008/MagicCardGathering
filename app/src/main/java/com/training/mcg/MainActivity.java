package com.training.mcg;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.training.mcg.controllers.ListCardController;
import com.training.mcg.domain.UseCaseHandler;
import com.training.mcg.domain.usecase.GetCard;
import com.training.mcg.repositories.local.LocalCardDataSource;
import com.training.mcg.repositories.remote.RemoteCardDataSource;
import com.training.mcg.repositories.repo.CardRepository;
import com.training.mcg.views.ListCardViewFragment;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

	private static final String URL = "https://api.magicthegathering.io/v1/";

	private ListCardController listCardController;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListCardViewFragment listCardView = (ListCardViewFragment) this.getFragmentManager()
				.findFragmentById(R.id.contentFrame);

		if (listCardView == null) {
			listCardView = ListCardViewFragment.createInstance();
			FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
			transaction.add(R.id.contentFrame, listCardView);
			transaction.commit();
		}

		this.listCardController = new ListCardController(listCardView,
				UseCaseHandler.getInstance(),
				new GetCard(CardRepository.GetInstance(new RemoteCardDataSource(),
						LocalCardDataSource.getInstance(this.getApplicationContext()))));
	}
}
