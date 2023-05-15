package com.trileuco.starwarsapi.model.swapi;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseSwapi {

    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

    public BaseSwapi(LocalDateTime created, LocalDateTime edited, String url) {
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public BaseSwapi() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseSwapi baseSwapi)) return false;
        return Objects.equals(created, baseSwapi.created) && Objects.equals(edited, baseSwapi.edited) && Objects.equals(url, baseSwapi.url);
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
