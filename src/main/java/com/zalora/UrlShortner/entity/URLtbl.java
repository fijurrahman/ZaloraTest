package com.zalora.UrlShortner.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import java.util.Date;


/**
 * In Memory table used to Presist the data in the memory for fast access.
 * Todo -> We may use NoSQL to Load the url for better performance read operations.
 */

@Entity
public class URLtbl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @NaturalId
    @Column(unique = true , nullable = true)
    private String alias;


    @Column(nullable = false)
    private String url;

    @Column(name = "creationTime",updatable = false )
    private Date createDate ;

    @PrePersist
    public void onCreate()
    {
        createDate = new Date();
    }

    public URLtbl()
    {

    }

    public URLtbl(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Redirect{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
