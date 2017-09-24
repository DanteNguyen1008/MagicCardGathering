package com.training.mcg;

import com.training.mcg.models.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Created by An Nguyen on 9/24/2017.
 */
public class Application extends android.app.Application {

	private BoxStore boxStore;

	@Override
	public void onCreate() {
		super.onCreate();
		this.boxStore = MyObjectBox.builder().androidContext(this).build();
	}

	/**
	 * Gets box store.
	 *
	 * @return the box store
	 */
	public BoxStore getBoxStore() {
		return boxStore;
	}
}
