package com.yeahlam.platform.Provider;

import com.alibaba.fastjson.JSON;
import com.yeahlam.platform.DTO.accessTokenDTO;
import com.yeahlam.platform.DTO.githubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class githubProvider {

    public String getAccessToken(accessTokenDTO accessTokenDTO) {
        MediaType MediaTypeJSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), MediaTypeJSON);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string().split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public githubUserDTO getUserByToken(String token) {
        OkHttpClient client = new OkHttpClient();
        if (token == "" || "bad_verification_code".equals(token)) {
            return null;
        }
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(), githubUserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
