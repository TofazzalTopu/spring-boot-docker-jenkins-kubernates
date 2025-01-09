package com.info.demo.config.security;

import com.info.demo.config.ApiRequestHolder;
import com.info.demo.config.HTTPHelper;
import com.info.demo.config.MCbsApiChannel;
import com.info.demo.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {

	private MCbsApiChannel localReq = null;
	
	@Autowired
	private CommonRepository commonRepository;
	
	@Value("${api_channel}")
	private String channel;
	
	@Value("${api_channel_user}")
	private String user;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String requstFromIp = HTTPHelper.getRealClientIpAddr(req);
			if (localReq == null) {
				localReq = commonRepository.getCBSLocalUser(channel, user);
			}
			if (localReq != null && localReq.getIpAddress().contains(requstFromIp)) {
				ApiRequestHolder.setToken(true);
			} else {
				ApiRequestHolder.setToken(false);
			}
			chain.doFilter(request, response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ApiRequestHolder.removeToken();
		}

	}

}
