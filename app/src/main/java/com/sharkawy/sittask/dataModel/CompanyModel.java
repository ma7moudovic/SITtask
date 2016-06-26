package com.sharkawy.sittask.dataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by T on 6/23/2016.
 */
public class CompanyModel {
//    "id": 31,
//            "nameen": "Al Nakheel Agriculture & Trading Wll",
//            "managername": "Mr. Henri Pavageau",
//            "addressen": "7th Flr, Al Mana Business Tower, Suhaim Bin Hamad St, C Ring Rd",
//            "postalcode": "8873, Doha - Qatar",
//            "phone": "+974 4408 5333",
//            "mobile": null,

//            "fax": "+974 4498 3420",
//            "website": "www.alnakheel.com.qa",
//            "phone1": null,
//            "email": "h.pavageau@alnakheel.com.qa",
//            "createddate": "2016-05-04",
//            "descreption": "Al Nakheel Agriculture & Trading (now Nakheel Landscapes) was born in 1998 and today is one of the leading companies in Middle East for the construction of landscapes in arid climate. The Company is based in Doha, Qatar. The Companys activities cover the whole range of modern public realm landscaping, hard landscape, soft landscape, irrigation systems, sports turf pitches and more\n",
//            "facebook": null,
//            "twitter": null,
//            "googelplus": null,
//            "linkedin": null,
//            "gradeid": "1",
//            "longitude": null,
//            "latitude": null,
//            "imagename": "http://sit-egypt.net/qbusiness-new/upload/company/a11756f3310757caa322f5c1c48d9da5.jpg",
//            "images": [
//    {
//        "name": "http://sit-egypt.net/qbusiness-new/upload/cimage/76612ef35101b2426e8a2c89991a3c1f.jpg"
//    },
//    {
//        "name": "http://sit-egypt.net/qbusiness-new/upload/cimage/13ee07a78f3a755958afe3cbb57a806d.jpg"
//    },
//    {
//        "name": "http://sit-egypt.net/qbusiness-new/upload/cimage/28571e3cd223e29339055ae85c4ba596.jpg"
//    },
//    {
//        "name": "http://sit-egypt.net/qbusiness-new/upload/cimage/ea9bc26bfa8c35a4380e8f6c511c71ed.jpg"
//    }
//    ],
//            "products": []

    String id ;
    String nameen ;
    String managername ;
    String addressen ;
    String postalcode;
    String phone;
    String mobile;
    String fax;
    String website ;
    String phone1 ;
    String email ;
    String createddate ;
    String descreption ;
    String facebook ;
    String twitter ;
    String googelplus;
    String linkedin;
    String gradeid;
    String longitude;
    String latitude;
    String imagename;
    JSONArray images;
    JSONArray products ;
    JSONObject object ;
    public CompanyModel() {
    }

    public CompanyModel(JSONObject object) {
        this.object = object;
        try {
            this.id = object.getString("id");
            this.nameen = object.getString("nameen");
            this.managername = object.getString("managername");
            this.addressen = object.getString("addressen");
            this.postalcode = object.getString("postalcode");
            this.phone = object.getString("phone");
            this.mobile = object.getString("mobile");
            this.fax = object.getString("fax");
            this.website = object.getString("website");
            this.phone1 = object.getString("phone1");
            this.email = object.getString("email");
            this.createddate = object.getString("createddate");
            this.descreption = object.getString("descreption");
            this.facebook = object.getString("facebook");
            this.twitter = object.getString("twitter");
            this.googelplus = object.getString("googelplus");
            this.linkedin = object.getString("linkedin");
            this.gradeid = object.getString("gradeid");
            this.longitude = object.getString("longitude");
            this.latitude = object.getString("latitude");
            this.imagename =object.getString("imagename");
            this.images = object.getJSONArray("images");
            this.products = object.getJSONArray("products");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CompanyModel(String id, String nameen, String managername, String addressen, String postalcode, String phone, String mobile, String fax, String website, String phone1, String email, String createddate, String descreption, String facebook, String twitter, String googelplus, String linkedin, String gradeid, String longitude, String latitude, String imagename, JSONArray images, JSONArray products) {
        this.id = id;
        this.nameen = nameen;
        this.managername = managername;
        this.addressen = addressen;
        this.postalcode = postalcode;
        this.phone = phone;
        this.mobile = mobile;
        this.fax = fax;
        this.website = website;
        this.phone1 = phone1;
        this.email = email;
        this.createddate = createddate;
        this.descreption = descreption;
        this.facebook = facebook;
        this.twitter = twitter;
        this.googelplus = googelplus;
        this.linkedin = linkedin;
        this.gradeid = gradeid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imagename = imagename;
        this.images = images;
        this.products = products;
    }

    public CompanyModel(String id, String nameen, String managername, String addressen, String postalcode, String phone, String mobile, String fax, String website, String phone1, String email, String createddate, String descreption, String facebook, String twitter, String googelplus, String linkedin, String gradeid, String longitude, String latitude, String imagename, JSONArray images, JSONArray products, JSONObject object) {
        this.id = id;
        this.nameen = nameen;
        this.managername = managername;
        this.addressen = addressen;
        this.postalcode = postalcode;
        this.phone = phone;
        this.mobile = mobile;
        this.fax = fax;
        this.website = website;
        this.phone1 = phone1;
        this.email = email;
        this.createddate = createddate;
        this.descreption = descreption;
        this.facebook = facebook;
        this.twitter = twitter;
        this.googelplus = googelplus;
        this.linkedin = linkedin;
        this.gradeid = gradeid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imagename = imagename;
        this.images = images;
        this.products = products;
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public String getNameen() {
        return nameen;
    }

    public String getManagername() {
        return managername;
    }

    public String getAddressen() {
        return addressen;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getFax() {
        return fax;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateddate() {
        return createddate;
    }

    public String getDescreption() {
        return descreption;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getGoogelplus() {
        return googelplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGradeid() {
        return gradeid;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getImagename() {
        return imagename;
    }

    public JSONArray getImages() {
        return images;
    }

    public JSONArray getProducts() {
        return products;
    }

    public JSONObject getObject() {
        return object;
    }
}
