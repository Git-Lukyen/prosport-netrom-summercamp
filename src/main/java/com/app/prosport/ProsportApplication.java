package com.app.prosport;

import com.app.prosport.dbobjects.Player;
import com.app.prosport.dbobjects.PlayerRepo;
import com.app.prosport.dbobjects.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProsportApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProsportApplication.class);
    @Autowired PlayerRepo pob;
    public static void main(String[] args)  {
        SpringApplication.run(ProsportApplication.class, args);
    }

    @Override
    public void run(String[] args) throws Exception {
        Player p1 = new Player();
        p1.setPlayerName("vasile");

        pob.save(p1);
    }
}
