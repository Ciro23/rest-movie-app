package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class GenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634275901+02:00", comments="Source field: genres.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634358094+02:00", comments="Source field: genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634303703+02:00", comments="Source field: genres.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634327147+02:00", comments="Source field: genres.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634379634+02:00", comments="Source field: genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634399541+02:00", comments="Source field: genres.name")
    public void setName(Object name) {
        this.name = name;
    }
}