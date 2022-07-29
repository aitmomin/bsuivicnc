package drh.concour;

import drh.concour.entities.*;
import drh.concour.repositories.*;
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
        RoomConcourRepository roomConcourRepository = context.getBean(RoomConcourRepository.class);

        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
/*

            User p1 = new User(1169562,"abderrahim", "elbouraimi", "0607080909", "rabat", "a.elbouraimi@justice.gov.ma", "1169562",
                    encoder.encode("1234"), "Ingénieur Chef", "Grade principal", "", "Direction des Ressources Humaines", "DRH");
            p1.setResponsible(true);
            User p2 = new User(1814978,"abdellah", "ait abdielmomin", "0607080909", "kénitra", "a.aitmomin@justice.gov.ma", "1814978",
                    encoder.encode("1234"), "Commissaire Judiciaire", "2ème Grade", "", "Direction des Ressources Humaines", "DRH");
            User p3 = new User(1155986,"test", "test", "0607080909", "rabat", "a.test@justice.gov.ma", "1155986",
                    encoder.encode("1234"), "Rédacteur judiciare", "1ère Grade", "", "Direction des Etudes de la Coopération et de la Modernisation", "DECM");
            User p4 = new User(1155986,"test", "test", "0607080909", "rabat", "a.test@justice.gov.ma", "1155986",
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

            Set<Role> roles2 = new HashSet<Role>();
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


            Room r1 = new Room("s1", "Salle 1");
            Room r2 = new Room("s2", "Salle 2");
            Room r3 = new Room("s3", "Salle 3");
            Room r4 = new Room("s4", "Salle 4");
            Room r5 = new Room("s5", "Salle 5");
            Room r6 = new Room("s6", "Salle 6");
            Room r7 = new Room("a1", "AMPHI 1");
            Room r8 = new Room("a2", "AMPHI 2");
            Room r9 = new Room("a3", "AMPHI 3");
            Room r10 = new Room("a4", "AMPHI 4");
            Room r11 = new Room("a5", "AMPHI 5");
            Room r12 = new Room("a6", "AMPHI 6");
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
            System.out.println("---------------------------------FIN-----------------------------------------------");
*/



            /*CenterConcour ccn1 = new CenterConcour();
            ccn1.setConcour(concourRepository.findById(1l).get());
            ccn1.setCenter(centerRepository.findById(1l).get());
            centerConcourRepository.save(ccn1);

            CenterConcour ccn2 = new CenterConcour();
            ccn2.setConcour(concourRepository.findById(2l).get());
            ccn2.setCenter(centerRepository.findById(1l).get());
            centerConcourRepository.save(ccn2);

            CenterConcour ccn3 = new CenterConcour();
            ccn3.setConcour(concourRepository.findById(3l).get());
            ccn3.setCenter(centerRepository.findById(1l).get());
            centerConcourRepository.save(ccn3);

            CenterConcour ccn4 = new CenterConcour();
            ccn4.setConcour(concourRepository.findById(4l).get());
            ccn4.setCenter(centerRepository.findById(1l).get());
            centerConcourRepository.save(ccn4);*/


            /*RoomConcour rc1 = new RoomConcour();
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

            rc1.setConcourr(concourRepository.findById(1l).get());
            rc1.setRoom(roomRepository.findById(1l).get());

            rc2.setConcourr(concourRepository.findById(1l).get());
            rc2.setRoom(roomRepository.findById(2l).get());

            rc3.setConcourr(concourRepository.findById(1l).get());
            rc3.setRoom(roomRepository.findById(3l).get());

            rc4.setConcourr(concourRepository.findById(2l).get());
            rc4.setRoom(roomRepository.findById(4l).get());

            rc5.setConcourr(concourRepository.findById(2l).get());
            rc5.setRoom(roomRepository.findById(5l).get());

            rc6.setConcourr(concourRepository.findById(2l).get());
            rc6.setRoom(roomRepository.findById(6l).get());

            rc7.setConcourr(concourRepository.findById(3l).get());
            rc7.setRoom(roomRepository.findById(7l).get());

            rc8.setConcourr(concourRepository.findById(3l).get());
            rc8.setRoom(roomRepository.findById(8l).get());

            rc9.setConcourr(concourRepository.findById(3l).get());
            rc9.setRoom(roomRepository.findById(9l).get());

            rc10.setConcourr(concourRepository.findById(4l).get());
            rc10.setRoom(roomRepository.findById(10l).get());

            rc11.setConcourr(concourRepository.findById(4l).get());
            rc11.setRoom(roomRepository.findById(11l).get());

            rc12.setConcourr(concourRepository.findById(4l).get());
            rc12.setRoom(roomRepository.findById(12l).get());


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

            /*User u = userRepository.findUserByIdentifier("1169562");

            System.out.println("------------------------------------- Resultat -----------------------------------------");
            System.out.println("---------------------- Identifiant == "+u.getIdentifier());
            System.out.println( "\n");
            System.out.println("---------------------- Centre == "+u.getCenter().getAddress()+" - "+u.getCenter().getCity());

            System.out.println("---------------------- liste Commission == ");
            u.getCenter().getCommission().forEach(p -> {
                System.out.println("-- "+p.getIdentifier()+ " :: "+p.getRank());
            });

            System.out.println("---------------------- liste Concours == ");
            u.getCenter().getCenterConcours().forEach(cc -> {
                System.out.println("CONCOUR ::: "+cc.getConcour().getTitle());
                cc.getConcour().getRoomConcours().forEach(rc -> {
                    System.out.println("------ROOM ::: "+rc.getRoom().getName()+" -- "+rc.getRoom().getNumber());
                });
                System.out.println( "\n");
            });*/
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