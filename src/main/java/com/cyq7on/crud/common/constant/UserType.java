package com.cyq7on.crud.common.constant;

public enum UserType {
    Admin(0),
    User(1);

    private int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
