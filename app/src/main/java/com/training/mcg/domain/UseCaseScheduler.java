package com.training.mcg.domain;

/**
 * Created by An Nguyen on 9/18/2017.
 */
public interface UseCaseScheduler {
	/**
	 * Execute.
	 *
	 * @param runnable the runnable
	 */
	void execute(Runnable runnable);

	/**
	 * Notify response.
	 *
	 * @param <RSV>    the type parameter
	 * @param response the response
	 * @param callback the callback
	 */
	<RSV extends ResponseValue> void notifyResponse(RSV response, UseCaseCallback<RSV> callback);

	/**
	 * On error.
	 *
	 * @param <RSV>    the type parameter
	 * @param error    the error
	 * @param callback the callback
	 */
	<RSV extends ResponseValue> void onError(Error error, UseCaseCallback<RSV> callback);
}
