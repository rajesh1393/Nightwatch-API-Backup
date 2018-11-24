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


suiteProperties.put('id', 'Test Suites/Artifact_Dristro_Accept-Unimatrix')

suiteProperties.put('name', 'Artifact_Dristro_Accept-Unimatrix')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("/Users/Shared/Jenkins/Documents/Bedrocket_API_Testing/Reports/Artifact_Dristro_Accept-Unimatrix/20180307_043545/execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/Artifact_Dristro_Accept-Unimatrix', suiteProperties, [new TestCaseBinding('Test Cases/Artifact_Distro_Acceptance_Unimatrix/01ArtifactWrite', 'Test Cases/Artifact_Distro_Acceptance_Unimatrix/01ArtifactWrite',  null), new TestCaseBinding('Test Cases/Artifact_Distro_Acceptance_Unimatrix/02Artifact_Read', 'Test Cases/Artifact_Distro_Acceptance_Unimatrix/02Artifact_Read',  null), new TestCaseBinding('Test Cases/Artifact_Distro_Acceptance_Unimatrix/03DistributeArtifact', 'Test Cases/Artifact_Distro_Acceptance_Unimatrix/03DistributeArtifact',  null)])
