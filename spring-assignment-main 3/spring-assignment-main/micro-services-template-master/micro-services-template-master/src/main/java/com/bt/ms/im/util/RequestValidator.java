package com.bt.ms.im.util;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
import com.bt.ms.im.entity.Request;
 
import com.bt.ms.im.exception.StandardError;
import com.bt.ms.im.exception.handler.standardexception.BadRequestException;
 
@Component
public class RequestValidator {
 
    private Logger logger = LoggerFactory.getLogger(RequestValidator.class);
    private static final String TRACKING_HEADER_REGEX = "^[\\w.~-]{1,255}$";
    private static final String UUID_REGEX = "^[a-zA-Z0-9-]{1,50}$";
    public void validateGetWifiRequest(Request request) {
        String APIGW_Tracking_Header = request.getAPIGW_Tracking_Header();
        String X_Profile_Guide = request.getX_Profile_Guide();
 
        if (APIGW_Tracking_Header == null ) {
            StandardError errormsg = StandardError.ERR400_25; 
            logger.error(errormsg.getMessage());
            throw new BadRequestException(errormsg);
        }

        if (!isValidTrackingHeader(APIGW_Tracking_Header)) {
            StandardError errormsg = StandardError.ERR400_26;
            logger.error(errormsg.getMessage());
            throw new BadRequestException(errormsg);
        }
 
        if (X_Profile_Guide != null && !X_Profile_Guide.isEmpty() && !isValidUUID(X_Profile_Guide)) {
            StandardError errormsg = StandardError.ERR400_26;
            logger.error(errormsg.getMessage());
            throw new BadRequestException(errormsg);
        }
    }
 
    private boolean isValidTrackingHeader(String trackingHeader) {
        Pattern pattern = Pattern.compile(TRACKING_HEADER_REGEX);
        Matcher matcher = pattern.matcher(trackingHeader);
        return matcher.matches();
    }
 
    private boolean isValidUUID(String X_Profile_Guide) {
        Pattern pattern = Pattern.compile(UUID_REGEX);
        Matcher matcher = pattern.matcher(X_Profile_Guide);
        return matcher.matches();
    }
}