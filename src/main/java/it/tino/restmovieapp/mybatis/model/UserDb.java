package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class UserDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.618531384+02:00", comments="Source field: users.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.619948358+02:00", comments="Source field: users.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.620095161+02:00", comments="Source field: users.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.620197902+02:00", comments="Source field: users.email")
    private String email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.619683496+02:00", comments="Source field: users.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.619902363+02:00", comments="Source field: users.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.619983383+02:00", comments="Source field: users.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.620055868+02:00", comments="Source field: users.username")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.620135657+02:00", comments="Source field: users.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.620169199+02:00", comments="Source field: users.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.620231625+02:00", comments="Source field: users.email")
    public String getEmail() {
        return email;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.62026638+02:00", comments="Source field: users.email")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}