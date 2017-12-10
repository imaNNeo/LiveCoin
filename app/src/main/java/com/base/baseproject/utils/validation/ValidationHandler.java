package com.base.baseproject.utils.validation;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class ValidationHandler {
    @Validation.ErrorCode
    public static int isValidEmail(String email){
        return Validation.validate(Validation.VALIDATE_EMAIL,email);
    }

    @Validation.ErrorCode
    public static int isValidPassword(String password){
        return Validation.validate(Validation.VALIDATE_PASSWORD,password);
    }

    @Validation.ErrorCode
    public static int isValidRePassword(String pass1, String pass2){
        return Validation.validate(Validation.VALIDATE_REPASSWORD,pass1,pass2);
    }

    @Validation.ErrorCode
    public static int isValidPrePhoneNumber(String prePhone){
        return Validation.validate(Validation.VALIDATE_PRE_PHONE,prePhone);
    }
}