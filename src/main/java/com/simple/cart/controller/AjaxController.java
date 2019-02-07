package com.simple.cart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.cart.dao.ProductDAO;
import com.simple.cart.entity.Product;
import com.simple.cart.model.AjaxResponseBody;
import com.simple.cart.model.CartInfo;
import com.simple.cart.model.ProductInfo;
import com.simple.cart.utils.Utils;

@RestController
public class AjaxController {

	@Autowired
	private ProductDAO productDAO;

	@PostMapping({ "/addToCart" })
	public ResponseEntity<?> addProductToCart(HttpServletRequest request, //
			@RequestParam(value = "code", defaultValue = "") String code) {
		AjaxResponseBody result = new AjaxResponseBody();
		Product product = null;
		CartInfo cartInfo = null;
		if (code != null && code.length() > 0) {
			product = productDAO.findProduct(code);
		} else {
			result.setMsg("Result Code is empty");
			return ResponseEntity.badRequest().body(result);
		}
		if (product != null) {

			//
			cartInfo = Utils.getCartInSession(request);

			ProductInfo productInfo = new ProductInfo(product);

			cartInfo.addProduct(productInfo, 1);
		}

		if (product == null) {
			result.setMsg("no product found!");
		} else {
			result.setMsg("success");
		}
		result.setResult(cartInfo);

		return ResponseEntity.ok(result);
	}
	
	@GetMapping({ "/getQtyCount" })
	public ResponseEntity<?> getQtyCount(HttpServletRequest request) {
		AjaxResponseBody result = new AjaxResponseBody();
		CartInfo cartInfo = null;
	
			cartInfo = Utils.getCartInSession(request);

		if (cartInfo == null) {
			result.setMsg("Cart Has No Product!");
		} else {
			result.setMsg("success");
		}
		result.setResult(cartInfo);

		return ResponseEntity.ok(result);
	}


}
