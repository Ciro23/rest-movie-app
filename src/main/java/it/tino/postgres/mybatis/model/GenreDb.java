package it.tino.postgres.mybatis.model;

import jakarta.annotation.Generated;

public class GenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.436236896+02:00", comments="Source field: genres.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.43633591+02:00", comments="Source field: genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.436266842+02:00", comments="Source field: genres.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.436295064+02:00", comments="Source field: genres.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.436361798+02:00", comments="Source field: genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.436388328+02:00", comments="Source field: genres.name")
    public void setName(Object name) {
        this.name = name;
    }
}