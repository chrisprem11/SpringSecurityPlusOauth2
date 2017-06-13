package test.demo.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


	private RequestCache requestCache = new HttpSessionRequestCache();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
			System.out.println(auths.toArray()[0].toString());
			if (auths.toArray()[0].toString().equals("ROLE_ADMIN")) {
				String targetUrl = "/admin/adminHome";
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
			} else if (auths.toArray()[0].toString().equals("ROLE_USER")) {
				String targetUrl = "/user/userHome";
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
			} 
			} else {
				String targetUrl = savedRequest.getRedirectUrl();
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}
	}
}
