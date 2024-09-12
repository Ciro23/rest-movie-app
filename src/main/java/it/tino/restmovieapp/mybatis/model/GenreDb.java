package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;

public class GenreDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.256349614+02:00", comments="Source field: genres.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.256432839+02:00", comments="Source field: genres.name")
    private Object name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.256385561+02:00", comments="Source field: genres.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.256412651+02:00", comments="Source field: genres.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.256452615+02:00", comments="Source field: genres.name")
    public Object getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.256474707+02:00", comments="Source field: genres.name")
    public void setName(Object name) {
        this.name = name;
    }
}