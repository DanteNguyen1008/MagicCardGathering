package com.training.mcg.usecases;

/**
 * Created by An Nguyen on 9/17/2017.
 *
 * @param <RQV> the type parameter
 * @param <RSV> the type parameter
 */
public abstract class UseCase<RQV extends RequestValue, RSV extends ResponseValue> {
    private RQV requestValue;
    private UseCaseCallback<RSV> useCaseCallback;

    /**
     * Sets request value.
     *
     * @param requestValue the request value
     */
    public void setRequestValue(RQV requestValue) {
        this.requestValue = requestValue;
    }

    /**
     * Sets use case callback.
     *
     * @param useCaseCallback the use case callback
     */
    public void setUseCaseCallback(UseCaseCallback<RSV> useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
    }

    /**
     * Run the use case
     */
    public void run() {
        this.executeUseCase(this.requestValue);
    }

    /**
     * Execute use case.
     *
     * @param requestValue the request value
     */
    protected abstract void executeUseCase(RQV requestValue);
}
