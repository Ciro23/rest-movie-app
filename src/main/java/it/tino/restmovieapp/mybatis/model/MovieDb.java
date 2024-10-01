package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class MovieDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637402443+02:00", comments="Source field: movies.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637462154+02:00", comments="Source field: movies.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637528227+02:00", comments="Source field: movies.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637604429+02:00", comments="Source field: movies.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637728209+02:00", comments="Source field: movies.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637838644+02:00", comments="Source field: movies.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637898976+02:00", comments="Source field: movies.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637426668+02:00", comments="Source field: movies.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637445183+02:00", comments="Source field: movies.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63749194+02:00", comments="Source field: movies.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637511105+02:00", comments="Source field: movies.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63755141+02:00", comments="Source field: movies.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63757825+02:00", comments="Source field: movies.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637634725+02:00", comments="Source field: movies.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637662417+02:00", comments="Source field: movies.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637770908+02:00", comments="Source field: movies.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637807777+02:00", comments="Source field: movies.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637859202+02:00", comments="Source field: movies.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637882355+02:00", comments="Source field: movies.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637918513+02:00", comments="Source field: movies.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.637941986+02:00", comments="Source field: movies.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }
}