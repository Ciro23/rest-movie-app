package it.tino.postgres.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class PersonDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437744521+02:00", comments="Source field: people.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437820722+02:00", comments="Source field: people.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437884551+02:00", comments="Source field: people.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437952668+02:00", comments="Source field: people.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.43777116+02:00", comments="Source field: people.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.43779253+02:00", comments="Source field: people.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437843264+02:00", comments="Source field: people.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437865626+02:00", comments="Source field: people.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437908195+02:00", comments="Source field: people.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437932891+02:00", comments="Source field: people.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437974408+02:00", comments="Source field: people.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.437996389+02:00", comments="Source field: people.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}