package com.travel.hotel.base.domain;

public class Constants {
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER = "Authorization";
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final int TOKEN_EXPIRE = 3600;
	public static final String DATE_FORMAT = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	
	//Error Messages
	public static final String HOTEL_CODE_MISSING = "Mandatory field missing: Hotel Code";
	public static final String HOTEL_NAME_MISSING = "Mandatory field missing: Hotel Name";
	public static final String HOTEL_ADDRESS_TYPE_MISSING = "Mandatory field missing: Hotel Address Type";
	public static final String HOTEL_ADDRESS_LINE1_MISSING = "Mandatory field missing: Hotel Address Line 1";
	public static final String HOTEL_CITY_MISSING = "Mandatory field missing: Hotel Address - City";
	public static final String HOTEL_STATE_MISSING = "Mandatory field missing: Hotel Address - State";
	public static final String HOTEL_POSTCODE_MISSING = "Mandatory field missing: Hotel Address - Postcode";
	public static final String HOTEL_COUNTRY_MISSING = "Mandatory field missing: Hotel Address - Country";
	public static final String HOTEL_CONTACT_TYPE_MISSING = "Mandatory field missing: Hotel Contact Type";
	public static final String HOTEL_CONTACT_MISSING = "Mandatory field missing: Hotel Contact Value";
	public static final String USERNAME_MISSING = "Mandatory field missing -  Username";
	public static final String PASSWORD_MISSING = "Mandatory field missing - Password";
	public static final String USER_EMAIL_MISSING = "Mandatory field missing - User Email";
	public static final String USER_MOBILE_MISSING = "Mandatory field missing - User Mobile";
	public static final String USER_MISSING = "Mandatory field missing -  Responsible User";
	
	
	public static final String HOTEL_EXISTS = "A hotel with the requested hotel code exists in the system";
	public static final String HOTEL_NOT_EXISTS = "A hotel with the requested hotel code doesn't exist in the system";
	public static final String HOTEL_ADDRESS_EXISTS = "A hotel Address with the requested hotel code and address type exists in the system";
	public static final String HOTEL_ADDRESS_NOT_EXISTS = "A hotel Address with the requested hotel code and address type doesn't exist in the system";
	public static final String HOTEL_CONTACT_EXISTS = "A hotel Contact with the requested hotel code and address type exists in the system";
	public static final String HOTEL_CONTACT_NOT_EXISTS ="A hotel Contact with the requested hotel code and address type doesn't exist in the system";
	public static final String USER_USERNAME_EXISTS ="An user with the requested username exists in the system";
	public static final String USER_USERNAME_NOT_EXISTS ="An user with the requested username doesn't exist in the system";
	public static final String USER_EMAIL_EXISTS ="An user with the requested email exists in the system";
	public static final String USER_MOBILE_EXISTS ="An user with the requested mobile exists in the system";
	
	public static final String RESOURCE_UPDATED = "The requested resource has already been updated by another user since it is been read";
	
	

}
