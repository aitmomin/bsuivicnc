package drh.concour.security.services;

import drh.concour.entities.User;
import drh.concour.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		User p = userRepository.getUserByUsername(id).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> id : " + id));

		return UserPrinciple.build(p);
	}
}