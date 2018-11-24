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
import groovy.time.TimeCategory

'Data driven appoarch used to get a data from InternalData'
InternalData data = findTestData("Distributions")
'Looping for N- no.of input'
for (int index =0; index<data.getRowNumbers(); index++) {
	RequestObject distributionArtifact = new RequestObject('')
	'Set the Request Method for write the Distribution'
	distributionArtifact.setRestRequestMethod('POST')
	'Set the URL for write the Distribute an Artifact'
	distributionArtifact.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/a5784c49027366bd728b3c24e6bf55c3/distributions?access_token=3ae7b712deabdfbf1fc9bcf7e95ba225')
	'Fetching the InternalData into varibale'
	def inputData1 = data.internallyGetValue("Name", index)
	println internal.GlobalVariable.inputData
	def bodyContent ="""{
		    			"distributions": [ {    	
		        							"type_name": "boxxspring_distribution",        
											"destination_uuid": "60fbb0afec8cab6c4aa82285e4205f8f"
						} ]
					 }"""	
	//"add_at": "2018-02-14 13:42:17 -215+00:00"
	'Get Current Date'
	def now = new Date()
	use( TimeCategory ) {
		'Get the Add_at Time'
		def addTime = now.format("yyyy-MM-dd'T'HH:mm:ss'.000Z'", TimeZone.getTimeZone('UTC'))
		'Get the Remove_at Time'
		def removeTime = 1.minutes.from.now.format( "yyyy-MM-dd'T'HH:mm:ss'.000Z'", TimeZone.getTimeZone('UTC'))		
		'Print the addTime'
		println  addTime
		'Print the removeTime'
		println removeTime
		'Parsing the Json object'
		def json = new JsonSlurper().parseText(bodyContent)
		'Adding HTTP Body object as Json'
		json.distributions[0] << [name:internal.GlobalVariable.inputData,artifact_uuid:internal.GlobalVariable.uuidData,add_at:addTime,remove_at:removeTime]
		parameters = new ArrayList()
		TestObjectProperty TestObjectPropert = new TestObjectProperty()
		'Adding the data into the parameter'
		parameters.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
		'Set HttpHeader properties request to respective url'
		distributionArtifact.setHttpHeaderProperties(parameters)
		'Set HTTP Body for Json output as string'
		distributionArtifact.setHttpBody(JsonOutput.toJson(json).toString())
		println  ("set Http Body: ${distributionArtifact.getHttpBody()}")
		'Get Response from the Distribution API'
		def response = WS.sendRequest(distributionArtifact)
		'Print the Distributed Response'
		println  ("Response: ${(response)}")
		'Verify the response code'
		WS.verifyResponseStatusCode(response, 200, FailureHandling.STOP_ON_FAILURE)
		def distroWrite = new groovy.json.JsonSlurper()
		'Get the Response Content'
		def requestDistro = distroWrite.parseText(response.getResponseBodyContent())
		'Print the Response Content'
		println requestDistro.distributions[0].uuid
		def uuidDistro =requestDistro.distributions[0].uuid
		println uuidDistro
		'Verify the Artifacts Name'
		WS.verifyElementPropertyValue(response,"distributions[0].name",internal.GlobalVariable.inputData,FailureHandling.STOP_ON_FAILURE)
		'Verify the Artifacts uuid'
		WS.verifyElementPropertyValue(response,"distributions[0].artifact_uuid",internal.GlobalVariable.uuidData,FailureHandling.STOP_ON_FAILURE)
		
		'-----------------------------------Get Distributed Artifacts--------------------------------------------'
		'Request to get url from Object Repository'
		RequestObject readDistro = new RequestObject('')
		readDistro.setRestRequestMethod('GET')
		'Parsing the Json object'
		def distroRead = new groovy.json.JsonSlurper()
		'Set the URL for Read the Distribution'
		readDistro.setRestUrl("http://us-west-2.api.staging.unimatrix.io/realms/a5784c49027366bd728b3c24e6bf55c3/distributions?access_token=3ae7b712deabdfbf1fc9bcf7e95ba225&uuid=")
		parametersRead = new ArrayList()
		TestObjectProperty TestObjectProperty = new TestObjectProperty()
		'Adding the data into the parameter'
		parametersRead.add(new TestObjectProperty('uuid', ConditionType.EQUALS, uuidDistro))
		println (parametersRead)
		'Set the data in the Parameter'
		readDistro.setRestParameters(parametersRead)
		'Send a REST request after its URL has been changed'
		def responseDistro = WS.sendRequest(readDistro)
		println (responseDistro)
		'Get the Response Content'
		def reqDistro = distroRead.parseText(response.getResponseBodyContent())
		println (reqDistro)
		'Verify the Distribution uuid'
		WS.verifyElementPropertyValue(responseDistro,"distributions[0].uuid",uuidDistro,FailureHandling.STOP_ON_FAILURE)
		Map Distribution = [:]
	}
}