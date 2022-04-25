package com.example.PAP2022.services;

import com.example.PAP2022.enums.ApplicationUserDetailsImplementation;
import com.example.PAP2022.models.Task;
import com.example.PAP2022.models.Team;
import com.example.PAP2022.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.PAP2022.models.ApplicationUser;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsServiceImplementation implements UserDetailsService {
  private final ApplicationUserRepository applicationUserRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    ApplicationUser user = applicationUserRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

    return ApplicationUserDetailsImplementation.build(user);
  }

  public ApplicationUser getApplicationUserById(Long id) {
    return applicationUserRepository.findById(id).get();
  }

  public Optional<ApplicationUser> loadApplicationUserById(Long id) {
    return applicationUserRepository.findById(id);
  }

  public List<ApplicationUser> getAllUsers(){
    return applicationUserRepository.findAll();
  }

  public List<Team> getAllTeams(Long id){
    return applicationUserRepository.getById(id).getTeams();
  }

  public List<Task> getAllTasks(Long id){
    return applicationUserRepository.getById(id).getTasks();
  }

  public Long deleteUser(Long id){
    applicationUserRepository.deleteById(id);
    return id;
  }
}
