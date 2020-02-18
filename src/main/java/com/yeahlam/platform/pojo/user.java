package com.yeahlam.platform.pojo;

public class user {
    private int id;
    private String account_id;
    private String name;
    private String  token;
    private long gmt_create;
    private long gm_modified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public long getGm_modified() {
        return gm_modified;
    }

    public void setGm_modified(long gm_modified) {
        this.gm_modified = gm_modified;
    }
}
