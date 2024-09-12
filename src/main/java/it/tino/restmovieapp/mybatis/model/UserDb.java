package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class UserDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.41831014+02:00", comments="Source field: users.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420769885+02:00", comments="Source field: users.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420943158+02:00", comments="Source field: users.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420397332+02:00", comments="Source field: users.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420709202+02:00", comments="Source field: users.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420816652+02:00", comments="Source field: users.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420897212+02:00", comments="Source field: users.username")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.420986839+02:00", comments="Source field: users.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.42103576+02:00", comments="Source field: users.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}