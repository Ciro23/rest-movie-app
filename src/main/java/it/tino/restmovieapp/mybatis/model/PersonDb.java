package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class PersonDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878048Z", comments="Source field: people.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878173Z", comments="Source field: people.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878284Z", comments="Source field: people.birth")
    private Date birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878402Z", comments="Source field: people.gender")
    private String gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.87854Z", comments="Source field: people.last_name")
    private String lastName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878094Z", comments="Source field: people.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878137Z", comments="Source field: people.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.87821Z", comments="Source field: people.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.87825Z", comments="Source field: people.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878327Z", comments="Source field: people.birth")
    public Date getBirth() {
        return birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878369Z", comments="Source field: people.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878444Z", comments="Source field: people.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878495Z", comments="Source field: people.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878591Z", comments="Source field: people.last_name")
    public String getLastName() {
        return lastName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.878694Z", comments="Source field: people.last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }
}