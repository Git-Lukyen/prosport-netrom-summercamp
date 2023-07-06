package com.app.prosport.dbobjects.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prosport/")
public class PlayerRestController {
    @Autowired
    PlayerRepo playerRepo;

    @GetMapping(value = "players")
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    @GetMapping(value = "players/{id}")
    public Optional<Player> findPlayerByID(@PathVariable(value = "id") Integer ID) {
        return playerRepo.findById(ID);
    }

    @DeleteMapping(value = "players")
    public void deleteAllPlayers() {
        playerRepo.deleteAll();
    }

}
