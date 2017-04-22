package com.jaimetoqu.countries.views;

/**
 * Created by jaime on 4/17/17.
 */

public class CodeValidation {

    private CodeCallback callback;

    public CodeValidation(CodeCallback callback) {
        this.callback = callback;
    }

    public void init(String code) {
        if (code.trim().length() > 0) {
            callback.code();
        } else {
            callback.noData();
        }
    }
}
