/**
 * 
 */
package com.jspmyadmin.app.common.beans;

import com.jspmyadmin.framework.constants.Constants;
import com.jspmyadmin.framework.web.utils.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yugandhar Gangu
 * @created_at 2016/09/05
 *
 */
public class AuthBean extends Bean {

	private static final long serialVersionUID = 3377912732082651254L;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
