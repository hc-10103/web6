package org.example.sb_webui_crud.domain;

public class Movie {
    private Long id;
    private String title;
    private String director;
    private String distributor;
    private float score;
    private boolean isAdultOnly;

    public Movie(Long id, String title, String director, String distributor, float score, boolean isAdultOnly) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.distributor = distributor;
        this.score = score;
        this.isAdultOnly = isAdultOnly;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isAdultOnly() {
        return isAdultOnly;
    }

    public void setAdultOnly(boolean adultOnly) {
        isAdultOnly = adultOnly;
    }
}
