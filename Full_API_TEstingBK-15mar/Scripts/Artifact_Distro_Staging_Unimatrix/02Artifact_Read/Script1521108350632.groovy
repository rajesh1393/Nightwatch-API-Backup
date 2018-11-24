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
InternalData data = findTestData("Distributions")
'Looping for N- no.of input'
for (int index =0; index<data.getRowNumbers(); index++) {
	RequestObject readArtifacts = new RequestObject('')
	'Set the Request Method for read the Artifacts'
	readArtifacts.setRestRequestMethod('GET')
	'Set the URL for Read the Artifacts'
	readArtifacts.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/a5784c49027366bd728b3c24e6bf55c3/artifacts?access_token=3ae7b712deabdfbf1fc9bcf7e95ba225?name=')
	'Fetching the InternalData into varibale'
	def inputData1 = data.internallyGetValue("Name", index)
	'Print the inputData'
	println internal.GlobalVariable.inputData
	TestObjectProperty TestObjectProperty = new TestObjectProperty()
	parameters = new ArrayList()
	'Adding the data into the parameter'
	parameters.add(new TestObjectProperty('name', ConditionType.EQUALS, internal.GlobalVariable.inputData))
	'Print the Parameters'
	println (parameters)
	'Set the data in the Parameter'
	readArtifacts.setRestParameters(parameters)
	'Send a REST request after its URL has been updated'
	def response = WS.sendRequest(readArtifacts)
	'Print the Artifact response'
	println (response)
	'Get the Request for Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response body content'
	println (requestContent)
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	'Verify the Artifacts Name'
	WS.verifyElementPropertyValue(response,"artifacts[0].name",internal.GlobalVariable.inputData,FailureHandling.STOP_ON_FAILURE)
	internal.GlobalVariable.uuidData =requestContent.artifacts[0].uuid
	'Print the global variable'
	println internal.GlobalVariable.uuidData
	Map ReadArtifact = [:]
}