package drh.concour;

import drh.concour.entities.*;
import drh.concour.repositories.*;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication()
public class BsuivicncApplication {

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

            /*

                        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                        User p1 = new User(1169562,"elbouraimi", "abderrahim", "0607080909", "rabat", "a.elbouraimi@justice.gov.ma", "1169562",
                                encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");
                        p1.setResponsible(true);
                        User p2 = new User(1814978,"ait abdielmomin", "abdellah", "0607080909", "kénitra", "a.aitmomin@justice.gov.ma", "1814978",
                                encoder.encode("1234"), "Commissaire Judiciaire", "2ème Grade", "G684980", "Direction des Ressources Humaines", "DRH");
                        User p3 = new User(1155986,"test", "test", "0607080909", "rabat", "a.test@justice.gov.ma", "1155986",
                                encoder.encode("1234"), "Rédacteur judiciare", "1ère Grade", "", "Direction des Etudes de la Coopération et de la Modernisation", "DECM");
                        User p4 = new User(1155987,"test", "test", "0607080909", "rabat", "t.test@justice.gov.ma", "1155987",
                                encoder.encode("1234"), "Rédacteur judiciare", "2ère Grade", "", "Direction des Affaires Civiles", "DAC");

                        Center c1 = new Center("CENTRE 1", "Faculté des Sciences Juridiques, Economiques et Sociales AV Mohammed Ben Abdallah Ragragui, " +
                                "Al Irfane. BP 6430, Rabat Instituts - Rabat", "rabat", "Rabat-Salé-Kénitra", new Date("31/10/2021 08:15"), new Date("31/10/2021 09:00"));

                        centerRepository.save(c1);

                        Concour cn1 = new Concour("Entrepreneuriat et Ingénierie Managériale", "Commissaire Judiciaire 2ème grade", new Date("31/10/2021 11:00"));
                        Concour cn2 = new Concour("Audit et Controle de Gestion", "Commissaire Judiciaire 2ème grade", new Date("31/10/2021 11:00"));
                        Concour cn3 = new Concour("Développement informatiques", "Rédacteur Judiciaire 3ème grade", new Date("31/10/2021 11:00"));
                        Concour cn4 = new Concour("Gros Oeuvre", "Rédacteur Judiciaire 3ème grade", new Date("31/10/2021 11:00"));


                        Role ro1 = new Role("USER");
                        Role ro2 = new Role("ADMIN");
                        Role ro3 = new Role("SA");
                        roleRepository.save(ro1);
                        roleRepository.save(ro2);
                        roleRepository.save(ro3);

                        List<Role> roles2 = new ArrayList<>();
                        roles2.add(ro1);
                        p1.setRoles(roles2);
                        p2.setRoles(roles2);
                        p3.setRoles(roles2);
                        p4.setRoles(roles2);

                        p1.setCenter(c1);
                        p2.setCenter(c1);
                        p3.setCenter(c1);
                        p4.setCenter(c1);


                        userRepository.save(p1);
                        userRepository.save(p2);
                        userRepository.save(p3);
                        userRepository.save(p4);

                        concourRepository.save(cn1);
                        concourRepository.save(cn2);
                        concourRepository.save(cn3);
                        concourRepository.save(cn4);


                        CenterConcour ccn1 = new CenterConcour();
                        ccn1.setConcour(cn1);
                        ccn1.setCenter(c1);
                        centerConcourRepository.save(ccn1);

                        CenterConcour ccn2 = new CenterConcour();
                        ccn2.setConcour(cn2);
                        ccn2.setCenter(c1);
                        centerConcourRepository.save(ccn2);

                        CenterConcour ccn3 = new CenterConcour();
                        ccn3.setConcour(cn3);
                        ccn3.setCenter(c1);
                        centerConcourRepository.save(ccn3);

                        CenterConcour ccn4 = new CenterConcour();
                        ccn4.setConcour(cn4);
                        ccn4.setCenter(c1);
                        centerConcourRepository.save(ccn4);



                        Room r1 = new Room("s1", "Salle 1", 700);
                        Room r2 = new Room("s2", "Salle 2", 800);
                        Room r3 = new Room("s3", "Salle 3", 900);
                        Room r4 = new Room("s4", "Salle 4", 700);
                        Room r5 = new Room("s5", "Salle 5", 800);
                        Room r6 = new Room("s6", "Salle 6", 900);
                        Room r7 = new Room("a1", "AMPHI 1", 700);
                        Room r8 = new Room("a2", "AMPHI 2", 800);
                        Room r9 = new Room("a3", "AMPHI 3", 900);
                        Room r10 = new Room("a4", "AMPHI 4", 700);
                        Room r11 = new Room("a5", "AMPHI 5", 800);
                        Room r12 = new Room("a6", "AMPHI 6", 900);

                        r1.setConcour(cn1);
                        r2.setConcour(cn1);
                        r3.setConcour(cn1);
                        r4.setConcour(cn2);
                        r5.setConcour(cn2);
                        r6.setConcour(cn2);
                        r7.setConcour(cn3);
                        r8.setConcour(cn3);
                        r9.setConcour(cn3);
                        r10.setConcour(cn4);
                        r11.setConcour(cn4);
                        r12.setConcour(cn4);

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
                        roomRepository.save(r11);
                        roomRepository.save(r12);


            */

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

            User p1 = new User(12345,"dashboard", "admin", "0607080909", "rabat", "admin@justice.gov.ma", "admin",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "A998877", "Direction des Ressources Humaines", "DRH");

            Role r = roleRepository.getRoleByRoleName("ADMIN");
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
            System.out.println("---------------------------------FIN WITH LOVE-----------------------------------------------");

        };
    }
}



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