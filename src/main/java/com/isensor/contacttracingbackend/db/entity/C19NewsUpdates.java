package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_news_updates")
public class C19NewsUpdates implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "title")
    public String title;

    @Column(name = "short_description")
    public String shortDescription;

    @Column(name = "url")
    public String URL;

    @Column(name = "insert_time")
    public Long InsertTime;

    @Column(name = "provider")
    public String provider;

    public C19NewsUpdates() {
    }

    public C19NewsUpdates(Long id, String title, String shortDescription, String URL, Long insertTime, String provider) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.URL = URL;
        InsertTime = insertTime;
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "C19NewsUpdates{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", URL='" + URL + '\'' +
                ", InsertTime=" + InsertTime +
                ", provider='" + provider + '\'' +
                '}';
    }
}

