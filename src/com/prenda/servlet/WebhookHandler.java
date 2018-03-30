package com.prenda.servlet;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebhookHandler {
	@RequestMapping(value = "common/Webhook.htm", method = RequestMethod.POST)
	@ResponseBody
	public String webhook(@RequestBody JSONObject request) {
		request.toString(3);
		JSONObject response = new JSONObject();
		response.put("status", "ok");
		return response.toString(3);
	}
}
