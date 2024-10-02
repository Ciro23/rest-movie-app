package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class GenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.872642Z", comments="Source field: genres.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.87299Z", comments="Source field: genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.872698Z", comments="Source field: genres.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.872943Z", comments="Source field: genres.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.873039Z", comments="Source field: genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.873085Z", comments="Source field: genres.name")
    public void setName(Object name) {
        this.name = name;
    }
}