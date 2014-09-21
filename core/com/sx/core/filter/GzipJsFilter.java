/**
 * 
 */
package com.sx.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * 
 */
public class GzipJsFilter implements Filter {

	@SuppressWarnings("unchecked")
	private Map headers;

	@SuppressWarnings("unchecked")
	public GzipJsFilter() {
		headers = new HashMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (req instanceof HttpServletRequest) {
			doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		java.util.Map.Entry entry;
		for (Iterator it = headers.entrySet().iterator(); it.hasNext(); response
				.addHeader((String) entry.getKey(), (String) entry.getValue())) {
			entry = (java.util.Map.Entry) it.next();
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		String headersStr = config.getInitParameter("headers");
		String headers[] = headersStr.split(",");
		for (int i = 0; i < headers.length; i++) {
			String temp[] = headers[i].split("=");
			this.headers.put(temp[0].trim(), temp[1].trim());
		}

	}

}
