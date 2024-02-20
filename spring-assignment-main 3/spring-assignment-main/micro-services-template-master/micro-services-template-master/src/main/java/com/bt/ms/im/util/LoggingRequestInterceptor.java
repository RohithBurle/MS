package com.bt.ms.im.util;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.bt.ms.im.bptm.BptmContextHolder;
import com.bt.util.logging.E2ETransaction;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Logging interceptor for capturing incoming and outgoing requests and responses.
 * 
 * @author Suman Mandal
 */
@Component
public class LoggingRequestInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    LogUtil logUtil;

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String e2edata = null;
        if (url.contains("/v2")) {

            StringBuilder sb = new StringBuilder();
            sb.append("2981 uri = ");
            sb.append(request.getRequestURI());
            sb.append("?");
            sb.append(request.getQueryString());
            sb.append(" Method= ");
            sb.append(request.getMethod());

            sb.append(" RequestHeaders=");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                sb.append(headerName);
                sb.append("=");
                sb.append(request.getHeader(headerName));
                sb.append(" ");
                if (AppConstantsUtil.E2EDATA_HEADER.equalsIgnoreCase(headerName)) {
                    e2edata = request.getHeader(headerName);
                }
            }

            sb.append("payload=");
            sb.append(getPayload(request));
            String[] urlArr = url.split("/bt-consumer");
            String serviceName = url;
            if (urlArr.length > 1) {
                String[] splitArr = urlArr[1].split("/");
                serviceName = splitArr[1]; 
            }

            if (e2edata != null) {
                BptmContextHolder.createContext(serviceName + "-" + request.getMethod(), appName, e2edata);
            } else {
                BptmContextHolder.createContext(serviceName + "-" + request.getMethod(), appName);
            }

            E2ETransaction e2eTxn = BptmContextHolder.getE2ETxn();
            e2eTxn.startInboundCall();
            String finalLog = logUtil.maskLog(sb.toString());
            e2eTxn.logMessage("2981", finalLog);
            log.info(finalLog);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String url = request.getRequestURI();
        if (url.contains("/wifi-eligibility")) {
            E2ETransaction e2eTxn = BptmContextHolder.getE2ETxn();
            response.addHeader(AppConstantsUtil.E2EDATA_HEADER, e2eTxn.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("2984  Status Code :: ");
            sb.append(response.getStatus());
            sb.append(System.lineSeparator());
            sb.append(" ResponseHeaders :: ");
            for (String headerName : response.getHeaderNames()) {
                sb.append(headerName);
                sb.append("=");
                sb.append(response.getHeader(headerName));
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
            String payLoad = getPayload(response);
            if (payLoad != null) {
                sb.append(" Body :: ");
                sb.append(payLoad);
            }

            String finalLog = logUtil.maskLog(sb.toString());
            e2eTxn.logMessage("2984", finalLog);
            log.info(finalLog);
            e2eTxn.endInboundCall();
            BptmContextHolder.clearContext();
        }
    }

    private String getPayload(final HttpServletResponse response) {
        String responseBody = null;
        if (response instanceof ContentCachingResponseWrapper) {
            final ContentCachingResponseWrapper res = (ContentCachingResponseWrapper) response;
            try {
                responseBody = IOUtils.toString(res.getContentInputStream(), response.getCharacterEncoding());
                res.copyBodyToResponse();
            } catch (IOException e) {
                log.error("Exception caught in LoggingRequestInterceptor: " + e.getMessage());
            }
        }
        return responseBody;
    }

    private String getPayload(final HttpServletRequest request) throws IOException {
        return IOUtils.toString(request.getReader());
    }
}
