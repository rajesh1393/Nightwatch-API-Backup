import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/Distributions')

suiteProperties.put('name', 'Distributions')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("/Users/Shared/Jenkins/Documents/Bedrocket_API_Testing/Reports/Distributions/20180313_191709/execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/Distributions', suiteProperties, [new TestCaseBinding('Test Cases/Artifact_Distribution/01ArtifactWrite', 'Test Cases/Artifact_Distribution/01ArtifactWrite',  [ 'Name' : 'Artifact_Distro' ,  ]), new TestCaseBinding('Test Cases/Artifact_Distribution/02Artifact_Read', 'Test Cases/Artifact_Distribution/02Artifact_Read',  [ 'Name' : 'Artifact_Distro' ,  ]), new TestCaseBinding('Test Cases/Artifact_Distribution/03DistributeArtifact', 'Test Cases/Artifact_Distribution/03DistributeArtifact',  [ 'Name' : 'Artifact_Distro' ,  ])])
