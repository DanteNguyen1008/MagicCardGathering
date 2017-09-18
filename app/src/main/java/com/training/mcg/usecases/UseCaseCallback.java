package com.training.mcg.usecases;

/**
 * Created by An Nguyen on 9/17/2017.
 */

public interface UseCaseCallback<RSV extends ResponseValue> {
    void onSuccess(RSV response);
    void onError(Error error);
}
