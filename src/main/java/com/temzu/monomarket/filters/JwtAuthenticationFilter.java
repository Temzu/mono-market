package com.temzu.monomarket.filters;

import com.temzu.monomarket.models.UserInfo;
import com.temzu.monomarket.services.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  @SneakyThrows
  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain) {
    String authorizationHeader = httpServletRequest.getHeader("Authorization");

    if (authorizationHeaderIsInvalid(authorizationHeader)) {
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      return;
    }

    UsernamePasswordAuthenticationToken authenticationToken = createToken(authorizationHeader);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
    return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
  }

  private UsernamePasswordAuthenticationToken createToken(String authorizationHeader)
      throws ExpiredJwtException {
    String token = authorizationHeader.replace("Bearer ", "");

    UserInfo userInfo = tokenService.parseToken(token);

    List<GrantedAuthority> authorities = new ArrayList<>();

    if (userInfo.getRoles() != null && !userInfo.getRoles().isEmpty()) {
      authorities.addAll(
          userInfo.getRoles().stream()
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList()));
    }

    return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
  }
}
