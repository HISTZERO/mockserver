package com.example.mockserver;


public final class Constants {
    private Constants() {
    }

    //TODO sua lai cach dat ten hang so
    public static final int TOKEN_SIZE = 32;
    public static final int MAX_SIZE = 256;
    public static final int CONTENT_MAX_SIZE = 2048;


    //Api Status

    public static final String OK = "ok";
    public static final String BAD = "bad";

    //Error code
    public static final String INVALID_PARAMETER = "invalidParameter";

    public static final String USERNAME_EXISTED = "usernameExisted";
    public static final String EMAIL_EXISTED = "emailExisted";
    public static final String WRONG_INFORMATION = "wrongInformation";
    public static final String PROJECT_EXISTED = "projectExisted";
    public static final String PROJECT_NOT_FOUND = "projectNotFound";

    public static final String API_NOT_FOUND = "apiNotFound";
    public static final String API_EXISTED = "apiExisted";
    public static final String STATUS_NOT_FOUND = "statusNotFound";


    //Error message
    public static final String TOKEN_INVALID_MESSAGE = "Token is invalid";
    public static final String NOT_EXISTED_MESSAGE = " is not found";
    public static final String USER_NAME_FIELD = "username";
    public static final String USER = "user";
    public static final String PASSWORD_FIELD = "password";
    public static final String EMAIL_FIELD = "email";
    public static final String TOKEN = "token";
    public static final String PROJECT = "projectName";
    public static final String FIELD_ALREADY_EXISTS_MESSAGE = "has existed ";
    public static final String FIELD_LONG_MESSAGE = "is too long ";
    public static final String TOKEN_SIZE_MESSAGE = "Token must be " + TOKEN_SIZE + " characters";

    public static final String CONFIRM_PASSWORD_MESSAGE = "Password and confirm password must be the same";
    public static final String CONFIRM_PASSWORD = "confirmPassword";
    public static final String WRONG_USER = "Wrong username or password";

    public static final String FIELD_PROJECT_NAME ="projectName";
    public static final String FIELD_API_PREFIX ="apiPrefix";
    public static final String API ="api";
    public static final String STATUS ="status";
    public static final String API_NAME ="apiName";
    public static final String PATH ="path";
    public static final String CONTENT ="content";








    //Delete Flag
    public static final int DELETED = 1;
    public static final int NOT_DELETED = 0;

    //Success message
    public static final String SUCCESSFULLY_MESSAGE = "Successfully";



}
