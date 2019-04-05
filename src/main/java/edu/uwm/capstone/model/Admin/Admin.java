package edu.uwm.capstone.model.Admin;

public class Admin {
    private String email;
    private String first_name;
    private String last_name;
    private boolean read_r;
    private boolean write_w;
    private String pin;
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isRead_r() {
        return read_r;
    }

    public void setRead_r(boolean read_r) {
        this.read_r = read_r;
    }

    public boolean isWrite_w() {
        return write_w;
    }

    public void setWrite_w(boolean write_w) {
        this.write_w = write_w;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
