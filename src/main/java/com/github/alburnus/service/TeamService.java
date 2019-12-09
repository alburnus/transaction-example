package com.github.alburnus.service;

import com.github.alburnus.factory.TeamFactory;
import com.github.alburnus.model.Team;
import com.github.alburnus.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;

    private final TeamFactory teamFactory;

    public TeamService(TeamRepository teamRepository, TeamFactory teamFactory) {
        this.teamRepository = teamRepository;
        this.teamFactory = teamFactory;
    }

    @Transactional
    public void createAndSave(String name, long sleepValue) {
        log.info("Start Create and Save team");
        try {
            TimeUnit.SECONDS.sleep(sleepValue);
            teamRepository.save(teamFactory.createTeam(name));
            log.info("Saved team");
            TimeUnit.SECONDS.sleep(sleepValue);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info("Finish Create and Save team");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Long id, String name) {
        log.info("Start update for name [{}]", name);
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            log.info("On id [{}] found team name [{}]", id, team.get().getName());
            Team foundTeam = team.get();
            foundTeam.changeName(name);
            try {
                TimeUnit.SECONDS.sleep(10);
                teamRepository.save(foundTeam);
                log.info("Updated team to name [{}]", name);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Transactional
    public List<Team> getAll() {
        return teamRepository.findAll();
    }
}
