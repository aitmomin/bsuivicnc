package drh.concour;

import drh.concour.entities.*;
import drh.concour.repositories.*;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication()
public class BsuivicncApplication extends SpringBootServletInitializer {

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // return super.configure(builder);
        return builder.sources(BsuivicncApplication.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(BsuivicncApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ApplicationContext context) {
        // Declaration of repositories
        UserRepository userRepository = context.getBean(UserRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);

        CenterRepository centerRepository = context.getBean(CenterRepository.class);
        ConcourRepository concourRepository = context.getBean(ConcourRepository.class);
        RoomRepository roomRepository = context.getBean(RoomRepository.class);

        CenterConcourRepository centerConcourRepository = context.getBean(CenterConcourRepository.class);

        return args -> {


            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


            // CREATION OF USERS
            User pr1 = new User(1111111,"aaaaaaaaaaaaaa", "aaaaaaaaaaaa", "0607080909", "rabat", "a.elbouraimi@justice.gov.ma", "1111111",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr1.setResponsible(true);
            User pr2 = new User(2222222,"bbbbbbbbbbbbbb", "bbbbbbbbbbbb", "0607080909", "rabat", "b@justice.gov.ma", "2222222",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr2.setResponsible(true);
            User pr3 = new User(3333333,"cccccccccccccc", "ccccccccccc", "0607080909", "rabat", "c@justice.gov.ma", "3333333",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr3.setResponsible(true);
            User pr4 = new User(4444444,"dddddddddddddd", "dddddddddd", "0607080909", "rabat", "d@justice.gov.ma", "4444444",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr4.setResponsible(true);
            User pr5 = new User(5555555,"eeeeeeeeeeeeee", "eeeeeeeeee", "0607080909", "rabat", "e@justice.gov.ma", "5555555",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr5.setResponsible(true);
            User pr6 = new User(6666666,"ffffffffffffff", "ffffffffff", "0607080909", "rabat", "f@justice.gov.ma", "6666666",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr6.setResponsible(true);
            User pr7 = new User(7777777,"ggggggggggggggg", "gggggggggg", "0607080909", "rabat", "g@justice.gov.ma", "7777777",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr7.setResponsible(true);
            User pr8 = new User(8888888,"hhhhhhhhhhhhhhh", "hhhhhhhhhh", "0607080909", "rabat", "h@justice.gov.ma", "8888888",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr8.setResponsible(true);
            User pr9 = new User(9999999,"kkkkkkkkkkkkkkk", "kkkkkkkkkkk", "0607080909", "rabat", "k@justice.gov.ma", "9999999",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr9.setResponsible(true);
            User pr10 = new User(101010,"gggggggggggggggg", "ggggggggggg", "0607080909", "rabat", "gg@justice.gov.ma", "101010",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr10.setResponsible(true);
            User pr11 = new User(202020,"mmmmmmmmmmmmmmmmm", "mmmmmmmmmmm", "0607080909", "rabat", "", "202020",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr11.setResponsible(true);
            User pr12 = new User(303030,"qqqqqqqqqqqqqqqqqqq", "qqqqqqqqqqq", "0607080909", "rabat", "m@justice.gov.ma", "303030",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr12.setResponsible(true);
            User pr13 = new User(404040,"kkkkkkkkkkkkkkk", "kkkkkkkkkkk", "0607080909", "rabat", "kk@justice.gov.ma", "404040",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            pr13.setResponsible(true);

            User p2 = new User(1814978,"xxxxxxxxxxxxxxx", "xxxxxxxxxxxx", "0607080909", "kénitra", "a.aitmomin@justice.gov.ma", "1814978",
                    encoder.encode("1234"), "Commissaire Judiciaire", "2ème Grade", "G684980", "Direction des Ressources Humaines", "DRH");
            User p3 = new User(1155986,"xxxxxxxxxxxxxxx", "xxxxxxxxxxxx", "0607080909", "rabat", "a.test@justice.gov.ma", "1155986",
                    encoder.encode("1234"), "Rédacteur judiciare", "1ère Grade", "", "Direction des Etudes de la Coopération et de la Modernisation", "DECM");
            User p4 = new User(1155987,"xxxxxxxxxxxxxxx", "xxxxxxxxxxxx", "0607080909", "rabat", "ttest@justice.gov.ma", "1155987",
                    encoder.encode("1234"), "Rédacteur judiciare", "2ère Grade", "", "Direction des Affaires Civiles", "DAC");
            User p5 = new User(1155966,"xxxxxxxxxxxxxxx", "xxxxxxxxxxxx", "0607080909", "rabat", "t.test@justice.gov.ma", "1155966",
                    encoder.encode("1234"), "Rédacteur judiciare", "2ère Grade", "", "Direction de la legislation", "DL");


            // CREATION OF CENTERS
            Center c1 = new Center("comité : 1", "", "Agadir", "Souss-Massa", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c2 = new Center("comité : 1", "", "Dakhla", "Dakhla-Oued Ed-Dahab", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c3 = new Center("comité : 1", "", "Casablanca", "Casablanca-Settat", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c4 = new Center("comité : 1", "", "Errachidia", "Drâa-Tafilalet", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c5 = new Center("comité : 1", "", "Laâyoune", "Laâyoune-Sakia El Hamra", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c6 = new Center("comité : 1", "", "Béni Mellal", "Béni Mellal-Khénifra", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c7 = new Center("comité : 1", "", "Tangier", "Tanger-Tétouan-Al Hoceïma", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c8 = new Center("comité : 1", "", "Fès", "Fès-Meknès", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c9 = new Center("comité : 1", "", "Guelmim", "Guelmim-Oued Noun", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c10 = new Center("comité : 1", "", "Marrakech", "Marrakech-Safi", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c11 = new Center("comité : 1", "", "Oujda", "Oriental", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c12 = new Center("comité : 1", "Faculté des Sciences Juridiques, Economiques et Sociales AV Mohammed Ben Abdallah Ragragui, " +
                    "Al Irfane. BP 6430, Rabat Instituts - Rabat", "Rabat", "Rabat-Salé-Kénitra", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));
            Center c13 = new Center("comité : 2", "Faculté des Sciences Juridiques, Economiques et Sociales AV Mohammed Ben Abdallah Ragragui, " +
                    "Al Irfane. BP 6430, Rabat Instituts - Rabat", "Rabat", "Rabat-Salé-Kénitra", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));


            centerRepository.save(c1);
            centerRepository.save(c2);
            centerRepository.save(c3);
            centerRepository.save(c4);
            centerRepository.save(c5);
            centerRepository.save(c6);
            centerRepository.save(c7);
            centerRepository.save(c8);
            centerRepository.save(c9);
            centerRepository.save(c10);
            centerRepository.save(c11);
            centerRepository.save(c12);
            centerRepository.save(c13);

            Concour cn1 = new Concour("Entrepreneuriat et Ingénierie Managériale", "Commissaire Judiciaire 2ème grade (échelle 11)", new Date("31/10/2021 11:00"));
            Concour cn2 = new Concour("Audit et Controle de Gestion", "Commissaire Judiciaire 2ème grade (échelle 11)", new Date("31/10/2021 11:00"));
            Concour cn3 = new Concour("Développement informatiques", "Rédacteur Judiciaire 3ème grade (échelle 9)", new Date("31/10/2021 11:00"));
            Concour cn4 = new Concour("Gros Oeuvre", "Rédacteur Judiciaire 3ème grade (échelle 9)", new Date("31/10/2021 11:00"));
            Concour cn5 = new Concour("Génie Civil", "Rédacteur Judiciaire 3ème grade (échelle 9)", new Date("31/10/2021 11:00"));
            Concour cn6 = new Concour("Electricité", "Rédacteur Judiciaire 3ème grade (échelle 9)", new Date("31/10/2021 11:00"));


            // CREATION OF ROLES
            Role ro1 = new Role("USER");
            Role ro2 = new Role("ADMIN");
            Role ro3 = new Role("SA");
            roleRepository.save(ro1);
            roleRepository.save(ro2);
            roleRepository.save(ro3);

            List<Role> roles2 = new ArrayList<>();
            roles2.add(ro1);
            pr1.setRoles(roles2);
            pr2.setRoles(roles2);
            pr3.setRoles(roles2);
            pr4.setRoles(roles2);
            pr5.setRoles(roles2);
            pr6.setRoles(roles2);
            pr7.setRoles(roles2);
            pr8.setRoles(roles2);
            pr9.setRoles(roles2);
            pr10.setRoles(roles2);
            pr11.setRoles(roles2);
            pr12.setRoles(roles2);
            pr13.setRoles(roles2);
            p2.setRoles(roles2);
            p3.setRoles(roles2);
            p4.setRoles(roles2);
            p5.setRoles(roles2);


            // AFFECTATION OF USERS/CENTERS
            pr1.setCenter(c1); p2.setCenter(c1); p3.setCenter(c1); p4.setCenter(c1); p5.setCenter(c1);
            pr2.setCenter(c2); p2.setCenter(c2); p3.setCenter(c2); p4.setCenter(c2); p5.setCenter(c2);
            pr3.setCenter(c3); p2.setCenter(c3); p3.setCenter(c3); p4.setCenter(c3); p5.setCenter(c3);
            pr4.setCenter(c4); p2.setCenter(c4); p3.setCenter(c4); p4.setCenter(c4); p5.setCenter(c4);
            pr5.setCenter(c5); p2.setCenter(c5); p3.setCenter(c5); p4.setCenter(c5); p5.setCenter(c5);
            pr6.setCenter(c6); p2.setCenter(c6); p3.setCenter(c6); p4.setCenter(c6); p5.setCenter(c6);
            pr7.setCenter(c7); p2.setCenter(c7); p3.setCenter(c7); p4.setCenter(c7); p5.setCenter(c7);
            pr8.setCenter(c8); p2.setCenter(c8); p3.setCenter(c8); p4.setCenter(c8); p5.setCenter(c8);
            pr9.setCenter(c9); p2.setCenter(c9); p3.setCenter(c9); p4.setCenter(c9); p5.setCenter(c9);
            pr10.setCenter(c10); p2.setCenter(c10); p3.setCenter(c10); p4.setCenter(c10); p5.setCenter(c10);
            pr11.setCenter(c11); p2.setCenter(c11); p3.setCenter(c11); p4.setCenter(c11); p5.setCenter(c11);
            pr12.setCenter(c12); p2.setCenter(c12); p3.setCenter(c12); p4.setCenter(c12); p5.setCenter(c12);
            pr13.setCenter(c13); p2.setCenter(c13); p3.setCenter(c13); p4.setCenter(c13); p5.setCenter(c13);


            userRepository.save(pr1);
            userRepository.save(pr2);
            userRepository.save(pr3);
            userRepository.save(pr4);
            userRepository.save(pr5);
            userRepository.save(pr6);
            userRepository.save(pr7);
            userRepository.save(pr8);
            userRepository.save(pr9);
            userRepository.save(pr10);
            userRepository.save(pr11);
            userRepository.save(pr12);
            userRepository.save(pr13);
            userRepository.save(p2);
            userRepository.save(p3);
            userRepository.save(p4);
            userRepository.save(p5);


            concourRepository.save(cn1);
            concourRepository.save(cn2);
            concourRepository.save(cn3);
            concourRepository.save(cn4);
            concourRepository.save(cn5);
            concourRepository.save(cn6);


            // AFFECTATION OF CONCOURS/CENTERS
            CenterConcour ccn1 = new CenterConcour();
            CenterConcour ccn2 = new CenterConcour();
            CenterConcour ccn3 = new CenterConcour();
            CenterConcour ccn4 = new CenterConcour();
            CenterConcour ccn5 = new CenterConcour();
            CenterConcour ccn6 = new CenterConcour();
            CenterConcour ccn7 = new CenterConcour();
            CenterConcour ccn8 = new CenterConcour();
            CenterConcour ccn9 = new CenterConcour();
            CenterConcour ccn10 = new CenterConcour();
            CenterConcour ccn11 = new CenterConcour();
            CenterConcour ccn12 = new CenterConcour();
            CenterConcour ccn13 = new CenterConcour();
            CenterConcour ccn14 = new CenterConcour();
            CenterConcour ccn15 = new CenterConcour();
            CenterConcour ccn16 = new CenterConcour();
            CenterConcour ccn17 = new CenterConcour();
            CenterConcour ccn18 = new CenterConcour();
            CenterConcour ccn19 = new CenterConcour();
            CenterConcour ccn20 = new CenterConcour();
            CenterConcour ccn21 = new CenterConcour();
            CenterConcour ccn22 = new CenterConcour();
            CenterConcour ccn23 = new CenterConcour();
            CenterConcour ccn24 = new CenterConcour();
            CenterConcour ccn25 = new CenterConcour();
            CenterConcour ccn26 = new CenterConcour();
            CenterConcour ccn27 = new CenterConcour();
            CenterConcour ccn28 = new CenterConcour();
            CenterConcour ccn29 = new CenterConcour();
            CenterConcour ccn30 = new CenterConcour();
            CenterConcour ccn31 = new CenterConcour();
            CenterConcour ccn32 = new CenterConcour();
            CenterConcour ccn33 = new CenterConcour();
            CenterConcour ccn34 = new CenterConcour();
            CenterConcour ccn35 = new CenterConcour();
            CenterConcour ccn36 = new CenterConcour();
            CenterConcour ccn37 = new CenterConcour();
            CenterConcour ccn38 = new CenterConcour();
            CenterConcour ccn39 = new CenterConcour();
            CenterConcour ccn40 = new CenterConcour();
            CenterConcour ccn41 = new CenterConcour();
            CenterConcour ccn42 = new CenterConcour();
            CenterConcour ccn43 = new CenterConcour();
            CenterConcour ccn44 = new CenterConcour();
            CenterConcour ccn45 = new CenterConcour();
            CenterConcour ccn46 = new CenterConcour();
            CenterConcour ccn47 = new CenterConcour();
            CenterConcour ccn48 = new CenterConcour();
            CenterConcour ccn49 = new CenterConcour();
            CenterConcour ccn50 = new CenterConcour();
            CenterConcour ccn51 = new CenterConcour();
            CenterConcour ccn52 = new CenterConcour();
            CenterConcour ccn53 = new CenterConcour();
            CenterConcour ccn54 = new CenterConcour();
            CenterConcour ccn55 = new CenterConcour();
            CenterConcour ccn56 = new CenterConcour();
            CenterConcour ccn57 = new CenterConcour();
            CenterConcour ccn58 = new CenterConcour();
            CenterConcour ccn59 = new CenterConcour();
            CenterConcour ccn60 = new CenterConcour();
            CenterConcour ccn61 = new CenterConcour();
            CenterConcour ccn62 = new CenterConcour();
            CenterConcour ccn63 = new CenterConcour();
            CenterConcour ccn64 = new CenterConcour();
            CenterConcour ccn65 = new CenterConcour();
            CenterConcour ccn66 = new CenterConcour();
            CenterConcour ccn67 = new CenterConcour();
            CenterConcour ccn68 = new CenterConcour();
            CenterConcour ccn69 = new CenterConcour();
            CenterConcour ccn70 = new CenterConcour();
            CenterConcour ccn71 = new CenterConcour();
            CenterConcour ccn72 = new CenterConcour();
            CenterConcour ccn73 = new CenterConcour();
            CenterConcour ccn74 = new CenterConcour();
            CenterConcour ccn75 = new CenterConcour();
            CenterConcour ccn76 = new CenterConcour();
            CenterConcour ccn77 = new CenterConcour();
            CenterConcour ccn78 = new CenterConcour();


            ccn1.setConcour(cn1);
            ccn1.setCenter(c1);
            centerConcourRepository.save(ccn1);
            ccn2.setConcour(cn2);
            ccn2.setCenter(c1);
            centerConcourRepository.save(ccn2);
            ccn3.setConcour(cn3);
            ccn3.setCenter(c1);
            centerConcourRepository.save(ccn3);
            ccn4.setConcour(cn4);
            ccn4.setCenter(c1);
            centerConcourRepository.save(ccn4);
            ccn5.setConcour(cn5);
            ccn5.setCenter(c1);
            centerConcourRepository.save(ccn5);
            ccn6.setConcour(cn6);
            ccn6.setCenter(c1);
            centerConcourRepository.save(ccn6);

            ccn7.setConcour(cn1);
            ccn7.setCenter(c2);
            centerConcourRepository.save(ccn7);
            ccn8.setConcour(cn2);
            ccn8.setCenter(c2);
            centerConcourRepository.save(ccn8);
            ccn9.setConcour(cn3);
            ccn9.setCenter(c2);
            centerConcourRepository.save(ccn9);
            ccn10.setConcour(cn4);
            ccn10.setCenter(c2);
            centerConcourRepository.save(ccn10);
            ccn11.setConcour(cn5);
            ccn11.setCenter(c2);
            centerConcourRepository.save(ccn11);
            ccn12.setConcour(cn6);
            ccn12.setCenter(c2);
            centerConcourRepository.save(ccn12);

            ccn13.setConcour(cn1);
            ccn13.setCenter(c3);
            centerConcourRepository.save(ccn13);
            ccn14.setConcour(cn2);
            ccn14.setCenter(c3);
            centerConcourRepository.save(ccn14);
            ccn15.setConcour(cn3);
            ccn15.setCenter(c3);
            centerConcourRepository.save(ccn15);
            ccn16.setConcour(cn4);
            ccn16.setCenter(c3);
            centerConcourRepository.save(ccn16);
            ccn17.setConcour(cn5);
            ccn17.setCenter(c3);
            centerConcourRepository.save(ccn17);
            ccn18.setConcour(cn6);
            ccn18.setCenter(c3);
            centerConcourRepository.save(ccn18);

            ccn19.setConcour(cn1);
            ccn19.setCenter(c4);
            centerConcourRepository.save(ccn19);
            ccn20.setConcour(cn2);
            ccn20.setCenter(c4);
            centerConcourRepository.save(ccn20);
            ccn21.setConcour(cn3);
            ccn21.setCenter(c4);
            centerConcourRepository.save(ccn21);
            ccn22.setConcour(cn4);
            ccn22.setCenter(c4);
            centerConcourRepository.save(ccn22);
            ccn23.setConcour(cn5);
            ccn23.setCenter(c4);
            centerConcourRepository.save(ccn23);
            ccn24.setConcour(cn6);
            ccn24.setCenter(c4);
            centerConcourRepository.save(ccn24);

            ccn25.setConcour(cn1);
            ccn25.setCenter(c5);
            centerConcourRepository.save(ccn25);
            ccn26.setConcour(cn2);
            ccn26.setCenter(c5);
            centerConcourRepository.save(ccn26);
            ccn27.setConcour(cn3);
            ccn27.setCenter(c5);
            centerConcourRepository.save(ccn27);
            ccn28.setConcour(cn4);
            ccn28.setCenter(c5);
            centerConcourRepository.save(ccn28);
            ccn29.setConcour(cn5);
            ccn29.setCenter(c5);
            centerConcourRepository.save(ccn29);
            ccn30.setConcour(cn6);
            ccn30.setCenter(c5);
            centerConcourRepository.save(ccn30);

            ccn31.setConcour(cn1);
            ccn31.setCenter(c6);
            centerConcourRepository.save(ccn31);
            ccn32.setConcour(cn2);
            ccn32.setCenter(c6);
            centerConcourRepository.save(ccn32);
            ccn33.setConcour(cn3);
            ccn33.setCenter(c6);
            centerConcourRepository.save(ccn33);
            ccn34.setConcour(cn4);
            ccn34.setCenter(c6);
            centerConcourRepository.save(ccn34);
            ccn35.setConcour(cn5);
            ccn35.setCenter(c6);
            centerConcourRepository.save(ccn35);
            ccn36.setConcour(cn6);
            ccn36.setCenter(c6);
            centerConcourRepository.save(ccn36);

            ccn37.setConcour(cn1);
            ccn37.setCenter(c7);
            centerConcourRepository.save(ccn37);
            ccn38.setConcour(cn2);
            ccn38.setCenter(c7);
            centerConcourRepository.save(ccn38);
            ccn39.setConcour(cn3);
            ccn39.setCenter(c7);
            centerConcourRepository.save(ccn39);
            ccn40.setConcour(cn4);
            ccn40.setCenter(c7);
            centerConcourRepository.save(ccn40);
            ccn41.setConcour(cn5);
            ccn41.setCenter(c7);
            centerConcourRepository.save(ccn41);
            ccn42.setConcour(cn6);
            ccn42.setCenter(c7);
            centerConcourRepository.save(ccn42);

            ccn43.setConcour(cn1);
            ccn43.setCenter(c8);
            centerConcourRepository.save(ccn43);
            ccn44.setConcour(cn2);
            ccn44.setCenter(c8);
            centerConcourRepository.save(ccn44);
            ccn45.setConcour(cn3);
            ccn45.setCenter(c8);
            centerConcourRepository.save(ccn45);
            ccn46.setConcour(cn4);
            ccn46.setCenter(c8);
            centerConcourRepository.save(ccn46);
            ccn47.setConcour(cn5);
            ccn47.setCenter(c8);
            centerConcourRepository.save(ccn47);
            ccn48.setConcour(cn6);
            ccn48.setCenter(c8);
            centerConcourRepository.save(ccn48);

            ccn49.setConcour(cn1);
            ccn49.setCenter(c9);
            centerConcourRepository.save(ccn49);
            ccn50.setConcour(cn2);
            ccn50.setCenter(c9);
            centerConcourRepository.save(ccn50);
            ccn51.setConcour(cn3);
            ccn51.setCenter(c9);
            centerConcourRepository.save(ccn51);
            ccn52.setConcour(cn4);
            ccn52.setCenter(c9);
            centerConcourRepository.save(ccn52);
            ccn53.setConcour(cn5);
            ccn53.setCenter(c9);
            centerConcourRepository.save(ccn53);
            ccn54.setConcour(cn6);
            ccn54.setCenter(c9);
            centerConcourRepository.save(ccn54);

            ccn55.setConcour(cn1);
            ccn55.setCenter(c10);
            centerConcourRepository.save(ccn55);
            ccn56.setConcour(cn2);
            ccn56.setCenter(c10);
            centerConcourRepository.save(ccn56);
            ccn57.setConcour(cn3);
            ccn57.setCenter(c10);
            centerConcourRepository.save(ccn57);
            ccn58.setConcour(cn4);
            ccn58.setCenter(c10);
            centerConcourRepository.save(ccn58);
            ccn59.setConcour(cn5);
            ccn59.setCenter(c10);
            centerConcourRepository.save(ccn59);
            ccn60.setConcour(cn6);
            ccn60.setCenter(c10);
            centerConcourRepository.save(ccn60);

            ccn61.setConcour(cn1);
            ccn61.setCenter(c11);
            centerConcourRepository.save(ccn61);
            ccn62.setConcour(cn2);
            ccn62.setCenter(c11);
            centerConcourRepository.save(ccn62);
            ccn63.setConcour(cn3);
            ccn63.setCenter(c11);
            centerConcourRepository.save(ccn63);
            ccn64.setConcour(cn4);
            ccn64.setCenter(c11);
            centerConcourRepository.save(ccn64);
            ccn65.setConcour(cn5);
            ccn65.setCenter(c11);
            centerConcourRepository.save(ccn65);
            ccn66.setConcour(cn6);
            ccn66.setCenter(c11);
            centerConcourRepository.save(ccn66);

            ccn67.setConcour(cn1);
            ccn67.setCenter(c12);
            centerConcourRepository.save(ccn67);
            ccn68.setConcour(cn2);
            ccn68.setCenter(c12);
            centerConcourRepository.save(ccn68);
            ccn69.setConcour(cn3);
            ccn69.setCenter(c12);
            centerConcourRepository.save(ccn69);
            ccn70.setConcour(cn4);
            ccn70.setCenter(c12);
            centerConcourRepository.save(ccn70);
            ccn71.setConcour(cn5);
            ccn71.setCenter(c12);
            centerConcourRepository.save(ccn71);
            ccn72.setConcour(cn6);
            ccn72.setCenter(c12);
            centerConcourRepository.save(ccn72);

            ccn73.setConcour(cn1);
            ccn73.setCenter(c13);
            centerConcourRepository.save(ccn73);
            ccn74.setConcour(cn2);
            ccn74.setCenter(c13);
            centerConcourRepository.save(ccn74);
            ccn75.setConcour(cn3);
            ccn75.setCenter(c13);
            centerConcourRepository.save(ccn75);
            ccn76.setConcour(cn4);
            ccn76.setCenter(c13);
            centerConcourRepository.save(ccn76);
            ccn77.setConcour(cn5);
            ccn77.setCenter(c13);
            centerConcourRepository.save(ccn77);
            ccn78.setConcour(cn6);
            ccn78.setCenter(c13);
            centerConcourRepository.save(ccn78);


            // CREATION OF ROOMS AGADIR
            Room r1 = new Room("", "Salle : 7", 40, 38,2, ccn4);
            Room r2 = new Room("", "Salle : 2", 45, 38,7, ccn3);
            Room r3 = new Room("", "Salle : 3", 46, 37,9, ccn3);
            Room r4 = new Room("", "Salle : 4", 46, 38,8, ccn3);
            Room r5 = new Room("", "Salle : 5", 50, 45,5, ccn6);
            Room r6 = new Room("", "Salle : 6", 23, 21,2, ccn5);
            Room r7 = new Room("", "Amphi : 1", 64, 57,7, ccn2);
            Room r8 = new Room("", "Amphi : 2", 64, 59, 5, ccn2);
            Room r9 = new Room("", "Amphi : 3", 65, 56, 9, ccn2);
            Room r10 = new Room("", "Amphi : 4", 46, 44, 2, ccn1);
            roomRepository.save(r1);
            roomRepository.save(r2);
            roomRepository.save(r3);
            roomRepository.save(r4);
            roomRepository.save(r5);
            roomRepository.save(r6);
            roomRepository.save(r7);
            roomRepository.save(r8);
            roomRepository.save(r9);
            roomRepository.save(r10);


            // CREATION OF ROOMS DAKHLA
            Room r11 = new Room("", "Salle : 6", 13, 11,2, ccn8);
            Room r12 = new Room("", "Salle : 7", 3, 3,0, ccn7);
            Room r13 = new Room("", "Salle : 2", 7, 7,0, ccn10);
            Room r14 = new Room("", "Salle : 4", 13, 11,2, ccn9);
            Room r15 = new Room("", "Salle : 5", 12, 8,4, ccn9);
            Room r16 = new Room("", "Salle : 3", 3, 3,0, ccn12);
            roomRepository.save(r11);
            roomRepository.save(r12);
            roomRepository.save(r13);
            roomRepository.save(r14);
            roomRepository.save(r15);
            roomRepository.save(r16);


            // CREATION OF ROOMS CASA
            Room r17 = new Room("", "Salle : 1", 30, 27,3, ccn14);
            Room r18 = new Room("", "Salle : 2", 30, 26,4, ccn14);
            Room r19 = new Room("", "Salle : 3", 30, 26,4, ccn14);
            Room r20 = new Room("", "Salle : 4", 30, 25,5, ccn14);
            Room r21 = new Room("", "Salle : 5", 30, 24,6, ccn14);
            Room r22 = new Room("", "Salle : 6", 30, 26,4, ccn14);
            Room r23 = new Room("", "Salle : 9", 30, 25,5, ccn14);
            Room r24 = new Room("", "Salle : 10", 30, 24,6, ccn14);
            Room r25 = new Room("", "Salle : 11", 30, 27,3, ccn14);
            Room r26 = new Room("", "Salle : 12", 30, 27,3, ccn14);
            Room r27 = new Room("", "Salle : 13", 27, 20,7, ccn14);
            Room r28 = new Room("", "Salle : 14", 31, 28,3, ccn13);
            Room r29 = new Room("", "Salle : 15", 31, 27,4, ccn13);
            Room r30 = new Room("", "Amphi : 1", 77, 67,10, ccn16);
            Room r31 = new Room("", "Amphi : 3", 90, 74,16, ccn15);
            Room r32 = new Room("", "Amphi : 4", 55, 41,14, ccn15);
            Room r33 = new Room("", "Amphi : 6", 95, 83,12, ccn18);
            Room r34 = new Room("", "Amphi : 7", 95, 72,23, ccn18);
            Room r35 = new Room("", "Amphi : 2", 86, 70,16, ccn17);
            roomRepository.save(r17);
            roomRepository.save(r18);
            roomRepository.save(r19);
            roomRepository.save(r20);
            roomRepository.save(r21);
            roomRepository.save(r22);
            roomRepository.save(r23);
            roomRepository.save(r24);
            roomRepository.save(r25);
            roomRepository.save(r26);
            roomRepository.save(r27);
            roomRepository.save(r28);
            roomRepository.save(r29);
            roomRepository.save(r30);
            roomRepository.save(r31);
            roomRepository.save(r32);
            roomRepository.save(r33);
            roomRepository.save(r34);
            roomRepository.save(r35);


            // CREATION OF ROOMS ERRACHIDIA
            Room r36 = new Room("", "Salle : 1", 25, 23,2, ccn20);
            Room r37 = new Room("", "Salle : 2", 8, 7,1, ccn19);
            Room r38 = new Room("", "Amphi : C", 98, 83,15, ccn22);
            Room r39 = new Room("", "Amphi : B", 78, 67,11, ccn21);
            Room r40 = new Room("", "Salle : 3", 17, 15,2, ccn24);
            Room r41 = new Room("", "Amphi : D", 77, 36,41, ccn23);
            roomRepository.save(r36);
            roomRepository.save(r37);
            roomRepository.save(r38);
            roomRepository.save(r39);
            roomRepository.save(r40);
            roomRepository.save(r41);


            // CREATION OF ROOMS LAAYOUNE
            Room r42 = new Room("", "Salle : 1", 17, 16,1, ccn26);
            Room r43 = new Room("", "Salle : 2", 17, 17,0, ccn26);
            Room r44 = new Room("", "Salle : 3", 5, 5,0, ccn25);
            Room r45 = new Room("", "Salle : 4", 18, 17,1, ccn28);
            Room r46 = new Room("", "Salle : 5", 18, 18,0, ccn28);
            Room r47 = new Room("", "Salle : 6", 18, 18,0, ccn28);
            Room r48 = new Room("", "Salle : 7", 18, 17,1, ccn28);
            Room r49 = new Room("", "Salle : 8", 18, 16,2, ccn28);
            Room r50 = new Room("", "Salle : 13", 19, 17,2, ccn27);
            Room r51 = new Room("", "Salle : 14", 19, 18,1, ccn27);
            Room r52 = new Room("", "Salle : 15", 19, 18,1, ccn27);
            Room r53 = new Room("", "Salle : 16", 19, 17,2, ccn27);
            Room r54 = new Room("", "Salle : 17", 19, 17,2, ccn27);
            Room r55 = new Room("", "Salle : 18", 19, 19,0, ccn27);
            Room r56 = new Room("", "Salle : 19", 19, 16,3, ccn27);
            Room r57 = new Room("", "Salle : 20", 19, 17,2, ccn27);
            Room r58 = new Room("", "Salle : 21", 19, 18,1, ccn27);
            Room r59 = new Room("", "Salle : 22", 19, 16,3, ccn27);
            Room r60 = new Room("", "Salle : 23", 19, 16,3, ccn27);
            Room r61 = new Room("", "Salle : 24", 16, 16,0, ccn27);
            Room r62 = new Room("", "Salle : 10", 18, 16,2, ccn30);
            Room r63 = new Room("", "Salle : 11", 18, 18,0, ccn30);
            Room r64 = new Room("", "Salle : 12", 17, 12,5, ccn30);
            Room r65 = new Room("", "Salle : 9", 10, 7,3, ccn29);

            roomRepository.save(r42);
            roomRepository.save(r43);
            roomRepository.save(r44);
            roomRepository.save(r45);
            roomRepository.save(r46);
            roomRepository.save(r47);
            roomRepository.save(r48);
            roomRepository.save(r49);
            roomRepository.save(r50);
            roomRepository.save(r51);
            roomRepository.save(r52);
            roomRepository.save(r53);
            roomRepository.save(r54);
            roomRepository.save(r55);
            roomRepository.save(r56);
            roomRepository.save(r57);
            roomRepository.save(r58);
            roomRepository.save(r59);
            roomRepository.save(r60);
            roomRepository.save(r61);
            roomRepository.save(r62);
            roomRepository.save(r63);
            roomRepository.save(r64);
            roomRepository.save(r65);


            // CREATION OF ROOMS BENI MELLAL
            Room r66 = new Room("", "Salle : C0-1", 36, 33,3, ccn32);
            Room r67 = new Room("", "Salle : C0-3", 35, 31,4, ccn32);
            Room r68 = new Room("", "Salle : C1-4", 14, 12,2, ccn31);
            Room r69 = new Room("", "Amphi : A", 29, 26,3, ccn34);
            Room r70 = new Room("", "Amphi : D", 72, 63,9, ccn33);
            Room r71 = new Room("", "Amphi : E", 74, 65,9, ccn33);
            Room r72 = new Room("", "Amphi : C", 83, 74,9, ccn36);
            Room r73 = new Room("", "Amphi : 8", 30, 26,4, ccn35);
            roomRepository.save(r66);
            roomRepository.save(r67);
            roomRepository.save(r68);
            roomRepository.save(r69);
            roomRepository.save(r70);
            roomRepository.save(r71);
            roomRepository.save(r72);
            roomRepository.save(r73);


            // CREATION OF ROOMS TANGIER
            Room r74 = new Room("", "Amphi : 2", 62, 59,3, ccn38);
            Room r75 = new Room("", "Amphi : 3", 60, 53,7, ccn38);
            Room r76 = new Room("", "Amphi : 4", 60, 50,10, ccn38);
            Room r77 = new Room("", "Salle : 3", 26, 24,2, ccn37);
            Room r78 = new Room("", "Salle : 4", 26, 26,0, ccn37);
            Room r79 = new Room("", "Amphi : 5", 100, 87,13, ccn40);
            Room r80 = new Room("", "Amphi : 6", 102, 81,21, ccn40);
            Room r81 = new Room("", "Amphi : 1", 90, 79,11, ccn39);
            Room r82 = new Room("", "Salle : 2", 27, 22,5, ccn42);
            Room r83 = new Room("", "Salle : 1", 13, 11,2, ccn41);
            roomRepository.save(r74);
            roomRepository.save(r75);
            roomRepository.save(r76);
            roomRepository.save(r77);
            roomRepository.save(r78);
            roomRepository.save(r79);
            roomRepository.save(r80);
            roomRepository.save(r81);
            roomRepository.save(r82);
            roomRepository.save(r83);


            // CREATION OF ROOMS FES
            Room r84 = new Room("", "Salle : U7", 28, 28,0, ccn44);
            Room r85 = new Room("", "Salle : U8", 28, 27,1, ccn44);
            Room r86 = new Room("", "Salle : U6", 28, 27,1, ccn44);
            Room r87 = new Room("", "Salle : U10", 28, 27,1, ccn44);
            Room r88 = new Room("", "Salle : U11", 28, 25,3, ccn44);
            Room r89 = new Room("", "Salle : U12", 28, 23,5, ccn44);
            Room r90 = new Room("", "Salle : U13", 28, 24,4, ccn44);
            Room r91 = new Room("", "Salle : U14", 28, 27,1, ccn44);
            Room r92 = new Room("", "Salle : U15", 28, 25,3, ccn44);
            Room r93 = new Room("", "Salle : U16", 28, 26,2, ccn44);
            Room r94 = new Room("", "Salle : U17", 28, 25,3, ccn44);
            Room r95 = new Room("", "Salle : U18", 28, 24,4, ccn44);
            Room r96 = new Room("", "Salle : U19", 5, 2,3, ccn44);
            Room r97 = new Room("", "Salle : U20", 30, 28,2, ccn43);
            Room r98 = new Room("", "Salle : U21", 30, 23,7, ccn43);
            Room r99 = new Room("", "Amphi : E", 100, 88,12, ccn46);
            Room r100 = new Room("", "Amphi : F", 91, 81,10, ccn46);
            Room r101 = new Room("", "Amphi : C2", 60, 51,9, ccn45);
            Room r102 = new Room("", "Amphi : D1", 60, 46,14, ccn45);
            Room r103 = new Room("", "Amphi : D2", 58, 47,11, ccn45);
            Room r104 = new Room("", "Amphi : A", 45, 36,9, ccn48);
            Room r105 = new Room("", "Amphi : B", 60, 47,13, ccn47);
            Room r106 = new Room("", "Amphi : C1", 53, 44,9, ccn47);
            roomRepository.save(r84);
            roomRepository.save(r85);
            roomRepository.save(r86);
            roomRepository.save(r87);
            roomRepository.save(r88);
            roomRepository.save(r89);
            roomRepository.save(r90);
            roomRepository.save(r91);
            roomRepository.save(r92);
            roomRepository.save(r93);
            roomRepository.save(r94);
            roomRepository.save(r95);
            roomRepository.save(r96);
            roomRepository.save(r97);
            roomRepository.save(r98);
            roomRepository.save(r99);
            roomRepository.save(r100);
            roomRepository.save(r101);
            roomRepository.save(r102);
            roomRepository.save(r103);
            roomRepository.save(r104);
            roomRepository.save(r105);
            roomRepository.save(r106);


            // CREATION OF ROOMS GUELMIM
            Room r107 = new Room("", "Salle : 1", 18, 15,3, ccn44);
            Room r108 = new Room("", "Salle : 2", 6, 6,0, ccn44);
            Room r109 = new Room("", "Amphi : 3", 15, 14,1, ccn44);
            Room r110 = new Room("", "Amphi : 1", 77, 65,12, ccn44);
            Room r111 = new Room("", "Salle : G", 18, 15,3, ccn44);
            Room r112 = new Room("", "Amphi : 2", 4, 4,0, ccn44);
            roomRepository.save(r107);
            roomRepository.save(r108);
            roomRepository.save(r109);
            roomRepository.save(r110);
            roomRepository.save(r111);
            roomRepository.save(r112);




            System.out.println("---------------------------------FIN WITH LOVE-----------------------------------------------");

        };
    }
}





            /*

                        RoomConcour rc1 = new RoomConcour();
                        RoomConcour rc2 = new RoomConcour();
                        RoomConcour rc3 = new RoomConcour();
                        RoomConcour rc4 = new RoomConcour();
                        RoomConcour rc5 = new RoomConcour();
                        RoomConcour rc6 = new RoomConcour();
                        RoomConcour rc7 = new RoomConcour();
                        RoomConcour rc8 = new RoomConcour();
                        RoomConcour rc9 = new RoomConcour();
                        RoomConcour rc10 = new RoomConcour();
                        RoomConcour rc11 = new RoomConcour();
                        RoomConcour rc12 = new RoomConcour();

                        rc1.setConcour(cn1);
                        rc1.setRoom(r1);

                        rc2.setConcour(cn1);
                        rc2.setRoom(r2);

                        rc3.setConcour(cn1);
                        rc3.setRoom(r3);

                        rc4.setConcour(cn2);
                        rc4.setRoom(r4);

                        rc5.setConcour(cn2);
                        rc5.setRoom(r5);

                        rc6.setConcour(cn2);
                        rc6.setRoom(r6);

                        rc7.setConcour(cn3);
                        rc7.setRoom(r7);

                        rc8.setConcour(cn3);
                        rc8.setRoom(r8);

                        rc9.setConcour(cn3);
                        rc9.setRoom(r9);

                        rc10.setConcour(cn4);
                        rc10.setRoom(r10);

                        rc11.setConcour(cn4);
                        rc11.setRoom(r11);

                        rc12.setConcour(cn4);
                        rc12.setRoom(r12);


                        roomConcourRepository.save(rc1);
                        roomConcourRepository.save(rc2);
                        roomConcourRepository.save(rc3);
                        roomConcourRepository.save(rc4);
                        roomConcourRepository.save(rc5);
                        roomConcourRepository.save(rc6);
                        roomConcourRepository.save(rc7);
                        roomConcourRepository.save(rc8);
                        roomConcourRepository.save(rc9);
                        roomConcourRepository.save(rc10);
                        roomConcourRepository.save(rc11);
                        roomConcourRepository.save(rc12);
            */

            /*

                        System.out.println("------------------------------------- Resultat -----------------------------------------");

                        User u = userRepository.findUserByUsername("1814978");
                        System.out.println("---------------------- Identifiant == "+u.getUsername());
                        System.out.println( "\n");


                        Center c = centerRepository.findById(u.getCenter().getId()).get();
                        System.out.println("---------------------- Centre == "+c.getAddress()+" - "+c.getCity());


                        System.out.println("---------------------- liste Commission == ");
                        userRepository.getUsersByCenterId(c.getId()).forEach(p -> {
                            System.out.println("-- "+p.getUsername()+ " :: "+p.getRank());
                        });

                        System.out.println("---------------------- liste Concours == ");
                        centerConcourRepository.getCenterConcoursByCenterId(c.getId()).forEach(cc -> {
                            System.out.println("CONCOUR ::: "+cc.getConcour().getTitle());
                            roomConcourRepository.getRoomConcoursByConcourId(cc.getConcour().getId()).forEach(rc -> {
                                System.out.println("------ROOM ::: "+rc.getRoom().getName()+" -- "+rc.getRoom().getNumber());
                            });
                            System.out.println( "\n");
                        });
            */

            /*Center c = centerRepository.findById(1l).get();
            Center cc = centerRepository.findById(2l).get();
            System.out.println("----------------------READY "+c.isReady());
            c.setReady(false);
            c.setEnd(false);
            c.setDistributed(false);
            c.setClosed(false);
            c.setOpened(false);
            c.setStep("step1");

            cc.setReady(false);
            cc.setEnd(false);
            cc.setDistributed(false);
            cc.setClosed(false);
            cc.setOpened(false);
            cc.setStep("step1");
            cc.setDateConcour(new Date("2022/07/11"));
            centerRepository.save(c);
            centerRepository.save(cc);

            User u = userRepository.findUserByUsername("112233");
            Role r = roleRepository.getRoleByRoleName("USER");
            List<Role> roles2 = new ArrayList<>();
            roles2.add(r);
            u.setRoles(roles2);
            userRepository.save(u);

            System.out.println("----------------------PEACE");*/

            /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User p1 = new User(987654,"super", "admin", "0607080909", "rabat", "sa@justice.gov.ma", "sa",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");

            Role r = roleRepository.getRoleByRoleName("SA");
            List<Role> roles2 = new ArrayList<>();
            roles2.add(r);
            p1.setRoles(roles2);
            userRepository.save(p1);*/

            /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User p1 = new User(112233,"tttttttt", "tttttttt", "0607080909", "rabat", "a.a@justice.gov.ma", "112233",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
            p1.setResponsible(true);
            User p2 = new User(445566,"ait cccccc", "ccccccccccc", "0607080909", "kénitra", "b.b@justice.gov.ma", "445566",
                    encoder.encode("1234"), "Commissaire Judiciaire", "2ème Grade", "G684980", "Direction des Ressources Humaines", "DRH");
            User p3 = new User(778899,"ddddddd", "dddddddddd", "0607080909", "rabat", "c.c@justice.gov.ma", "778899",
                    encoder.encode("1234"), "Rédacteur judiciare", "1ère Grade", "", "Direction des Etudes de la Coopération et de la Modernisation", "DECM");
            User p4 = new User(000111,"qqqqqqqq", "sssssssss", "0607080909", "rabat", "d.d@justice.gov.ma", "000111",
                    encoder.encode("1234"), "Rédacteur judiciare", "2ère Grade", "", "Direction des Affaires Civiles", "DAC");

            Center c1 = new Center("CENTRE 2", "Faculté des Sciences Juridiques, Economiques et Sociales AV Mohammed Ben Abdallah Ragragui, " +
                    "Al Irfane. BP 6430, Rabat Instituts - Casablanca", "Casablanca", "Casablanca-Settat", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));

            centerRepository.save(c1);

            p1.setCenter(c1);
            p2.setCenter(c1);
            p3.setCenter(c1);
            p4.setCenter(c1);
            userRepository.save(p1);
            userRepository.save(p2);
            userRepository.save(p3);
            userRepository.save(p4);*/

            /*Center cc = centerRepository.findById(2l).get();
            cc.setPresence(699);
            cc.setAbsence(70);
            centerRepository.save(cc);*/

    /*

    Cities c1 = new Cities("ci1", "Tangier", "Tanger-Tétouan-Al Hoceïma", 947952);
    Cities c2 = new Cities("ci2", "Oujda", "Oriental", 409391);
    Cities c3 = new Cities("ci3", "Fès", "Fès-Meknès", 1112072);
    Cities c4 = new Cities("ci4", "Rabat", "Rabat-Salé-Kénitra", 572717);
    Cities c5 = new Cities("ci5", "Errachidia", "Drâa-Tafilalet", 92374);
    Cities c6 = new Cities("ci6", "Casablanca", "Casablanca-Settat", 4370000);
    Cities c7 = new Cities("ci7", "Marrakech", "Marrakech-Safi", 928850);
    Cities c8 = new Cities("ci8", "Béni Mellal", "Béni Mellal-Khénifra", 192676);
    Cities c9 = new Cities("ci9", "Agadir", "Souss-Massa", 421844);
    Cities c10 = new Cities("ci10", "Guelmim", "Guelmim-Oued Noun", 118318);
    Cities c11 = new Cities("ci11", "Laâyoune", "Laâyoune", 217732);
    Cities c12 = new Cities("ci12", "Ad Dakhla", "Ad Dakhla", 89292);

    */