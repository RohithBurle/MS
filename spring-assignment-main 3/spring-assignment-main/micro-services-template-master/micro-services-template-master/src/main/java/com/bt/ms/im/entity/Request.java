package com.bt.ms.im.entity;

public class Request {
    private String APIGW_Tracking_Header;
    private String X_Profile_Guide;
    private String X_Consumer_Digital_Id_Ref;

    public String getAPIGW_Tracking_Header() {
		return APIGW_Tracking_Header;
	}

	public String getX_Profile_Guide() {
		return X_Profile_Guide;
	}

	public String getX_Consumer_Digital_Id_Ref() {
		return X_Consumer_Digital_Id_Ref;
	}

    public void setAPIGW_Tracking_Header(String APIGW_Tracking_Header) {
        this.APIGW_Tracking_Header = APIGW_Tracking_Header;
    }

    public void setX_Profile_Guide(String X_Profile_Guide) {
        this.X_Profile_Guide = X_Profile_Guide;
    }

    public void setX_Consumer_Digital_Id_Ref(String X_Consumer_Digital_Id_Ref) {
        this.X_Consumer_Digital_Id_Ref = X_Consumer_Digital_Id_Ref;
    }
}

