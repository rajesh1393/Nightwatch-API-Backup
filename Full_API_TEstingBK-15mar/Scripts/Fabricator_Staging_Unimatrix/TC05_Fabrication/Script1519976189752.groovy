import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.testobject.RequestObject as RequestObject

InternalData data = findTestData('Data Files/Fabrication')

for (int index = 0; index < data.getRowNumbers(); index++) {
    'Writing Fabrication and returns its response'
    RequestObject writeFabricator = new RequestObject('')

    writeFabricator.setRestRequestMethod('POST')

    writeFabricator.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/' + data.internallyGetValue('Realm_uuid', 
            index) + '/fabrications?access_token=88c6f9ed498a38fd13af586bd9b42f8e')

    def responseWrite = WS.sendRequest(writeFabricator)

    'Verify the response code'
    WS.verifyResponseStatusCode(responseWrite, 200, FailureHandling.STOP_ON_FAILURE)

    'Verify if uuid created after sending request'
    def slurper = new JsonSlurper()

    def Write_result = slurper.parseText(responseWrite.getResponseText())

    println("uuid ${write_uuid = Write_result.fabrications[0].uuid}")

    println("source_realm_uuid ${write_source_realm_uuid = Write_result.fabrications[0].source_realm_uuid}")

    'Verify if generated source_realm_uuid match with the request realm_uuid'
    WS.verifyElementPropertyValue(responseWrite, 'fabrications[0].source_realm_uuid', data.internallyGetValue('Realm_uuid', 
            index), FailureHandling.STOP_ON_FAILURE)

    'Send Request to Read Fabrication by using generated uuid & source realm uuid'
    RequestObject readFabricator = new RequestObject('')

    readFabricator.setRestRequestMethod('GET')

    readFabricator.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/' + write_source_realm_uuid + 
        '/fabrications?uuid=' + write_uuid + '&access_token=88c6f9ed498a38fd13af586bd9b42f8e')
	def responseRead = WS.sendRequest(readFabricator)
	
    for (def delayTime = 0; delayTime <= 20; delayTime ++) {
        'Reading Fabrication and returns its response'
        responseRead = WS.sendRequest(readFabricator)

        'Verify the response code'
        WS.verifyResponseStatusCode(responseRead, 200, FailureHandling.STOP_ON_FAILURE)

        'Verify if generated state succeeded'
        def Read_result = slurper.parseText(responseRead.getResponseText())

       println("State ${Read_result.fabrications[0].state}")

        if ((Read_result.fabrications[0].state == 'idle') || (Read_result.fabrications[0].state == 'running')) {
            WS.delay(180)
			
        } 
		else if ( Read_result.fabrications[0].state == 'succeeded' || Read_result.fabrications[0].state == 'failed') {          
			
		 println("uuid ${read_uuid = Read_result.fabrications[0].uuid}")
		
		 println("source_realm_uuid ${read_source_realm_uuid = Read_result.fabrications[0].source_realm_uuid}")

		 println("target_realm_uuid ${read_target_realm_uuid = Read_result.fabrications[0].target_realm_uuid}")

		 println("state ${read_state = Read_result.fabrications[0].state}")

		 println("message ${read_message = Read_result.fabrications[0].message}")
		 				
		 break
		
		}
    }
	WS.verifyElementPropertyValue(responseRead, 'fabrications[0].state', 'succeeded', FailureHandling.STOP_ON_FAILURE)
	
	WS.verifyElementPropertyValue(responseRead, 'fabrications[0].uuid', write_uuid, FailureHandling.STOP_ON_FAILURE)
	
	WS.verifyElementPropertyValue(responseRead, 'fabrications[0].source_realm_uuid', write_source_realm_uuid, FailureHandling.STOP_ON_FAILURE)
    
    'Sending Request to compare blueprints in the Source realm'
    RequestObject blueprintsSource = new RequestObject('')

    blueprintsSource.setRestRequestMethod('GET')

    blueprintsSource.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/' + read_source_realm_uuid + 
        '/blueprints?access_token=88c6f9ed498a38fd13af586bd9b42f8e&count=100')

    def responseBlueprintsSource = WS.sendRequest(blueprintsSource)

    'Verify the response code'
    WS.verifyResponseStatusCode(responseBlueprintsSource, 200, FailureHandling.STOP_ON_FAILURE)

    'Sending Request to compare blueprints in the Target realm'
    RequestObject blueprintsTarget = new RequestObject('')

    blueprintsTarget.setRestRequestMethod('GET')

    blueprintsTarget.setRestUrl(('http://us-west-2.api.staging.unimatrix.io/realms/' + read_target_realm_uuid) + 
        '/blueprints?access_token=88c6f9ed498a38fd13af586bd9b42f8e&count=100')

    def responseBlueprintsTarget = WS.sendRequest(blueprintsTarget)

    'Verify the response code'
    WS.verifyResponseStatusCode(responseBlueprintsTarget, 200, FailureHandling.STOP_ON_FAILURE)

    'Verify if the Source Realm and Target Realm matches'
    def Source_realm = slurper.parseText(responseBlueprintsSource.getResponseText())

    def Target_realm = slurper.parseText(responseBlueprintsTarget.getResponseText())

    println("blueprintSourceCount ${blueprintSourceCount = Source_realm.$this.unlimited_count}")

    println("blueprintTargetCount ${blueprintTargetCount = Target_realm.$this.unlimited_count}")

    assert blueprintSourceCount == blueprintTargetCount

    'Sending Request to compare blueprint attribute in the Source realm'
    RequestObject blueprintAttributeSource = new RequestObject('')

    blueprintAttributeSource.setRestRequestMethod('GET')

    blueprintAttributeSource.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/' + read_source_realm_uuid + 
        '/blueprint_attributes?access_token=88c6f9ed498a38fd13af586bd9b42f8e&count=100')

    def responseBlueprintAttributeSource = WS.sendRequest(blueprintAttributeSource)

    'Verify the response code'
    WS.verifyResponseStatusCode(responseBlueprintAttributeSource, 200, FailureHandling.STOP_ON_FAILURE)

    'Sending Request to compare blueprint attribute in the Target realm'
    RequestObject blueprintAttributeTarget = new RequestObject('')

    blueprintAttributeTarget.setRestRequestMethod('GET')

    blueprintAttributeTarget.setRestUrl('http://us-west-2.api.staging.unimatrix.io/realms/' + read_target_realm_uuid + 
        '/blueprint_attributes?access_token=88c6f9ed498a38fd13af586bd9b42f8e&count=100')

    def responseBlueprintAttributeTarget = WS.sendRequest(blueprintAttributeTarget)

    'Verify the response code'
    WS.verifyResponseStatusCode(responseBlueprintAttributeTarget, 200, FailureHandling.STOP_ON_FAILURE)

    'Verify if the Source Realm and Target Realm in blueprint attribute matches'
    def Source_realm_Attribute = slurper.parseText(responseBlueprintAttributeSource.getResponseText())

    def Target_realm_Attribute = slurper.parseText(responseBlueprintAttributeTarget.getResponseText())

    println("blueprintAttriSourceCount ${blueprintAttriSourceCount = Source_realm_Attribute.$this.unlimited_count}")

    println("blueprintAttriTargetCount ${blueprintAttriTargetCount = Target_realm_Attribute.$this.unlimited_count}")

    assert blueprintAttriSourceCount == blueprintAttriTargetCount
	Map Fabrication = [:]
}

