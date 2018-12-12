package com.zhita.model.manage;

public class ShufflingFigure {
    private Integer id;

    private String title;

    private String cover;

    private String link;

    private String state;

    private String deleted;

    public ShufflingFigure(Integer id, String title, String cover, String link, String state, String deleted) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.link = link;
        this.state = state;
        this.deleted = deleted;
    }

    public ShufflingFigure() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}