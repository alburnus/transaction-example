package com.github.alburnus.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class Team extends BaseEntity {

    @Getter
    private String name;

    public void changeName(String newName) {
        this.name = newName;
    }
}
