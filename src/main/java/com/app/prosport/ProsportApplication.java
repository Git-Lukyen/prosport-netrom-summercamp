package com.app.prosport;

import com.app.prosport.dbobjects.player.Player;
import com.app.prosport.dbobjects.player.PlayerRepo;
import com.app.prosport.dbobjects.team.TeamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProsportApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProsportApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProsportApplication.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {

    }
}
