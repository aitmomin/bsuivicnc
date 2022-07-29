package drh.concour.controllers;

import drh.concour.entities.*;
import drh.concour.message.request.LoginForm;
import drh.concour.message.response.JwtResponse;
import drh.concour.message.response.ResponseMessage;
import drh.concour.repositories.*;
import drh.concour.security.jwt.JwtProvider;
import org.hibernate.Hibernate;
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

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
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


    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginForm request) {
        String jwt = "";
        UserDetails userDetails = null;

        if (!userRepository.existsByIdentifier(request.getIdentifier())) {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> l'identifiant est incorrect !"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByIdentifier(request.getIdentifier())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = userRepository.findUserByIdentifier(request.getIdentifier());
            if(encoder.matches(request.getMotDePasse(), user.getPassword())){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getMotDePasse()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                jwt = jwtProvider.generateJwtToken(authentication);
                userDetails = (UserDetails) authentication.getPrincipal();
            }else{
                return new ResponseEntity<>(new ResponseMessage("Erreur -> le mot de passe est incorrect !"),
                        HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @RequestMapping(value="/test/test", method=RequestMethod.GET)
    public String TEST(){
        // return personneRepository.getById(1l);
        User u = userRepository.findUserByIdentifier("1814978");
        return u.getCenter().getAddress();
    }

    @RequestMapping(value="/personne/{id}", method=RequestMethod.GET)
    public User getPersonneById(@PathVariable Long id){
        return userRepository.getById(id);
    }

    @RequestMapping(value="/personne/identifiant/{identifiant}", method=RequestMethod.GET)
    public User getPersonneByIdentifiant(@PathVariable String identifiant){
        return userRepository.findUserByIdentifier(identifiant);
    }

    @RequestMapping(value="/centre/personne/{id}", method=RequestMethod.GET)
    public Center getCentreByPersonne(@PathVariable Long id){
        // return centreRepository.getCentreById(id);
        return centerRepository.getCentreByPersonne(id);
    }

    @RequestMapping(value="/centre/jury/{id}", method=RequestMethod.GET)
    public List<User> getJuryOfCentre(@PathVariable Long id){
        return userRepository.getUsersByCenter(id);
    }

    @RequestMapping(value="/centre/concours/{id}", method=RequestMethod.GET)
    public List<Concour> getConcoursByCentre(@PathVariable Long id){
        return concourRepository.findConcoursByCenterConcours(id);
    }

    @RequestMapping(value="/centre/salles/{id}", method=RequestMethod.GET)
    public List<Room> getSallesByCentre(@PathVariable Long id){
        return roomRepository.getRoomsByRoomConcours(id);
    }

    @PostMapping("/add/new/remarque")
    public void addRemarque(@RequestParam("description") String description, @RequestParam("dateRemarque") Date dateRemarque, @RequestParam("personne") long p,
                            @RequestParam("concour") long c) {
        Remark remark = new Remark(description, dateRemarque);
        remark.setConcour(concourRepository.getById(c));
        remark.setUser(userRepository.getById(p));
        remarksRepository.save(remark);
    }

    @PutMapping("/centre/opened/{id}")
    public ResponseEntity<?> openCentre(@PathVariable Long id) {
        Center c = centerRepository.getCenterById(id);
        if (!centerRepository.existsById(id)) {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
        c.setOpened(true);
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now));
        Date date = new Date();
        c.setOpenedAt(date);
        centerRepository.save(c);
        return new ResponseEntity<>(new ResponseMessage("Centre opened!"), HttpStatus.OK);
    }

    @PutMapping("/centre/closed/{id}")
    public ResponseEntity<?> closeCentre(@PathVariable Long id) {
        Center c = centerRepository.getCenterById(id);
        if (!centerRepository.existsById(id)) {
            return new ResponseEntity<>(new ResponseMessage("Erreur -> le centre n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }
        c.setClosed(true);
        Date date = new Date();
        c.setClosedAt(date);
        centerRepository.save(c);
        return new ResponseEntity<>(new ResponseMessage("Centre closed!"), HttpStatus.OK);
    }

}
