package com.base.baseproject.utils.validation;

import android.content.Context;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.base.baseproject.R;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class Validation {

    //Constants
    public static final byte VALIDATE_EMAIL = 1;
    public static final byte VALIDATE_PASSWORD = 2;
    public static final byte VALIDATE_REPASSWORD = 3;
    public static final byte VALIDATE_PRE_PHONE = 4;

    // Declare the @IntDef for these constants
    @IntDef({VALIDATE_EMAIL, VALIDATE_PASSWORD,
            VALIDATE_REPASSWORD ,VALIDATE_PRE_PHONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidateType {}



    public static final byte ERROR_CODE_SUCCESS = 0;
    public static final byte ERROR_CODE_EMAIL = 1;
    public static final byte ERROR_CODE_PASSWORD_SHORT = 2;
    public static final byte ERROR_CODE_PASSWORD_NOT_MATCH = 3;
    public static final byte ERROR_CODE_PRE_PHONE_NOT_VALID = 4;
    // Declare the @IntDef for these constants
    @IntDef({ERROR_CODE_SUCCESS, ERROR_CODE_EMAIL,
            ERROR_CODE_PASSWORD_SHORT ,
            ERROR_CODE_PASSWORD_NOT_MATCH,ERROR_CODE_PRE_PHONE_NOT_VALID})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {}




    private int errorCode = ERROR_CODE_SUCCESS;

    public static String getMessage(Context ctx, @ErrorCode int errorCode){
        switch (errorCode){
            case ERROR_CODE_EMAIL :
                    return ctx.getString(R.string.email_is_not_valid);

            case ERROR_CODE_PASSWORD_SHORT :
                    return ctx.getString(R.string.password_short);

            case ERROR_CODE_PASSWORD_NOT_MATCH :
                    return ctx.getString(R.string.password_not_match);
        }

        return "";
    }

    @ErrorCode
    public static int validate(@ValidateType int validateType,Object... input){
        int errorCode = ERROR_CODE_SUCCESS;
        switch (validateType){
            case VALIDATE_EMAIL:
                String email = (String) input[0];
                if(email.length()<6 || email.length()>96 || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    errorCode = ERROR_CODE_EMAIL;
                }
                break;

            case VALIDATE_PASSWORD:
                String password = (String) input[0];
                if(password.length()<6) {
                    errorCode = ERROR_CODE_PASSWORD_SHORT;
                }
                break;

            case VALIDATE_REPASSWORD:
                String pass1 = (String) input[0];
                String pass2 = (String) input[1];

                if(!pass1.equals(pass2))
                    errorCode = ERROR_CODE_PASSWORD_NOT_MATCH;
                break;

            case VALIDATE_PRE_PHONE:
                String prePhoneStr = (String) input[0];
                if(!prePhoneStr.startsWith("+") && !prePhoneStr.startsWith("00")){
                    errorCode = ERROR_CODE_PRE_PHONE_NOT_VALID;
                }

                break;
        }

        return errorCode;
    }
}