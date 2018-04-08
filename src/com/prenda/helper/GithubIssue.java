package com.prenda.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.prenda.Mode;
import com.prenda.servlet.RegisterOwner;

public class GithubIssue {

	private static Logger log = Logger.getLogger(GithubIssue.class);
	
	private Integer installId;
	private String token;
	
	public GithubIssue()  {
		Properties props = new Properties();
		try {
			props.load(RegisterOwner.class.getResourceAsStream("/env.properties"));
			installId = Integer.parseInt(props.getProperty("github.install"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int create(String title, String body, String username, String repo, String[] labels, String[] assignees,
			int tokenType) {
		int issue = 0;
		try {
			if (!exists(title, username, repo)) {
				JSONObject json = new JSONObject();
				json.put("title", title);
				json.put("body", body);
				JSONArray jsonArray = new JSONArray();
				for (String label : labels) {
					jsonArray.put(label);
				}
				json.put("labels", jsonArray);
				jsonArray = new JSONArray();
				for (String assignee : assignees) {
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
				//conn.setInstanceFollowRedirects(false);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("charset", "utf-8");
				conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
				conn.setUseCaches(false);
				conn.setRequestProperty("Content-Type", "application/json");
				if (tokenType == Mode.PAT) { // Personal Access Token only
					String encoded = Base64.getEncoder()
							.encodeToString((username + ":" + token).getBytes(StandardCharsets.UTF_8));
					conn.setRequestProperty("Authorization", "Basic " + encoded);
				} else if (tokenType == Mode.JWT) { // JSON Web Token
					conn.setRequestProperty("Authorization", "Token " + getToken());
				}
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
				wr.write(postData);
				int responseCode = conn.getResponseCode();
				log.info("Response Code : " + responseCode);
				if (responseCode == 201) {
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return issue;
	}

	private String getToken() {
		log.info("installId: " + installId);
		try {
			String request = "https://api.github.com/installations/" + installId + "/access_tokens";
			log.info(request);
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/vnd.github.machine-man-preview+json");
			conn.setRequestProperty("Authorization", "Bearer " + KeyUtil.getJws());
			int responseCode = conn.getResponseCode();
			log.info("Response Code : " + responseCode);
			if (responseCode == 201) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				log.info(response.toString());
				JSONObject myResponse = new JSONObject(response.toString());
				token = myResponse.getString("token");
				log.info("token: " + token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	protected String getRepo() {
		String name ="";
		try {
			String request = "https://api.github.com/installation/repositories";
			log.info(request);
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/vnd.github.machine-man-preview+json");
			conn.setRequestProperty("Authorization", "Token " + getToken());
			int responseCode = conn.getResponseCode();
			log.info("Response Code : " + responseCode);
			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				log.info(response.toString());
				JSONObject myResponse = new JSONObject(response.toString());
				JSONObject repo = myResponse.getJSONArray("repositories").getJSONObject(0);
				repo.getString("name");
				log.info("name: " + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	protected boolean exists(String title, String username, String repo) {
		boolean found = false;
		try {
			String request = "https://api.github.com/search/issues?q=" + URLEncoder.encode(title, "UTF-8")
					+ "+type:issue+in:title+repo:" + username + "%2f" + repo;
			log.info(request);
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			log.info("Response Code : " + responseCode);
			if (responseCode == 200) {
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
				if (count > 0) {
					found = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}
	
	protected String getATitle(String username, String repo) {
		String title = "";
		try {
			String request = "https://api.github.com/repos/"+username+"/"+repo+"/issues?state=all";
			log.info(request);
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			log.info("Response Code : " + responseCode);
			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				log.info(response.toString());
				JSONArray myResponse = new JSONArray(response.toString());
				JSONObject object = myResponse.getJSONObject(1);
				if(object.length()>0) {
					title = object.getString("title");
					log.info("title: " + title);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return title;
	}
}
