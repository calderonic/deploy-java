package com.childcaresolutions.childcare_app.service;

import com.childcaresolutions.childcare_app.model.dto.request.RequestLogin;

public interface IAuthenticationService {

    Object login(RequestLogin data);
    String forgotPassword(String email);
    String setPassword(String email, String newPassword);
}
