package com.blueriver.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blueriver.beans.RepositoryUtilityImpl;
import com.blueriver.model.Product;
import com.blueriver.model.support.ProductRequest;
import com.blueriver.model.support.ProductResponse;

@Controller
public class ProductController {
	private Logger logger = Logger.getLogger(ProductController.class.getName());

	@RequestMapping(value="/product**", method=RequestMethod.GET)
	public String getAllProducts(Model m) {
		logger.debug("in getAllProducts()");
		m.addAttribute("product", new ProductResponse());
		m.addAttribute("products", getOrderedProducts());
		return "ProductPage";
	}

	/*
	 * This is the method that responds to the Submit button (or equivalent)
	 * It takes from the client a product that includes a "multi-part" picture
	 */
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String submitForm(@RequestPart(value="fileContent", required=false) MultipartFile prodPicture, @ModelAttribute Product product, Model model) {
		logger.debug("in submitForm()");
		
		if (prodPicture != null && prodPicture.getSize() > 0) {
			logger.debug("file name: "+prodPicture.getOriginalFilename());
			logger.debug("file size: "+prodPicture.getSize());
			BufferedImage image = null;
			try {
				image = ImageIO.read(prodPicture.getInputStream());
				product.setProductPic(prodPicture.getBytes());
				product.setPicFilename(prodPicture.getOriginalFilename());
				product.setPicWidth(image.getWidth());
				product.setPicHeight(image.getHeight());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			product.setProductPic(null);
		}

		// add to database
		RepositoryUtilityImpl.getInstance().addProduct(product);
		
		// build model and view
		List<ProductResponse> products = new ArrayList<ProductResponse>();
		for (Product aProduct : getOrderedProducts()) {
			ProductResponse prodResponse = new ProductResponse();
			prodResponse.setProductSku(aProduct.getProductSku());
			prodResponse.setProductDescr(aProduct.getProductDescr());
			products.add(prodResponse);
		}
		
//		ModelAndView m = new ModelAndView("ProductPage", "products", products);
		ProductResponse modelProd = new ProductResponse();
		modelProd.setProductSku(""); // this is to clean the values on the client side
		modelProd.setProductDescr(""); // this is to clean the values on the client side
		modelProd.setProductPicString(""); // this is to clean the values on the client side
		model.addAttribute("product", modelProd);
		model.addAttribute("products", products);
		return "ProductPage";
	}
	
/*	
	@RequestMapping(value = "/product/{productSku}", method=RequestMethod.GET)
	public ModelAndView getProductBySku(@PathVariable String productSku)  {
		Product product = JpaDriver.getProductById(productSku);
		
		if (product == null) {
			product = new Product();
			product.setProductSku(productSku);
			product.setProductDescr("Product not found in the system");
			product.setProductPic(null);
		}
		
		ModelAndView model = new ModelAndView("ProductPage"); // the name of the JSP page
		model.addObject("product", product);
		model.addObject("products", new ArrayList<Product>());
		return model;
		
	}
*/	
//	@RequestMapping(value = "/product/{productSku}", method=RequestMethod.GET)
	@RequestMapping("/product/{productSku}")
	public String getProductBySku(@PathVariable String productSku, Model model)  {
		logger.debug("in getProductBySku()");
		Product product = RepositoryUtilityImpl.getInstance().getProductById(productSku);
		ProductResponse prodResponse = new ProductResponse();
		prodResponse.setProductSku(productSku);
		
		if (product == null) {
			prodResponse.setProductDescr("Product not found in the system");
			prodResponse.setProductPicString("");
		}
		else {
			prodResponse.setProductDescr(product.getProductDescr());
			prodResponse.setProductPicString("data:image/jpg;base64," + Base64.encodeBase64String(product.getProductPic()));
		}
		
		List<ProductResponse> products = new ArrayList<ProductResponse>();
		for (Product aProduct : getOrderedProducts()) {
			ProductResponse prodResp = new ProductResponse();
			prodResp.setProductSku(aProduct.getProductSku());
			prodResp.setProductDescr(aProduct.getProductDescr());
			products.add(prodResp);
		}
		
		model.addAttribute("product", prodResponse);
		model.addAttribute("products", products);
		return "ProductPage";
		
	}
	
	/*
	 * This method provides only a short version of ProductResponse
	 * Called by both OrderPage and ProductPage
	 */
	@RequestMapping(value = "/checkProductAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public ProductResponse checkProduct(@RequestBody String productIdStr) {
		logger.debug("in checkProduct()");
		ObjectMapper mapper = new ObjectMapper();
		ProductRequest productObj = null;
		try {
			productObj = mapper.readValue(productIdStr, ProductRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		String productSku = productObj.getProductSku();
		logger.debug("product sku: "+productSku);
		Product product = RepositoryUtilityImpl.getInstance().getProductById(productSku);
		ProductResponse prodResponse = new ProductResponse();
		
		if (product != null) {
			prodResponse.setResult("found");
			prodResponse.setProductSku(product.getProductSku());
			prodResponse.setProductDescr(product.getProductDescr());
		}
		else {
			prodResponse.setResult("not found");
		}
		return prodResponse;
	}

	/*
	 * This method provides the full version of ProductResponse
	 */
	@RequestMapping(value = "/getProductAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public ProductResponse getProduct(@RequestBody String productIdStr) {
		logger.debug("in getProduct()");
		ObjectMapper mapper = new ObjectMapper();
		ProductRequest productObj = null;
		try {
			productObj = mapper.readValue(productIdStr, ProductRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		String productSku = productObj.getProductSku();
		logger.debug("product sku: "+productSku);
		Product product = RepositoryUtilityImpl.getInstance().getProductById(productSku);
		ProductResponse prodResponse = new ProductResponse();
		
		if (product != null) {
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
		}
		else {
			prodResponse.setResult("not found");
		}
		return prodResponse;
	}
	
	
	@RequestMapping(value = "/updateProductAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public ProductResponse updateProduct(@RequestBody String productStr) {
		logger.debug("in updateProduct()");
		ObjectMapper mapper = new ObjectMapper();
		ProductRequest prodRequest = null;
		try {
			prodRequest = mapper.readValue(productStr, ProductRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		String imgString = prodRequest.getProductPic();
		imgString = imgString.substring(22); // skip 22 bytes
		logger.debug("img string: "+imgString);
		
		byte[] img = Base64.decodeBase64(imgString);
		prodRequest.setProductPicBinary(img);
		
		ProductResponse prodResponse = new ProductResponse();
		
		// update database
		try {
			RepositoryUtilityImpl.getInstance().updateProduct(prodRequest);
			prodResponse.setResult("success");
		}
		catch (IllegalArgumentException ex) {
			prodResponse.setResult("failure");
		}
		
		return prodResponse;
	}

	/*
	 * This is the method called when the client clicks on the "Delete" button
	 */
	@RequestMapping(value = "/deleteProductAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public ProductResponse deleteProduct(@RequestBody String stringCustomerReq) {
		logger.debug("in deleteProduct()");
		ObjectMapper mapper = new ObjectMapper();
		ProductRequest prodRequest = null;
		try {
			prodRequest = mapper.readValue(stringCustomerReq, ProductRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		ProductResponse prodResponse = new ProductResponse();

		if ( itemsUseProduct(prodRequest) ) {
			prodResponse.setResult("has orders");
		}
		else {
			try {
				RepositoryUtilityImpl.getInstance().deleteProduct(prodRequest.getProductSku());
				prodResponse.setResult("success");
			}
			catch (Exception ex) {
				prodResponse.setResult("failure");
			}
		}
		
		return prodResponse;
	}
	
	private boolean itemsUseProduct(ProductRequest prodRequest) {
		return false;
	}
	
	private List<Product> getOrderedProducts() {
		// order customers by Id
		List<Product> productList = RepositoryUtilityImpl.getInstance().getProducts();
		Collections.sort(productList);
		return productList;
	}
	
}
