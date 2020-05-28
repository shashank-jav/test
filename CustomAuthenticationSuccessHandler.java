package com.dgh.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.dgh.model.SingleSignonUserModel;
import com.dgh.service.MainService;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler//,AuthenticationFailureHandler {
	
{
	protected Log logger = LogFactory.getLog(this.getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	@Autowired
	MainService mainService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		setUserDetailInSession(request, authentication);
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}
	/*@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("Authentication Failed. Error message will be set!");
		request.getSession().setAttribute("error", "Invalid Credentials!!");
	}*/
	private void setUserDetailInSession(HttpServletRequest request, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		System.out.println("username:: "+user.getUsername());
		SingleSignonUserModel userModel = mainService.getLoggedInUserDetails(user.getUsername());
		System.out.println("### "+userModel);
		request.getSession().setAttribute("UserDetail", userModel);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		System.out.println("authorities: "+authorities);
		for (GrantedAuthority grantedAuthority : authorities) {
			 if ((grantedAuthority.getAuthority().equals("ROLE_USER"))) {
				return "/user/dashboard";
			 }
			 if ((grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
					return "/admin/dashboardadmin";
			}else {
				throw new IllegalStateException();
			}
		}
		return null;
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
