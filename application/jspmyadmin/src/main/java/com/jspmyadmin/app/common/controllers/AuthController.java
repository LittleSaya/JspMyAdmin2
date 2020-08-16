/**
 * 
 */
package com.jspmyadmin.app.common.controllers;

import com.jspmyadmin.app.common.beans.AuthBean;
import com.jspmyadmin.app.common.beans.InstallBean;
import com.jspmyadmin.app.common.logic.InstallLogic;
import com.jspmyadmin.framework.connection.ConnectionFactory;
import com.jspmyadmin.framework.constants.AppConstants;
import com.jspmyadmin.framework.constants.Constants;
import com.jspmyadmin.framework.exception.EncodingException;
import com.jspmyadmin.framework.web.annotations.*;
import com.jspmyadmin.framework.web.logic.EncodeHelper;
import com.jspmyadmin.framework.web.utils.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * @author LittleSaya
 * @created_at 2020/08/10
 *
 */
@WebController(authentication = false, path = "/auth.html", requestLevel = RequestLevel.DEFAULT)
public class AuthController {

	@Detect
	private RequestAdaptor requestAdaptor;
	@Detect
	private RedirectParams redirectParams;
	@Detect
	private EncodeHelper encodeObj;
	@Detect
	private View view;
	@Detect
	private HttpSession session;
	@Model
	private AuthBean bean;

	private static final String salt = "WDS";

	@HandleGet
	private void load() throws EncodingException, SQLException {

		bean.setToken(requestAdaptor.generateToken());
		view.setType(ViewType.FORWARD);
		view.setPath(AppConstants.JSP_COMMON_AUTH);
	}

	@HandlePost
	@ValidateToken
	private void save() {
		// calculate currently correct password
		String currentPassword = _calcPassword();
		String providedPassword = bean.getPassword();
		System.out.println("currentPassword = " + currentPassword + ", providedPassword = " + providedPassword);
		if (currentPassword.equals(providedPassword)) {
			// set auth flag
			session.setAttribute(Constants.AUTH_FLAG, "1");
			view.setType(ViewType.REDIRECT);
			view.setPath("/home.html");
		} else {
			// redirect to auth page
			view.setType(ViewType.REDIRECT);
			view.setPath("/auth.html");
		}
	}

	private String _calcPassword () {
		String plain = salt + new Date().getTime() / 60000;
		String hashed = encryptPasswordWithSHA512(plain);
		return hashed.substring(0, 8);
	}

	private static String encryptPasswordWithSHA512(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");  //创建SHA512类型的加密对象
			messageDigest.update(password.getBytes());
			byte[] bytes = messageDigest.digest();
			StringBuffer strHexString = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xff & bytes[i]);
				if (hex.length() == 1) {
					strHexString.append('0');
				}
				strHexString.append(hex);
			}
			String result = strHexString.toString();
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
