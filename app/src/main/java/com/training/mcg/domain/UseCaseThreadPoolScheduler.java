package com.training.mcg.domain;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by An Nguyen on 9/17/2017.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

	/**
	 * The constant POOL_SIZE.
	 */
	public static final int POOL_SIZE = 2;
	/**
	 * The constant MAX_POOL_SIZE.
	 */
	public static final int MAX_POOL_SIZE = 4;
	/**
	 * The constant TIMEOUT.
	 */
	public static final int TIMEOUT = 60;
	private final Handler handler = new Handler();
	private ThreadPoolExecutor threadPoolExecutor;

	/**
	 * Instantiates a new Use case thread pool scheduler.
	 */
	public UseCaseThreadPoolScheduler() {
		this.threadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
	}

	@Override
	public void execute(Runnable runnable) {
		this.threadPoolExecutor.execute(runnable);
	}

	@Override
	public <RSV extends ResponseValue> void notifyResponse(final RSV response,
	                                                       final UseCaseCallback<RSV> callback) {
		this.handler.post(new Runnable() {
			@Override
			public void run() {
				callback.onSuccess(response);
			}
		});
	}

	@Override
	public <RSV extends ResponseValue> void onError(final Error error,
	                                                final UseCaseCallback<RSV> callback) {
		this.handler.post(new Runnable() {
			@Override
			public void run() {
				callback.onError(error);
			}
		});
	}
}
