package org.bond.yy.filter;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.zuul.context.RequestContext;

public class AccessTokenFilter extends AbstractYYFilter {

	@Value("${system.config.accessTokenFilter.ignoreUrls}")
	private Set<String> ignoreUrls;

	@Override
	public boolean shouldFilter() {
		HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
		log.info("ignore url :" + req.getRequestURI().toString());
		return StringUtils.equalsIgnoreCase(req.getMethod(), "post")
				&& !ignoreUrls.contains(req.getRequestURI().toString());
	}

	@Override
	public Object run() {
		return validToken();
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
