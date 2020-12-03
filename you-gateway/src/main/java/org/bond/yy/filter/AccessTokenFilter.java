package org.bond.yy.filter;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.zuul.context.RequestContext;

public class AccessTokenFilter extends AbstractYYFilter {

	@Value("${system.config.accessTokenFilter.ignoreUrls}")
	private String[] ignoreUrls;

	@Override
	public boolean shouldFilter() {
		HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
		if(!StringUtils.equalsIgnoreCase(req.getMethod(), "post")){
			return false;
		}
 		if(null != ignoreUrls && ignoreUrls.length != 0){
			for(String url : ignoreUrls){
				if(url.contains(req.getRequestURI().toString())){
					return false;
				}
			}
		}
		return true;
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
