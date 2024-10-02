package it.tino.restmovieapp.mybatis.model;

import jakarta.annotation.Generated;
import java.util.Date;

public class MovieDb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881356Z", comments="Source field: movies.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881466Z", comments="Source field: movies.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881714Z", comments="Source field: movies.release_date")
    private Date releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881851Z", comments="Source field: movies.budget")
    private Integer budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881973Z", comments="Source field: movies.box_office")
    private Integer boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882107Z", comments="Source field: movies.runtime")
    private Integer runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.88223Z", comments="Source field: movies.overview")
    private String overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881391Z", comments="Source field: movies.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881435Z", comments="Source field: movies.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881501Z", comments="Source field: movies.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881533Z", comments="Source field: movies.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881764Z", comments="Source field: movies.release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881815Z", comments="Source field: movies.release_date")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.88189Z", comments="Source field: movies.budget")
    public Integer getBudget() {
        return budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.881939Z", comments="Source field: movies.budget")
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882014Z", comments="Source field: movies.box_office")
    public Integer getBoxOffice() {
        return boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882051Z", comments="Source field: movies.box_office")
    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882145Z", comments="Source field: movies.runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882192Z", comments="Source field: movies.runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882271Z", comments="Source field: movies.overview")
    public String getOverview() {
        return overview;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.882318Z", comments="Source field: movies.overview")
    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }
}