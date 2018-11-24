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
InternalData data = findTestData("Negavite_QM")
'Looping for N- no.of input'
for (int index =0; index<data.getRowNumbers(); index++) {
	RequestObject writeBinaryIngressor = new RequestObject('')
	'Set the Request Method for write the Binary_Ingressor'
	writeBinaryIngressor.setRestRequestMethod('POST')
	'Set the URL for write the Binary_Ingressor'
	writeBinaryIngressor.setRestUrl('http://us-west-2.api.unimatrix.io/realms/faf10fa99bf536f7c219b7c37cf7a705/binaries/'+internal.GlobalVariable.binaryId+'/ingressors?access_token=1b014b8eb4dfec8cbb137b81b42205b8')
	'Fetching the InternalData into varibale'
	def url = data.internallyGetValue("URL", index)
	'Print the inputData'
	println ("url : ${url}")
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
	println writeBinaryIngressor.setHttpHeaderProperties(parameters)
	'Set HttpBody request to respective url'
	writeBinaryIngressor.setHttpBody(JsonOutput.toJson(json).toString())
	'Get Response and stored in the variable'
	def response = WS.sendRequest(writeBinaryIngressor)
	'Print the Binary_Ingressor Response'
	println response
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 403, FailureHandling.STOP_ON_FAILURE)
	'Get the Request Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response for body content'
	println ("Response for the requestContent : ${requestContent}")
	println ("Response for the requestContent with binary_uuid : ${requestContent.errors[0].message}")	
	//internal.GlobalVariable.binaryReadId = requestContent.binaries_ingressors[0].binary_uuid
	//internal.GlobalVariable.binaryIngressId = requestContent.binaries_ingressors[0].uuid
	//println ("Globally stored Binary_uuid: ${internal.GlobalVariable.binaryReadId}")
	//println  ("Globally stored Binary_Ingressor_uuid: ${internal.GlobalVariable.binaryIngressId}")
	'Verify the Binary_Ingressor URL'
	WS.verifyElementPropertyValue(response,"errors[0].message","The binary already has a binary_ingressor.", FailureHandling.STOP_ON_FAILURE)
	Map Create_Binary_Ingressor = [:]
	}
	