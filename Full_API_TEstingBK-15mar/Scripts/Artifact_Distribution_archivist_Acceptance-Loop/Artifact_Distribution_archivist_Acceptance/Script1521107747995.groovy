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
'Looping for N- no.of input'
for (int index =0; index<100; index++) {
	
Map ArtifactWrite = WebUI.callTestCase(findTestCase('Artifact_Distribution_archivist_Acceptance/01ArtifactWrite'), [('ArtifactWrite'):''])
Map ArtifactRead = WebUI.callTestCase(findTestCase('Artifact_Distribution_archivist_Acceptance/02Artifact_Read'),[('ArtifactRead'):''])
Map DistributeArtifact = WebUI.callTestCase(findTestCase('Artifact_Distribution_archivist_Acceptance/03DistributeArtifact'), [('DistributeArtifact'):''])
	
	
}
	