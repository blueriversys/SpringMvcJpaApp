package com.blueriver.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blueriver.beans.RepositoryUtilityImpl;
import com.blueriver.model.Product;
import com.blueriver.model.Tenant;
import com.blueriver.model.support.ProductRequest;
import com.blueriver.model.support.ProductResponse;
import com.blueriver.model.support.TenantRequest;
import com.blueriver.model.support.TenantResponse;

@Controller
public class TenantController {
	private Logger logger = Logger.getLogger(TenantController.class.getName());

	/*
	 * Returns the one single tenant in the database
	 */
	@RequestMapping(value="/tenant**", method=RequestMethod.GET)
	public String getTenant(Model m) {
		m.addAttribute("tenant", getTenantResponse());
		return "TenantPage";
	}

	/*
	 * This is the method that responds to the Submit button (or equivalent).
	 * It gets from the browser a tenant that includes a "multi-part" logo image
	 */
	@RequestMapping(value="/tenant", method=RequestMethod.POST)
	public String insertTenant(@RequestPart(value="fileContent", required=false) MultipartFile prodPicture, @ModelAttribute Tenant tenant, Model model) {
		
		if (prodPicture != null && prodPicture.getSize() > 0) {
			logger.debug("file name: "+prodPicture.getOriginalFilename());
			logger.debug("file size: "+prodPicture.getSize());
			BufferedImage image = null;
			try {
				image = ImageIO.read(prodPicture.getInputStream());
				tenant.setLogoPic(prodPicture.getBytes());
				tenant.setLogoFilename(prodPicture.getOriginalFilename());
				tenant.setPicWidth(image.getWidth());
				tenant.setPicHeight(image.getHeight());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			tenant.setLogoPic(null);
		}

		// add to database
		RepositoryUtilityImpl.getInstance().addTenant(tenant);
		
		TenantResponse modelTenant = new TenantResponse();
		modelTenant.setTenantId(""); // this is to clean the values on the client side
		modelTenant.setTenantName(""); // this is to clean the values on the client side
		modelTenant.setTenantLogoString(""); // this is to clean the values on the client side
		model.addAttribute("tenant", modelTenant);
		return "TenantPage";
	}
	
	/*
	 * This method provides only a short version of TenantResponse.
	 * This is invoked just to verify whether or not the tenant is already in the database.
	 */
	@RequestMapping(value = "/checkTenantAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public TenantResponse checkTenant(@RequestBody String tenantIdStr) {
		ObjectMapper mapper = new ObjectMapper();
		TenantRequest tenantObj = null;
		try {
			tenantObj = mapper.readValue(tenantIdStr, TenantRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		String tenantId = tenantObj.getTenantId();
		Tenant tenant = RepositoryUtilityImpl.getInstance().getTenantById(tenantId);
		TenantResponse tenantResponse = new TenantResponse();
		
		if (tenant != null) {
			tenantResponse.setResult("found");
			tenantResponse.setTenantId(tenant.getTenantId());
			tenantResponse.setTenantName(tenant.getTenantName());
		}
		else {
			tenantResponse.setResult("not found");
		}
		return tenantResponse;
	}

	/**
	 * This is used to update the tenant record.
	 * @param productStr
	 * @return
	 */
	@RequestMapping(value = "/updateTenantAjax", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public TenantResponse updateTenant(@RequestBody String tenantStr) {
		logger.debug("in updateTenant()");
		ObjectMapper mapper = new ObjectMapper();
		TenantRequest tenantRequest = null;
		try {
			tenantRequest = mapper.readValue(tenantStr, TenantRequest.class);
		}
		catch (Exception ex) {
			logger.debug(ex.getStackTrace());
		}
		
		String imgString = tenantRequest.getLogoPic();
		imgString = imgString.substring(22); // skip 22 bytes
		logger.debug("img string: "+imgString);
		
		byte[] img = Base64.decodeBase64(imgString);
		tenantRequest.setLogoPicBinary(img);
		
		TenantResponse tenantResponse = new TenantResponse();
		
		// update database
		try {
			RepositoryUtilityImpl.getInstance().updateTenant(tenantRequest);
			tenantResponse.setResult("success");
		}
		catch (IllegalArgumentException ex) {
			tenantResponse.setResult("failure");
		}
		
		return tenantResponse;
	}

	public static TenantResponse getTenantResponse() {
		TenantResponse resp = new TenantResponse();
		Tenant tenant = RepositoryUtilityImpl.getInstance().getTenant();
		if (tenant != null) {
			resp.setTenantId(tenant.getTenantId());
			resp.setTenantName(tenant.getTenantName());
			resp.setTenantPic(tenant.getLogoPic());
			resp.setTenantLogoString("data:image/jpg;base64," + Base64.encodeBase64String(tenant.getLogoPic()));
			resp.setPicWidth(tenant.getLogoWidth());
			resp.setPicHeight(tenant.getLogoHeight());
		}
		else {
			resp.setTenantId("");
			resp.setTenantName("");
		}
		return resp;
	}
}
