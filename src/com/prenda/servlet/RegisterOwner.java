package com.prenda.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.Level;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.RegisterService;
import com.prenda.service.UserService;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

@Controller
public class RegisterOwner {

	private static Logger log = Logger.getLogger(RegisterOwner.class);

	@RequestMapping(value = "register/RegisterOwner.htm", method = RequestMethod.POST)
	@Transactional
	private String register(HttpSession session, ModelMap map, @RequestParam("referer") String redirectUrl,
			@RequestParam("user") String targetUser, @RequestParam("email") String email,
			@RequestParam("pass1") String newPassword, @RequestParam("pass2") String verifyPassword,
			@RequestParam("lname") String lastName, @RequestParam("fname") String firstName,
			@RequestParam("mname") String middleName,
			@RequestParam("g-recaptcha-response") String gRecaptchaResponse) {
		UserModify um = new UserModify();
		String message;
		if (recaptcha(gRecaptchaResponse)) {
			String success = "Please check your email and follow instructions to complete registration";
			message = um.createNewOwner(targetUser, newPassword, verifyPassword, Level.OWNER, email,
					lastName, firstName, middleName);
			if(!message.equals(success)){
				redirectUrl = "register/newuser";
			}
			map.addAttribute("msg", message);
		}else {
			map.addAttribute("msg", "Only humans are allowed to register");
			redirectUrl = "register/newuser";
		}
		return redirectUrl;
	}

	public boolean recaptcha(String gRecaptchaResponse) {
		boolean isHuman = false;
		try {
			Properties props = new Properties();
			props.load(RegisterOwner.class.getResourceAsStream("/sjm.properties"));
			String secret = props.getProperty("reCaptcha.secret");
			log.info("secret " + secret);
			String urlParameters  = "secret="+ secret+"&response="+gRecaptchaResponse;
			byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			String request        = "https://www.google.com/recaptcha/api/siteverify";
			URL    url            = new URL( request );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			conn.setUseCaches( false );
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			int responseCode = conn.getResponseCode();
			log.info("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			log.info(response.toString());
			JSONObject myResponse = new JSONObject(response.toString());
			isHuman = myResponse.getBoolean("success");
			log.info("success: " + isHuman);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return isHuman;
	}

	@RequestMapping(value = "register/ActivateOwner.htm", method = { RequestMethod.GET, RequestMethod.POST })
	@Transactional
	private String activatePost(HttpSession session, ModelMap map, @RequestParam("user") String targetUser,
			@RequestParam("key") String password) {
		String message = "Either key is invalid or user is not registered";
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		RegisterService rs = new RegisterService();
		String key = rs.getKey(user.getId());
		if (key.equals(password)) {
			if (user.isArchive()) {
				user.setArchive(false);
				DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
				dataLayerPrenda.update(user);
				dataLayerPrenda.flushAndClearSession();
				message = "User activated";
			} else {
				message = "User already activated";
			}
		}
		map.addAttribute("msg", message);
		return "redirect:../common/login.jsp";
	}
}
