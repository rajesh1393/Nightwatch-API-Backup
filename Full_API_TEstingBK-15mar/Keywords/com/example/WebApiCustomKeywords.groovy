package com.example

import groovy.json.JsonSlurper

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType;
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty

public class WebApiCustomKeywords {

	/**
	 * Create Header basic authentication property,
	 * this property value is Base64 encoded token from user name and password 
	 * @param usernameAdmin user name
	 * @param password password password
	 * @return Katalon object property, an instance of TestObjectProperty 
	 */
	@Keyword
	def TestObjectProperty createBasicAuthProperty(String usernameAdmin, String password) {
		String authorizationValue =  usernameAdmin + ":" + password
		authorizationValue = "Basic "  +  authorizationValue.bytes.encodeBase64().toString()
		TestObjectProperty propertyCookie = new TestObjectProperty("Authorization",
			ConditionType.EQUALS, authorizationValue, true)
		return propertyCookie
	}
	
	/**
	 * Create HTTP body content in json format to update existing user
	 * @param password Account password
	 * @param emailAddress
	 * @param displayName A friendly user name
	 * @param notification Any comment
	 * @return Json string generated from inputed data
	 */
	@Keyword
	def String updateHttpBody(String password, String emailAddress, String displayName, String notification)
	{
		String httpBodyTemplate = '{"password" : "%s", "emailAddress" : "%s", "displayName": "%s", "notification" : "%s"}';
		String httpBody = String.format(httpBodyTemplate, password,emailAddress,displayName,notification)
		return httpBody;
	}
	
	/**
	 * Create HTTP body content in json format to create new user 
	 * @param username Account name
	 * @param password Account password
	 * @param emailAddress 
	 * @param displayName A friendly user name
	 * @param notification Any comment
	 * @return Json string generated from inputed data
	 */
	@Keyword
	def String newHttpBody(String username, String password, String emailAddress, String displayName, String notification)
	{
		String httpBodyTemplate = '{"name" : "%s", "password" : "%s", "emailAddress" : "%s", "displayName": "%s", "notification" : "%s"}';
		String httpBody = String.format(httpBodyTemplate, username,password,emailAddress,displayName,notification)
		return httpBody;
	}
}
