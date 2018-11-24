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


suiteProperties.put('id', 'Test Suites/Artifact_Distribution_Staging-Unimatrix-loop')

suiteProperties.put('name', 'Artifact_Distribution_Staging-Unimatrix-loop')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("/Users/Shared/Jenkins/Documents/Bedrocket_API_Testing/Reports/Artifact_Distribution_Staging-Unimatrix-loop/20180314_051717/execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/Artifact_Distribution_Staging-Unimatrix-loop', suiteProperties, [new TestCaseBinding('Test Cases/Artifact_Distro-Staging-Unimatrix-loop/Artifact_Distro_staging_unimatrix', 'Test Cases/Artifact_Distro-Staging-Unimatrix-loop/Artifact_Distro_staging_unimatrix',  null)])
