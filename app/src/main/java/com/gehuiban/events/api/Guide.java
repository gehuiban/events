package com.gehuiban.events.api;

/*
{
    "productIdentifier": "CU9sTi1g6JfbUW99HfgCYdaFY087qWsmtLqbP5kO",
    "bundleVersion": 0,
    "minAppVersion-Android": 60,
    "startDate": "2020-02-18T00:00:00",
    "minAppVersion-iOS": 30,
    "endDate": "2020-02-20T00:00:00",
    "gb_url": "gb://redeem/?guideId=173835",
    "venue": {
        "state": "FL",
        "city": "Palm Coast",
        "street": "200 Ocean Crest Dr",
        "country": "United States",
        "zipcode": "32137",
        "address": "200 Ocean Crest Dr, Palm Coast, FL 32137, USA"
    },
    "name": "116th Annual Meeting",
    "type": "guide",
    "icon": "https://s3.amazonaws.com/media.guidebook.com/service/CU9sTi1g6JfbUW99HfgCYdaFY087qWsmtLqbP5kO/logo.png?version=13",
    "ownerId": 14863,
    "loginRequired": false,
    "inviteOnly": false,
    "id": 173835,
    "guideVersion": 13
}
 */

import android.text.TextUtils;

public class Guide {
    private Integer id;
    private String startDate;
    private String endDate;
    private Venue venue;
    private String name;
    private String url;
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getLocation() {
        String location = "";
        if (venue == null) {
            return "Unknown";
        }
        String city = TextUtils.isEmpty(venue.getCity()) ? "city" : venue.getCity();
        String state = TextUtils.isEmpty(venue.getState()) ? " state" : venue.getState();
        location = venue.getCity() + ", " + venue.getState();
        return location;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
