package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class MovieDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259439553+02:00", comments="Source field: movies.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259506518+02:00", comments="Source field: movies.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259563684+02:00", comments="Source field: movies.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259619909+02:00", comments="Source field: movies.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259673659+02:00", comments="Source field: movies.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259746725+02:00", comments="Source field: movies.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259812096+02:00", comments="Source field: movies.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259466193+02:00", comments="Source field: movies.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259489145+02:00", comments="Source field: movies.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259526755+02:00", comments="Source field: movies.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259548706+02:00", comments="Source field: movies.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259584413+02:00", comments="Source field: movies.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259605051+02:00", comments="Source field: movies.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259638794+02:00", comments="Source field: movies.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259658921+02:00", comments="Source field: movies.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259698395+02:00", comments="Source field: movies.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25972838+02:00", comments="Source field: movies.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259767213+02:00", comments="Source field: movies.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259796136+02:00", comments="Source field: movies.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259837664+02:00", comments="Source field: movies.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259875184+02:00", comments="Source field: movies.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }
}