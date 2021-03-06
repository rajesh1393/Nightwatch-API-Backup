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
	def url = data.internallyGetValue("URL", index)	
	RequestObject readBinaryIngressor = new RequestObject('')
	'Set the Request Method for Read the Binary_Ingressor'
	readBinaryIngressor.setRestRequestMethod('GET')
	println ("Binary_UUID:${internal.GlobalVariable.binaryReadId}")
	println ("UUID:${internal.GlobalVariable.binaryIngressId}")
	'Set the URL for write the BinaryIngressor'
	readBinaryIngressor.setRestUrl('http://us-west-2.api.unimatrix.io/realms/faf10fa99bf536f7c219b7c37cf7a705/binaries/'+internal.GlobalVariable.binaryReadId+'/ingressors?uuid.in='+internal.GlobalVariable.binaryIngressId+'&access_token=1b014b8eb4dfec8cbb137b81b42205b8')
	'Get Response and stored in the variable'
	def response = WS.sendRequest(readBinaryIngressor)
	Thread.sleep(2000)
	response = WS.sendRequest(readBinaryIngressor)
	'Print the Binary_Ingressor Response'
	println response
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	'Get the Request Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response for body content'
	println requestContent.binary_ingressors[0].content_type
	println requestContent.binary_ingressors[0].state
	'Verify the Binary_Ingressor State'
	//WS.verifyElementPropertyValue(response,"binaries_ingressors[0].state",'running', FailureHandling.STOP_ON_FAILURE)
	//Thread.sleep(1000)
	response = WS.sendRequest(readBinaryIngressor)
	def requestContentFinal = slurper.parseText(response.getResponseBodyContent())
	println (" Response for the Binaries with Content type: ${ requestContentFinal.binary_ingressors[0].content_type}")
	println (" Response for the Binaries with state: ${requestContentFinal.binary_ingressors[0].state}")
	println (" Response for the Binaries : ${requestContentFinal}")
	'Verify the Binary_Ingressor State'
	WS.verifyElementPropertyValue(response,"binary_ingressors[0].state",'succeeded', FailureHandling.STOP_ON_FAILURE)
	'Verify the Binary_Ingressor URL'
	WS.verifyElementPropertyValue(response,"binary_ingressors[0].url",url, FailureHandling.STOP_ON_FAILURE)
	Map readCheckBinaryIngressor = [:]
	
	
}
	