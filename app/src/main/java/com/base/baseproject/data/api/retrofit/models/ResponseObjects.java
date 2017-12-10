package com.base.baseproject.data.api.retrofit.models;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class ResponseObjects {
    public static final class LoginResponse{
        public String token;
        public boolean success;
        public String message;
    }

    public static final class ErrorAnswer {
        public String error;
        public String error_description;
    }

}
