package com.MovieBackIn.registeration;

import java.io.Serializable;

public class bubobj implements Serializable {
	private int user_id;
    private String user_name;
    private String user_email;
    private String user_mobile;
    private String user_role;
    private String ban_flag;
    public bubobj() {
    }
    public bubobj(int u_id,String user_name, String user_email, String user_mobile, String user_role, String ban_flag) {
        this.user_id=u_id;
    	this.user_name = user_name;
        this.user_email = user_email;
        this.user_mobile = user_mobile;
        this.user_role = user_role;
        this.ban_flag = ban_flag;
    }
    public int getUser_id() {
        return user_id;
    }
    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public String getUser_role() {
        return user_role;
    }

    public String getBan_flag() {
        return ban_flag;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}