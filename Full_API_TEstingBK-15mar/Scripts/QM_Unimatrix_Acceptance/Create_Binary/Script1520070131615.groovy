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
InternalData qmData = findTestData("QuarterMaster")
'Looping for N- no.of input'
for (int index =0; index<qmData.getRowNumbers(); index++) {
	RequestObject writeBinary = new RequestObject('')
	'Set the Request Method for write the Binary'
	writeBinary.setRestRequestMethod('POST')
	'Set the URL for write the Binary'
	writeBinary.setRestUrl('http://us-west-2.api.acceptance.unimatrix.io/realms/f53dbe250d8a05d1ca44b28b95eb5e20/binaries?access_token=5422e9fefd05e874dfea631770b9f7bb')
	'Fetching the InternalData into varibale'
	def inputData = qmData.internallyGetValue("fileName", index)	
	def format = qmData.internallyGetValue("format", index)
	int randomStringLength = 4
	'Generate Random string'
	String charset = (('a'..'z') + ('0'..'9')).join()
	'Concat the InputData with Random string'
	def randomInput = inputData+RandomStringUtils.random(randomStringLength, charset.toCharArray())
	randomInput = randomInput+format	
	println ("Print the inputData:${randomInput}")
	def bodyContent ="""{
    						"binaries": [ {
       						
    									} ]
						}"""
//	"filename": "QM_Testing.pps"
	'Parsing the Json object'
	def json = new JsonSlurper().parseText(bodyContent)
	'Adding the New Json object in the Json Array'
	json.binaries[0] << [filename:randomInput]
	println json.binaries[0]<< [name:randomInput]
	parameters = new ArrayList()
	TestObjectProperty TestObjectProperty = new TestObjectProperty()
	'Adding the data into the parameter'
	parameters.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
	'Set HttpHeader properties request to respective url'
	writeBinary.setHttpHeaderProperties(parameters)
	
	println ("Print the Http Header properties: ${writeBinary.setHttpHeaderProperties(parameters)}")
	'Set HttpBody request to respective url'
	writeBinary.setHttpBody(JsonOutput.toJson(json).toString())
	'Get Response and stored in the variable'
	def response = WS.sendRequest(writeBinary)
	'Print the Binary Response'
	println ("Binary Response : ${response}")
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	'Get the Request Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response for body content'
	println ("Response for the Binary: ${requestContent}")
	println ("Response for the Binary with UUID: ${requestContent.binaries[0].uuid}")
	internal.GlobalVariable.binaryId = requestContent.binaries[0].uuid
	println ("Global variable for Binary UUID: ${internal.GlobalVariable.binaryId}")
	'Verify the Binary File_name'
	WS.verifyElementPropertyValue(response,"binaries[0].filename",randomInput, FailureHandling.STOP_ON_FAILURE)
	Map Create_Binary = [:]
}
	