package com.blueriver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueriver.beans.RepositoryUtility;
import com.blueriver.beans.RepositoryUtilityImpl;
import com.blueriver.model.Product;
import com.blueriver.model.support.ProductResponse;

@Controller
public class CatalogController {
	
	@RequestMapping(value={"catalog"}, method=RequestMethod.GET)
	public String getCatalog(Model m) {
		m.addAttribute("products", getOrderedProducts());
		return "CatalogPage";
	}
	
	private List<ProductResponse> getOrderedProducts() {
		// order customers by Id
		List<Product> productList = RepositoryUtilityImpl.getInstance().getProducts();
		Collections.sort(productList);
		
		List<ProductResponse> prodResponseList = new ArrayList<ProductResponse>();
		
		for (Product product : productList) {
			ProductResponse prodResponse = new ProductResponse();
			prodResponse.setResult("found");
			prodResponse.setProductSku(product.getProductSku());
			prodResponse.setProductDescr(product.getProductDescr());
			if (product.getProductPic() == null) {
				prodResponse.setProductPicString("");
			}
			else {
				String imageData = "data:image/jpg;base64," + Base64.encodeBase64String(product.getProductPic());
				prodResponse.setProductPicString(imageData);
			}
			prodResponse.setPicFilename(product.getPicFilename());
			prodResponse.setPicWidth(product.getPicWidth());
			prodResponse.setPicHeight(product.getPicHeight());
			prodResponse.setSize(product.getProductPic().length);
			prodResponseList.add(prodResponse);
		}
		return prodResponseList;
	}
	
	
}
