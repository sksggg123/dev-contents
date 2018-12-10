package com.goquality.devcontents.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Links extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 4000, nullable = false)
    private String link;

    @Column(length = 4000, nullable = false)
    private String title;

    @Builder
    public Links(String link, String title) {
        this.link = link;
        this.title = title;

    }
}
