package com.employee.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ServiceCall {
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	
	public static String organizationName(String organizationCode) {
		String sURL = "http://localhost:7171/api/v1/organization/"+organizationCode;
		String organizationName = "";
		JSONObject json = null;
		try {
			json = readJsonFromUrl(sURL);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		organizationName = json.get("data").toString();
		return organizationName;
	}

	public static String json(String sURL) {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(sURL);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	public static void main(String[] args) throws IOException, JSONException {
		String sURL = "";
		System.out.println(json(sURL));
	}
}