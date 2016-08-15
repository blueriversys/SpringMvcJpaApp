package com.blueriver.beans;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.springframework.util.StringUtils;

public class TenantSessionCustomizer implements SessionCustomizer {
	  private static String schemaName;

	  public static void setSchemaName(String schemaName) {
		  TenantSessionCustomizer.schemaName = schemaName;
	  }

	  @Override
	  public void customize(Session session) throws Exception {
	      if (StringUtils.hasText(TenantSessionCustomizer.schemaName)) {
	          session.getLogin().setTableQualifier(TenantSessionCustomizer.schemaName);
	      }
	  }
	}