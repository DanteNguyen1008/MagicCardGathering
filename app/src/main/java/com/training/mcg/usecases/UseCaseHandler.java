package com.training.mcg.usecases;

import com.training.mcg.base.UseCase;

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

	public <RQV extends RequestValue, RSV extends ResponseValue> void execute(
			UseCase<RQV, RSV> useCase, RQV requestValue, UseCaseCallback<RSV> callback) {

	}
}
