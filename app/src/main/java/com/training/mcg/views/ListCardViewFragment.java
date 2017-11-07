package com.training.mcg.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.training.mcg.R;
import com.training.mcg.adapters.CardInListAdapter;
import com.training.mcg.contracts.ListCardContract;
import com.training.mcg.models.Card;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by An Nguyen on 10/23/2017.
 */
public class ListCardViewFragment extends Fragment implements ListCardContract.View {

	@BindView(R.id.rl_card_list)
	RecyclerView rlListCard;
	@BindView(R.id.progressBar)
	ProgressBar loadingBar;
	private ListCardContract.Controller controller;
	private CardInListAdapter cardInListAdapter;
	private Unbinder unbinder;

	/**
	 * Create instance list card view fragment.
	 *
	 * @return the list card view fragment
	 */
	public static ListCardViewFragment createInstance() {
		return new ListCardViewFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.cardInListAdapter = new CardInListAdapter(this.getActivity(), new ArrayList<Card>(0));
		this.rlListCard.setLayoutManager(new LinearLayoutManager(this.getActivity()));
		this.rlListCard.setAdapter(this.cardInListAdapter);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_card_view_fragment, container, false);
		this.unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void setController(ListCardContract.Controller controller) {
		this.controller = controller;
	}

	@Override
	public void onResume() {
		super.onResume();
		this.controller.start();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (this.unbinder != null) {
			this.unbinder.unbind();
		}
	}

	@Override
	public void showLoadingUI() {
		this.loadingBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoadingUI() {
		this.loadingBar.setVisibility(View.GONE);
	}

	@Override
	public void displayError(final String errorMessage) {
		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean isActive() {
		return this.isAdded();
	}

	@Override
	public void showCards(final List<Card> cards) {
		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ListCardViewFragment.this.cardInListAdapter.notifyDataSetChanged(cards);
			}
		});
	}
}
