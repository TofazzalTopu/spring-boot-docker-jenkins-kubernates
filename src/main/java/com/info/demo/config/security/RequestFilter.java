package com.info.demo.config.security;

import com.info.demo.config.ApiRequestHolder;
import com.info.demo.config.HTTPHelper;
import com.info.demo.config.MCbsApiChannel;
import com.info.demo.repository.CommonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class RequestFilter implements Filter {

	private MCbsApiChannel localReq = null;
	
	private final CommonRepository commonRepository;
	
	@Value("${api_channel}")
	private String channel;
	
	@Value("${api_channel_user}")
	private String user;

	public RequestFilter(CommonRepository commonRepository) {
		this.commonRepository = commonRepository;
	}

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
			log.error("Error in RequestFilter: Error = " + ex.getMessage());
		} finally {
			ApiRequestHolder.removeToken();
		}

	}

}
