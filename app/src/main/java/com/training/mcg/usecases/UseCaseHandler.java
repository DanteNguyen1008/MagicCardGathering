package com.training.mcg.usecases;

/**
 * Created by An Nguyen on 9/17/2017.
 */
public class UseCaseHandler {

	private static UseCaseHandler INSTANCE = null;
	private UseCaseThreadPoolScheduler scheduler;

	private UseCaseHandler(UseCaseThreadPoolScheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * Gets instance of UseCaseHandler - Singleton
	 *
	 * @return the UseCaseHandler instance
	 */
	public static UseCaseHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
		}

		return INSTANCE;
	}

	/**
	 * Execute.
	 *
	 * @param <RQV>        the type parameter
	 * @param <RSV>        the type parameter
	 * @param useCase      the use case
	 * @param requestValue the request value
	 * @param callback     the callback
	 */
	public <RQV extends RequestValue, RSV extends ResponseValue> void execute(
			final UseCase<RQV, RSV> useCase, RQV requestValue, UseCaseCallback<RSV> callback) {
		useCase.setRequestValue(requestValue);
		useCase.setUseCaseCallback(callback);

		this.scheduler.execute(new Runnable() {
			@Override
			public void run() {
				useCase.run();
			}
		});
	}
}
