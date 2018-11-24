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
import org.apache.commons.lang.RandomStringUtils

'Data driven appoarch used to get a data from InternalData'
InternalData data = findTestData("Distributions")
'Looping for N- no.of input'
for (int index =0; index<data.getRowNumbers(); index++) {
	RequestObject writeArtifact = new RequestObject('')
	'Set the Request Method for write the Artifacts'
	writeArtifact.setRestRequestMethod('POST')
	'Set the URL for write the Artifacts'
	writeArtifact.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/a5784c49027366bd728b3c24e6bf55c3/artifacts?access_token=3ae7b712deabdfbf1fc9bcf7e95ba225')
	'Fetching the InternalData into varibale'
	def inputData = data.internallyGetValue("Name", index)
	int randomStringLength = 5
	String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
	def randomInput = inputData+RandomStringUtils.random(randomStringLength, charset.toCharArray())
	
	'Print the inputData'
	println randomInput
	def bodyContent ="""{
		    "artifacts": [{            
		            "description":"Bedrocket Artifact Description for testing", 
		            "type_name": "tag_artifact",
		            "provider_uid": "3453343224363343543",
		            "provider": "iStreamPlanet",    
			}]}"""
	'Parsing the Json object'
	def json = new JsonSlurper().parseText(bodyContent)
	'Adding the New Json object in the Json Array'
	json.artifacts[0] << [name:randomInput]
	println json.artifacts[0] << [name:randomInput]
	parameters = new ArrayList()	
	TestObjectProperty TestObjectProperty = new TestObjectProperty()
	'Adding the data into the parameter'
	parameters.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
	'Set HttpHeader properties request to respective url'
	writeArtifact.setHttpHeaderProperties(parameters)
	'Print the Http Header properties'
	println writeArtifact.setHttpHeaderProperties(parameters)
	'Set HttpBody request to respective url'
	writeArtifact.setHttpBody(JsonOutput.toJson(json).toString())
	'Get Response and stored in the variable'
	def response = WS.sendRequest(writeArtifact)
	'Print the Artifact Response'
	println response
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	'Get the Request Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response for body content'
	println requestContent
	println requestContent.artifacts[0].name
	println randomInput
	internal.GlobalVariable.inputData = randomInput
	println internal.GlobalVariable.inputData = randomInput
	'Verify the Artifacts Name'
	WS.verifyElementPropertyValue(response,"artifacts[0].name",randomInput, FailureHandling.STOP_ON_FAILURE)
	Map createArtifact = [:]
	}
	