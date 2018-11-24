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
	RequestObject readBinaries = new RequestObject('')
	'Set the Request Method for Read the Binary'
	readBinaries.setRestRequestMethod('GET')
	'Set the URL for Read the Binary'
	readBinaries.setRestUrl('http://us-west-2.api.acceptance.unimatrix.io/realms/f53dbe250d8a05d1ca44b28b95eb5e20/binaries?uuid.in='+internal.GlobalVariable.binaryReadId+'&access_token=5422e9fefd05e874dfea631770b9f7bb')
	'Fetching the InternalData into varibale'
	def url = data.internallyGetValue("URL", index)
	def exts = url.substring(url.lastIndexOf('.')+1);
	if (exts.equals('jpg')) {
		exts = 'jpeg';
	}
	exts ="image/"+exts
	println ("Extension: ${exts}") 	
	'Get Response and stored in the variable'
	def response = WS.sendRequest(readBinaries)
	'Print the Binary Response'
	println response
	'Verify the response code'
	WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
	'Get the Request Body Content'
	def slurper = new groovy.json.JsonSlurper()
	'Get the Response Content'
	def requestContent = slurper.parseText(response.getResponseBodyContent())
	'Print the Response for body content'
	println ("Print the Response for Binaries Content type: ${requestContent.binaries[0].content_type}")
	println ("Print the Response for Binaries uuid: ${requestContent.binaries[0].uuid}")
	internal.GlobalVariable.contentTypes = requestContent.binaries[0].content_type
	//'Verify the Artifacts Name'
	//WS.verifyElementPropertyValue(response,"binaries[0].url",url, FailureHandling.STOP_ON_FAILURE)
	'Verify the Binaries Content_Type'
	WS.verifyElementPropertyValue(response,"binaries[0].content_type",exts, FailureHandling.STOP_ON_FAILURE)
	Map Read_Check_Binary = [:]
	
}
	