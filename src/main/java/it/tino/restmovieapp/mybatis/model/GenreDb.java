package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class GenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354650523+02:00", comments="Source field: genres.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35472962+02:00", comments="Source field: genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354683594+02:00", comments="Source field: genres.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354710154+02:00", comments="Source field: genres.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354753795+02:00", comments="Source field: genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354778351+02:00", comments="Source field: genres.name")
    public void setName(Object name) {
        this.name = name;
    }
}