package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class PersonDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257713532+02:00", comments="Source field: people.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257782109+02:00", comments="Source field: people.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257867718+02:00", comments="Source field: people.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257942518+02:00", comments="Source field: people.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257740762+02:00", comments="Source field: people.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257764577+02:00", comments="Source field: people.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257803349+02:00", comments="Source field: people.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257832062+02:00", comments="Source field: people.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257893707+02:00", comments="Source field: people.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257917772+02:00", comments="Source field: people.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257968175+02:00", comments="Source field: people.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.257994344+02:00", comments="Source field: people.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}