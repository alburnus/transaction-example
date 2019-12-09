package com.github.alburnus.factory;

import com.github.alburnus.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamFactory {

    public Team createTeam(String name) {
        return Team.builder()
                .name(name)
                .build();
    }
}
