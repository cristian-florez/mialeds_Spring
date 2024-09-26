package com.mialeds.models;

public enum Role {

    ADMIN, USER;

    public static Role getRole(String role) {
        switch (role) {
            case "ADMIN":
                return ADMIN;
            case "USER":
                return USER;
            default:
                return null;
        }
    }
}
