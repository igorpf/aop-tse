package com.gincaneiro;

import com.gincaneiro.controllers.AuthenticationMocks;
import org.springframework.security.core.context.SecurityContextHolder;

public class TestUtils {

    private TestUtils(){}

    public static void setRegularUserAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(AuthenticationMocks.regularAuthentication());
    }

    public static void setPayingUserAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(AuthenticationMocks.payingAuthentication());
    }
}
