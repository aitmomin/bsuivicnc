package drh.concour.controllers;

import drh.concour.entities.*;

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
    private RoomConcourRepository roomConcourRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;


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
        boolean exists = centerRepository.existsById(userID);
        if (exists){
            center = centerRepository.findById(userID).get();
            return ResponseEntity.ok(center);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
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
            Date date = new Date();
            System.out.println(date);
            center.setReadyAt(date);
            centerRepository.save(center);
            return new ResponseEntity<>(new ResponseMessage("Centre ready!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/centers/{centerID}/opened")
    public ResponseEntity<?> centreOpened(@PathVariable Long centerID) {
        Center center = null;
        boolean exists = centerRepository.existsById(centerID);
        if (exists){
            center = centerRepository.findById(centerID).get();
            center.setOpened(true);
            Date date = new Date();
            System.out.println(date);
            center.setOpenedAt(date);
            centerRepository.save(center);
            return new ResponseEntity<>(new ResponseMessage("Centre opened!"), HttpStatus.OK);
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
            Date date = new Date();
            System.out.println(date);
            center.setClosedAt(date);
            centerRepository.save(center);
            return new ResponseEntity<>(new ResponseMessage("Centre closed!"), HttpStatus.OK);
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
            Date date = new Date();
            System.out.println(date);
            center.setDistributedAt(date);
            centerRepository.save(center);
            return new ResponseEntity<>(new ResponseMessage("Centre distributed!"), HttpStatus.OK);
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
            Date date = new Date();
            System.out.println(date);
            center.setEndAt(date);
            centerRepository.save(center);
            return new ResponseEntity<>(new ResponseMessage("Centre ended!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
    }




    // ---> CONCOUR
    @RequestMapping(value="/concours/{concourID}/rooms", method=RequestMethod.GET)
    public ResponseEntity<?> getRoomsByConcour(@PathVariable Long concourID){
        boolean exists = roomRepository.existsById(concourID);
        if (exists){
            List<Room> rooms = new ArrayList<>();
            roomConcourRepository.getRoomConcoursByConcourId(concourID).forEach(cc -> {
                rooms.add(cc.getRoom());
            });
            return ResponseEntity.ok(rooms);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
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
}
