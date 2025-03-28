package com.THS_GROUP;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.THS_GROUP.Entyties.Etudiant;
import com.THS_GROUP.Entyties.Formation;
import com.THS_GROUP.Entyties.Inscription;
import com.THS_GROUP.Entyties.Personnels;
import com.THS_GROUP.Entyties.StatusInscription;
import com.THS_GROUP.Services.EtudiantService;
import com.THS_GROUP.Services.FormationService;
import com.THS_GROUP.Services.InscriptionService;
import com.THS_GROUP.Services.PersonnelService;

import java.util.Date;

@SpringBootApplication
public class ThsGroupApplication implements CommandLineRunner {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private FormationService formationService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private InscriptionService inscriptionService;

    public static void main(String[] args) {
        SpringApplication.run(ThsGroupApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	System.out.println("#############-------FINI------##########");
    }
}
