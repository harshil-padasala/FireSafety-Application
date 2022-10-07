package com.example.firesafety;

public class UserInfo {
    public String name, address, area, email, phone;
    public boolean isAdmin;

    public UserInfo(String name, String address, String area, String email, String phone, boolean isAdmin) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.email = email;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
