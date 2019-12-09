package com.github.alburnus.controller;

import com.github.alburnus.model.Team;
import com.github.alburnus.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/team")
@Slf4j
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public String save(@RequestParam("name") String name, @RequestParam("sleep") Long sleep) {
        log.info("Start save team");
        teamService.createAndSave(name, sleep);
        return "";
    }

    @PutMapping
    public String update(@RequestParam("id") Long id, @RequestParam("name") String newName) {
        log.info("Start update team");
        teamService.update(id, newName);
        return "";
    }


    @GetMapping
    public List<Team> getAll() {
        log.info("Start getAll team");
        return teamService.getAll();
    }

}
