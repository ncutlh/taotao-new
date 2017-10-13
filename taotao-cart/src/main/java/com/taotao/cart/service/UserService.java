package com.taotao.cart.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.bean.User;
import com.taotao.common.service.ApiService;

@Service
public class UserService {

    @Autowired
    private ApiService apiService;

    @Value("${TAOTAO_SSO_URL}")
    public String TAOTAO_SSO_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public User queryUserByToken(String token) {
        try {
            String url = TAOTAO_SSO_URL + "/service/user/" + token;
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isEmpty(jsonData)) {
                return null;
            }
            return MAPPER.readValue(jsonData, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
