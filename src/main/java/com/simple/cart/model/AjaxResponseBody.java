package com.simple.cart.model;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    CartInfo result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public CartInfo getResult() {
		return result;
	}

	public void setResult(CartInfo result) {
		this.result = result;
	}

   
}
