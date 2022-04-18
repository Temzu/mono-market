package com.temzu.monomarket.dao.impl;

import com.temzu.monomarket.dao.RoleDao;
import com.temzu.monomarket.dao.UserDao;
import com.temzu.monomarket.models.Role;
import com.temzu.monomarket.models.User;
import com.temzu.monomarket.dao.repositories.UserRepository;
import com.temzu.monomarket.exceptions.ResourceAlreadyExistsException;
import com.temzu.monomarket.exceptions.ResourceNotFoundException;
import com.temzu.monomarket.exceptions.UserLoginOrPasswordException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

  private final UserRepository userRepository;
  private final RoleDao roleDao;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User save(User user) {
    String login = user.getLogin();

    if (userRepository.existsByLogin(login)) {
      throw ResourceAlreadyExistsException.byLogin(login, User.class);
    }

    Role role = roleDao.findByName("ROLE_USER");
    user.setRoles(List.of(role));
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User findByLogin(String login) {
    return userRepository
        .findByLogin(login)
        .orElseThrow(() -> ResourceNotFoundException.byLogin(login, User.class));
  }

  @Override
  public User findByLoginAndPassword(String login, String password) {
    return Optional.of(findByLogin(login))
        .filter(user -> passwordEncoder.matches(password, user.getPassword()))
        .orElseThrow(UserLoginOrPasswordException::new);
  }
}
