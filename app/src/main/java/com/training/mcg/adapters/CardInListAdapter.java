package com.training.mcg.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.mcg.adapters.viewholders.CardInListViewHolder;
import com.training.mcg.models.Card;

import java.util.List;

/**
 * Created by An Nguyen on 10/23/2017.
 */
public class CardInListAdapter extends RecyclerView.Adapter<CardInListViewHolder> {

	private Context context;
	private List<Card> data;

	/**
	 * Instantiates a new Card in list adapter.
	 *
	 * @param context the context
	 * @param data    the data
	 */
	public CardInListAdapter(Context context, List<Card> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public CardInListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(this.context).inflate(android.R.layout
				.simple_list_item_1, null);
		return new CardInListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(CardInListViewHolder holder, int position) {
		try {
			holder.tvCardId.setText(this.data.get(position).getId());
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public int getItemCount() {
		return this.data == null ? 0 : this.data.size();
	}

	/**
	 * Notify data set changed.
	 *
	 * @param cards the cards
	 */
	public void notifyDataSetChanged(List<Card> cards) {
		this.data = cards;
		this.notifyDataSetChanged();
	}
}
