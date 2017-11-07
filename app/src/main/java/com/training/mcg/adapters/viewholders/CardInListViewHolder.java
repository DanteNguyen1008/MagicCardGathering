package com.training.mcg.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by An Nguyen on 10/23/2017.
 */
public class CardInListViewHolder extends RecyclerView.ViewHolder {

	public TextView tvCardId;

	/**
	 * Instantiates a new Card in list view holder.
	 *
	 * @param itemView the item view
	 */
	public CardInListViewHolder(View itemView) {
		super(itemView);
		this.tvCardId = (TextView) itemView;
	}
}
