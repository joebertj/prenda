package com.prenda.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class GithubIssue {
	
	private static Logger log = Logger.getLogger(GithubIssue.class);
	
	public int create(String title, String body, String username, String repo, String [] labels, String [] assignees, String token) {
		int issue = 0;
		if(exists(title,username,repo)) {
			return issue;
		}
		try {
			JSONObject json = new JSONObject();
			json.put("title", title);
			json.put("body", body);
			JSONArray jsonArray = new JSONArray();
			for(String label: labels) {
				jsonArray.put(label);
			}
			json.put("labels", jsonArray);
			jsonArray = new JSONArray();
			for(String assignee: assignees) {
				jsonArray.put(assignee);
			}
			json.put("assignees", jsonArray);
			log.info(json.toString(3));
			byte[] postData = json.toString().getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			String request = "https://api.github.com/repos/" + username + "/" + repo + "/issues";
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			conn.setUseCaches(false);
			//Personal Access Token only
			String encoded = Base64.getEncoder()
					.encodeToString((username + ":" + token).getBytes(StandardCharsets.UTF_8));
			conn.setRequestProperty("Authorization", "Basic " + encoded);
			/*
			KeyUtil ku = new KeyUtil();
			conn.setRequestProperty("Authorization", "Bearer " + ku.getJws());
			*/
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
			issue = myResponse.getInt("number");
			log.info("issue: " + issue);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return issue;
	}

	protected boolean exists(String title, String username, String repo) {
		boolean found=false;
		try {
			String request = "https://api.github.com/search/issues?q="+URLEncoder.encode(title,"UTF-8")+"+type:issue+in:title+repo:"+username+"%2f"+repo;
			log.info(request);
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
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
			int count = myResponse.getInt("total_count");
			log.info("total_count: " + count);
			if(count>0) {
				found = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}
}
