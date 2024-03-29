package dev.asjordi.models;

import java.time.LocalDate;

public class Url {

    private Integer id;
    private String url;
    private String hash;
    private LocalDate created;

    public Url() {}

    public Url(Integer id, String url, String hash, LocalDate created) {
        this.id = id;
        this.url = url;
        this.hash = hash;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Url{" + "id=" + id +
                ", url='" + url + '\'' +
                ", hash='" + hash + '\'' +
                ", created=" + created +
                '}';
    }
}
