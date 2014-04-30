package com.example.loginsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;



public class Server extends Login {

    static InputStream input = null;
    static JSONObject object = null;
    static java.lang.String json = "";

    public Server() {
    }

    public JSONObject getJSONFromUrl(URI url, List<NameValuePair> params) {

    	try {
    		DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            input = httpEntity.getContent();

        } 
    	catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
    	catch (ClientProtocolException e) {
            e.printStackTrace();
        } 
    	catch (IOException e) {
            e.printStackTrace();
        }

    	try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            java.lang.String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            input.close();
            json = sb.toString();
            Log.e("JSON", json);
        } 
    	catch (Exception e) {
            Log.e("Buffer Error", "Error " + e.toString());
        }
    	try {
            object = new JSONObject(json);
        } 
    	catch (JSONException e) {
            Log.e("JSON Parser", "Error " + e.toString());
            }
    	return object;
    	}
    
    JSONObject obj;
    
    public JSONObject getJSON(String url, List<NameValuePair> string) {
        String object = new String(url,string);
        Request task = new Request();
        
        try {
         obj= task.execute(string).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        return obj;
    }

    private static class String {
        String url;
        List<NameValuePair> string;

        String(String url, List<NameValuePair> string) {
            this.url = url;
            this.string = string;
        }
    }

    private class Request extends AsyncTask<URL, String, JSONObject> {
    	
    	@Override
        protected JSONObject doInBackground(URL... args) {

            Server request = new Server();
            JSONObject json = request.getJSONFromUrl(args[0].URL,args[0].String);

            return json;
        }

        public AsyncTask<URL, String, JSONObject> execute(
				List<NameValuePair> string) {
			return null;
		}

		@Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            }
        }
    }