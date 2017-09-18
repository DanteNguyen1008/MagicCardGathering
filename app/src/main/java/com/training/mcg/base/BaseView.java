package com.training.mcg.base;

/**
 * Created by An Nguyen on 9/17/2017.
 *
 * @param <C> the type parameter
 */
public interface BaseView<C extends BaseController> {
    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    void setController(C controller);
}
