package com.trileuco.starwarsapi.model.swapi;

import java.time.LocalDateTime;

public abstract class BaseSwapi {

    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

    public BaseSwapi(LocalDateTime created, LocalDateTime edited, String url) {
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BaseDaoSwapi{" +
                "created=" + created +
                ", edited=" + edited +
                ", url='" + url + '\'' +
                '}';
    }
}
