package com.MovieBackIn.registeration;

import java.sql.Timestamp;

public class movie {
	private int id;
	private String m_name;
    private String location;
    private Timestamp date;
    private String theatre;
    private String screen;
    private String seller;
    private String genre;
    private String seat_no;
    private String buyer;
    private float price;

	public movie(int id,String m_name, String location, Timestamp date, String theatre, String screen,String seller,String genre,String seat_no,float price) {
        this.id=id;
		this.m_name = m_name;
        this.location = location;
        this.date = date;
        this.theatre = theatre;
        this.screen = screen;
        this.seller = seller;
        this.genre = genre;
        this.seat_no = seat_no;
        this.price=price;
    }
	public void setBuyer(String x) {
		this.buyer=x;
	}
	public String getbuyer() {
		return buyer;
	}
	public int getid() {
        return id;
    }

    public String getm_name() {
        return m_name;
    }
    public String getBuyer() {
        return buyer;
    }

    public String getlocation() {
        return location;
    }

    public Timestamp getdate() {
        return date;
    }

    public String gettheatre() {
        return theatre;
    }

    public String getUser_role() {
        return screen;
    }

    public String getseller() {
        return seller;
    }
    public String getgenre() {
        return genre;
    }
    public String getseat_no() {
        return seat_no;
    }
    public float getprice() {
        return price;
    }

}

