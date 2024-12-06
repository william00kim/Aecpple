package com.aecpple.aecpple;

public class User {
    private String USER_ID;
    private String PASSWORD;
    private String USERNAME;
    private String USERBIRTH;
    private boolean HANDICAP;

    public User(String USER_ID , String PASSWORD, String USERNAME, String USERBIRTH,boolean HANDICAP) {
        this.USER_ID = USER_ID;
        this.PASSWORD = PASSWORD;
        this.USERNAME = USERNAME;
        this.USERBIRTH = USERBIRTH;
        this.HANDICAP = HANDICAP;
    }

    // Getter/Setter 필요 시 추가
}

