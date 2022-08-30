package com.urlshort.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="url_short")
public class UrlShortEntity {
    @Id
    @Column(name = "\"id\"")
    private String id;

    @Column(name = "\"url\"")
    private String url;

    @Column(name = "\"expire\"")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date expire;

}
