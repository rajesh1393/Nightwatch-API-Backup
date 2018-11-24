import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.awt.print.Printable
import javax.net.ssl.HttpsURLConnection
import org.eclipse.jetty.websocket.api.Session
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WebAPI
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import groovy.json.JsonSlurper as JsonSlurper
import groovy.json.JsonOutput as JsonOutput
import groovy.json.JsonBuilder as JsonBuilder
import com.kms.katalon.core.testdata.InternalData
import groovy.json.StreamingJsonBuilder

'Data driven appoarch used to get a data from InternalData'
InternalData data = findTestData("QuarterMaster")
'Looping for N- no.of input'
for (int index =0; index<data.getRowNumbers(); index++) {
	RequestObject writeBinaryIngressor = new RequestObject('')
	'Set the Request Method for write the Binary_Ingressor'
	writeBinaryIngressor.setRestRequestMethod('POST')
	'Set the URL for write the Binary_Ingressor'
	writeBinaryIngressor.setRestUrl('http://us-west-2.api.acceptance.unimatrix.io/realms/f53dbe250d8a05d1ca44b28b95eb5e20/binaries/'+internal.GlobalVariable.binaryId+'/ingressors?access_token=5422e9fefd05e874dfea631770b9f7bb')
	'Fetching the InternalData into varibale'
	def url = data.internallyGetValue("URL", index)
	def bodyContent ="""{
    "binary_ingressors": [ {
      
    } ]
}"""
//	 "url": "https://cdn2.hubspot.net/hubfs/208250/Blog_Images/jmeter324.png"
	'Parsing the Json object'
	def json = new JsonSlurper().parseText(bodyContent)
	'Adding the New Json object in the Json Array'
	json.binary_ingressors[0] << [url:url]
	println json.binary_ingressors[0]<< [url:url]
	parameters = new ArrayList()
	TestObjectProperty TestObjectProperty = new TestObjectProperty()
	'Adding the data into the parameter'
	parameters.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
	'Set HttpHeader properties request to respective url'
	writeBinaryIngressor.setHttpHeaderProperties(parameters)
	'Print the Http Header properties'
	println ("Print the Http Header properties: ${writeBinaryIngressor.setHttpHeaderProperties(parameters)}")
	'Set HttpBody request to respective url'
	writeBinaryIngressor.setHttpBody(JsonOutput.toJson(json).toString())
	'Get Response and stored in the variable'
	def response = WS.sendRequest(writeBinaryIngressor)
	'Print the Binary_Ingressor Response'
	println response
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	'Get the Request Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response for body content'
	println ("Response for Binary_Ingressor: ${requestContent}")
	println ("Response for Binary_Ingressor with Binary_uuid: ${requestContent.binary_ingressors[0].binary_uuid}")
	internal.GlobalVariable.binaryReadId = requestContent.binary_ingressors[0].binary_uuid
	internal.GlobalVariable.binaryIngressId = requestContent.binary_ingressors[0].uuid
	println ("Globally stored Binary UUID : ${internal.GlobalVariable.binaryReadId}")
	println ("Globally stored Binary_Ingressor UUID : ${internal.GlobalVariable.binaryIngressId}")
	'Verify the Binary_Ingressor URL'
	WS.verifyElementPropertyValue(response,"binary_ingressors[0].url",url, FailureHandling.STOP_ON_FAILURE)
	Map Create_Binary_Ingressor = [:]
}
	