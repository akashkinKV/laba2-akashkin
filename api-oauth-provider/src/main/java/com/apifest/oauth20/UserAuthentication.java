package com.apifest.oauth20;

import api.AuthenticationException;
import api.IUserAuthentication;
import api.UserDetails;
import com.google.gson.JsonObject;
import org.apache.http.HttpHeaders;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.netty.handler.codec.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class UserAuthentication implements IUserAuthentication {

    @Override
    public UserDetails authenticate(String username, String password, HttpRequest authRequest) throws AuthenticationException {
        UserDetails userDetails = null;

        HttpPost post = new HttpPost("http://127.0.0.1:5000/users/authenticate");
        JsonObject json = new JsonObject();

        json.addProperty("username", username);
        json.addProperty("password", password);

        try {
            post.setEntity(new StringEntity(json.toString(), "UTF-8"));
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse httpResponse = client.execute(post);

            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new AuthenticationException("invalid username/password");
            } else {
                userDetails = createUserDetails(httpResponse);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    private UserDetails createUserDetails(HttpResponse response) {
        UserDetails details = null;
        InputStream input = null;
        try {
            input = response.getEntity().getContent();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> json = mapper.readValue(input, Map.class);
            details = new UserDetails(json.get("user_id").toString(), null);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return details;
    }
}