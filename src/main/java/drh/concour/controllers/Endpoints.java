package drh.concour.controllers;

import drh.concour.entities.*;

import drh.concour.entities.models.ConcourWithRooms;
import drh.concour.entities.models.Feeds;
import drh.concour.message.request.LoginForm;
import drh.concour.message.response.JwtResponse;
import drh.concour.message.response.ResponseMessage;
import drh.concour.repositories.*;


import drh.concour.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class Endpoints {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    @Autowired
    private ConcourRepository concourRepository;
    @Autowired
    private RemarksRepository remarksRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private CenterConcourRepository centerConcourRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ConcourWithRoomsRepository concourWithRoomsRepository;
    @Autowired
    private FeedsRepository feedsRepository;


    // ---> USER
    @RequestMapping(value="/test/test")
    public String test(){
        return "name of center is "+centerRepository.findById(2l).get().getCity();
    }




    // ---> AUTHENTICATION LOGIN
    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> login(@RequestBody LoginForm request) {
        String jwt = "";
        UserDetails userDetails = null;
        System.out.println(request.getUsername());
        if (!userRepository.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            // User user = userRepository.findUserByUsername(request.getUsername());
            User user = userRepository.findUserByUsername(request.getUsername());
            if(encoder.matches(request.getPassword(), user.getPassword())){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                jwt = jwtProvider.generateJwtToken(authentication);
                userDetails = (UserDetails) authentication.getPrincipal();
            }else{
                return new ResponseEntity<>(new ResponseMessage("le mot de passe est incorrect !"),
                        HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }




    // ---> USER
    @RequestMapping(value="/users/identifier/{identifier}", method=RequestMethod.GET)
    public ResponseEntity<?> getUserByUsername(@PathVariable String identifier){
        User user = null;
        if (!identifier.isBlank() && !identifier.isEmpty()) {
            boolean exists = userRepository.existsByUsername(identifier);
            if (exists){
                user = userRepository.findUserByUsername(identifier);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant not valid !"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value="/users/{userID}/center", method=RequestMethod.GET)
    public ResponseEntity<?> getCenterByUser(@PathVariable long userID){
        Center center = null;
        User user = null;
        boolean existsUser = userRepository.existsById(userID);
        if (existsUser){
            user = userRepository.findById(userID).get();
            boolean existsCenter = centerRepository.existsById(user.getCenter().getId());
            if (existsCenter){
                center = centerRepository.getCenterById(user.getCenter().getId());
            } else {
                return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                        HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(center);
    }

    @PutMapping("/update/password/{identifier}")
    public ResponseEntity<?> updatePassword(@PathVariable String identifier, @RequestBody User updated){
        User user = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!identifier.isBlank() && !identifier.isEmpty()) {
            boolean exists = userRepository.existsByUsername(identifier);
            if (exists){
                user = userRepository.findUserByUsername(identifier);
                if (encoder.matches(updated.getPassword(), user.getPassword())){
                    System.out.println("PASS == "+updated.getPassword());
                    System.out.println("NEW == "+updated.getNewPassword());
                    user.setPassword(encoder.encode(updated.getNewPassword()));
                    userRepository.save(user);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("mot de passe invalide."),
                            HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(new ResponseMessage("l'identifiant n'existe pas."),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("l'identifiant n'est pas valide."),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }




    // ---> CENTER
    @RequestMapping(value="/centers/{centerID}/jury", method=RequestMethod.GET)
    public ResponseEntity<?> getJuryOfCentre(@PathVariable Long centerID){
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            List<User> users = userRepository.getUsersByCenterId(centerID);
            return ResponseEntity.ok(users);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/centers/{centerID}/concours", method=RequestMethod.GET)
    public ResponseEntity<?> getConcoursByCentre(@PathVariable Long centerID){
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            List<Concour> concours = new ArrayList<>();
            centerConcourRepository.getCenterConcoursByCenterId(centerID).forEach(cc -> {
                concours.add(cc.getConcour());
            });
            return ResponseEntity.ok(concours);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/centers/{centerID}/ready")
    public ResponseEntity<?> centerReady(@PathVariable Long centerID) {
        Center center = null;
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            center = centerRepository.findById(centerID).get();
            center.setReady(true);
            center.setStep("step2");
            Date date = new Date();
            System.out.println(date);
            center.setReadyAt(date);
            centerRepository.save(center);
            feedsRepository.save(new Feeds(center.getCity(), "Centre est Prêt", date));
            return new ResponseEntity<>(center, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/centers/{centerID}/opened")
    public ResponseEntity<?> centerOpened(@PathVariable Long centerID) {
        Center center = null;
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            center = centerRepository.findById(centerID).get();
            center.setOpened(true);
            center.setStep("step3");
            Date date = new Date();
            System.out.println(date);
            center.setOpenedAt(date);
            centerRepository.save(center);
            feedsRepository.save(new Feeds(center.getCity(), "Ouverture du centre", date));
            return new ResponseEntity<>(center, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/centers/{centerID}/closed")
    public ResponseEntity<?> centerClosed(@PathVariable Long centerID) {
        Center center = null;
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            center = centerRepository.findById(centerID).get();
            center.setClosed(true);
            center.setStep("step4");
            Date date = new Date();
            System.out.println(date);
            center.setClosedAt(date);
            centerRepository.save(center);
            feedsRepository.save(new Feeds(center.getCity(), "Fermeture du centre", date));
            return new ResponseEntity<>(center, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/centers/{centerID}/distributed")
    public ResponseEntity<?> centerDistributed(@PathVariable Long centerID) {
        Center center = null;
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            center = centerRepository.findById(centerID).get();
            center.setDistributed(true);
            center.setStep("step5");
            Date date = new Date();
            System.out.println(date);
            center.setDistributedAt(date);
            centerRepository.save(center);
            feedsRepository.save(new Feeds(center.getCity(), "Distribution des papiers", date));
            return new ResponseEntity<>(center, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/centers/{centerID}/ended")
    public ResponseEntity<?> centerEnded(@PathVariable Long centerID) {
        Center center = null;
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            center = centerRepository.findById(centerID).get();
            center.setEnd(true);
            center.setStep("");
            Date date = new Date();
            System.out.println(date);
            center.setEndAt(date);
            centerRepository.save(center);
            feedsRepository.save(new Feeds(center.getCity(), "Fin du concour", date));
            return new ResponseEntity<>(center, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/all/centers", method=RequestMethod.GET)
    public ResponseEntity<?> getAllCenters(){
        List<Center> centers = centerRepository.findAll();
        centers.forEach(center -> {
            List<User> jury = userRepository.getUsersByCenterId(center.getId());
            center.setUsers(jury);
        });
        return ResponseEntity.ok(centers);
    }

    @RequestMapping(value="/count/all/centers", method=RequestMethod.GET)
    public ResponseEntity<?> countAllCenters(){
        long numbers = centerRepository.countAllCenters();
        return ResponseEntity.ok(numbers);
    }

    @RequestMapping(value="/count/ready/centers", method=RequestMethod.GET)
    public ResponseEntity<?> countAllReadyCenters(){
        long numbers = centerRepository.countAllReadyCenters();
        return ResponseEntity.ok(numbers);
    }

    @RequestMapping(value="/count/opened/centers", method=RequestMethod.GET)
    public ResponseEntity<?> countAllOpenedCenters(){
        long numbers = centerRepository.countAllOpenedCenters();
        return ResponseEntity.ok(numbers);
    }

    @RequestMapping(value="/count/closed/centers", method=RequestMethod.GET)
    public ResponseEntity<?> countAllClosedCenters(){
        long numbers = centerRepository.countAllClosedCenters();
        return ResponseEntity.ok(numbers);
    }

    @RequestMapping(value="/count/distributed/centers", method=RequestMethod.GET)
    public ResponseEntity<?> countAllDistributedCenters(){
        long numbers = centerRepository.countAllDistributedCenters();
        return ResponseEntity.ok(numbers);
    }

    @RequestMapping(value="/count/ended/centers", method=RequestMethod.GET)
    public ResponseEntity<?> countAllEndedCenters(){
        long numbers = centerRepository.countAllEndedCenters();
        return ResponseEntity.ok(numbers);
    }

    @RequestMapping(value="/sum/all/centers", method=RequestMethod.GET)
    public ResponseEntity<?> sumOfAllCenters(){
        long statistics[] = new long[4];
        statistics[0] = centerRepository.sumCandidatesOfAllCenters();
        statistics[1] = centerRepository.sumPresenceOfAllCenters();
        statistics[2] = centerRepository.sumAbsenceOfAllCenters();
        statistics[3] = centerRepository.sumReportsOfAllCenters();
        return ResponseEntity.ok(statistics);
    }

    @RequestMapping(value="/centers/{centerID}", method=RequestMethod.GET)
    public ResponseEntity<?> getCenterById(@PathVariable long centerID){
        Center center = null;
        boolean existsCenter = centerRepository.existsById(centerID);
        if (existsCenter){
            center = centerRepository.findById(centerID).get();
            return ResponseEntity.ok(center);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant du centre est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }




    // FEED
    @RequestMapping(value="/all/feeds", method=RequestMethod.GET)
    public ResponseEntity<?> getFeeds(){
        return ResponseEntity.ok(feedsRepository.findAllSortedByfeedDate());
    }

    @PutMapping("/update/seen/feeds")
    public ResponseEntity<?> updateFeeds(){
        List<Feeds> feeds = feedsRepository.findAllSortedByfeedDate();
        feeds.forEach(f -> {
            f.setSeen(true);
            feedsRepository.save(f);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }




    // ---> CONCOUR
    @RequestMapping(value="/{centerID}/concours/{concourID}/rooms", method=RequestMethod.GET)
    public ResponseEntity<?> getRoomsByConcour(@PathVariable Long centerID, @PathVariable Long concourID){
        Concour cn = null;
        boolean exists = concourRepository.existsById(concourID);
        boolean exists2 = centerRepository.existsById(centerID);
        if (exists && exists2){
            cn = concourRepository.findById(concourID).get();
            List<Room> rooms = roomRepository.getRoomsByConcourId(cn.getId(), centerID);;
            return ResponseEntity.ok(rooms);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant du concour est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }




    // ---> ROOM
    @RequestMapping(value="/centers/{centerID}/rooms", method=RequestMethod.GET)
    public ResponseEntity<?> getRoomsByCenter(@PathVariable Long centerID){
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            List<Room> rooms = roomRepository.getRoomsByCenterId(centerID);
            return ResponseEntity.ok(rooms);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/rooms/{roomID}", method=RequestMethod.GET)
    public ResponseEntity<?> getRoomById(@PathVariable Long roomID){
        boolean exists = roomRepository.existsById(roomID);
        if (exists){
            Room room = roomRepository.findById(roomID).get();
            return ResponseEntity.ok(room);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/center/rooms/{centerID}", method=RequestMethod.GET)
    public ResponseEntity<?> getAllRoomsByCenterId(@PathVariable Long centerID){
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            return ResponseEntity.ok(concourWithRoomsRepository.AllRoomsByCenterId(centerID));
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/room/update/absence/{roomID}")
    public ResponseEntity<?> updateAbsenceOfRoom(@PathVariable Long roomID, @RequestParam("absence") String absence, @RequestParam("centerID") String centerID){
        Room room = null;
        Center center = null;
        boolean exists = roomRepository.existsById(roomID);
        boolean exists2 = centerRepository.existsById(Long.parseLong(centerID));
        if (exists2){
            if (exists){
                room = roomRepository.findById(roomID).get();
                room.setPresence(room.getCandidates() - Long.parseLong(absence));
                room.setAbsence(Long.parseLong(absence));
                room.setDone(true);
                roomRepository.save(room);

                center = centerRepository.findById(Long.parseLong(centerID)).get();
                center.setAbsence(centerRepository.sumAbsenceByCenter(Long.parseLong(centerID)));
                center.setPresence(centerRepository.sumPresenceByCenter(Long.parseLong(centerID)));
                centerRepository.save(center);
            } else {
                return new ResponseEntity<>(new ResponseMessage("l'identifiant room n'existe pas."),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("l'identifiant centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(room);
    }


}
