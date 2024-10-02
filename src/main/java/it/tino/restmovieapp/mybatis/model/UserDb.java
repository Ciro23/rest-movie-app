package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class UserDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.820771Z", comments="Source field: users.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822513Z", comments="Source field: users.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822733Z", comments="Source field: users.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822904Z", comments="Source field: users.email")
    private String email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.82204Z", comments="Source field: users.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822406Z", comments="Source field: users.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822579Z", comments="Source field: users.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822676Z", comments="Source field: users.username")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822789Z", comments="Source field: users.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822848Z", comments="Source field: users.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.822961Z", comments="Source field: users.email")
    public String getEmail() {
        return email;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.823018Z", comments="Source field: users.email")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}