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


suiteProperties.put('id', 'Test Suites/QM_Unimatrix-Staging')

suiteProperties.put('name', 'QM_Unimatrix-Staging')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("/Users/Shared/Jenkins/Documents/Bedrocket_API_Testing/Reports/QM_Unimatrix-Staging/20180307_234523/execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/QM_Unimatrix-Staging', suiteProperties, [new TestCaseBinding('Test Cases/QM_Unimatrix-Staging/Create_Binary', 'Test Cases/QM_Unimatrix-Staging/Create_Binary',  null), new TestCaseBinding('Test Cases/QM_Unimatrix-Staging/Create_Binary_Ingressor', 'Test Cases/QM_Unimatrix-Staging/Create_Binary_Ingressor',  null), new TestCaseBinding('Test Cases/QM_Unimatrix-Staging/Read_Check_Binary-Ingressor', 'Test Cases/QM_Unimatrix-Staging/Read_Check_Binary-Ingressor',  null), new TestCaseBinding('Test Cases/QM_Unimatrix-Staging/Read_Check_Binary', 'Test Cases/QM_Unimatrix-Staging/Read_Check_Binary',  null), new TestCaseBinding('Test Cases/QM_Unimatrix-Staging/Negative_Binary_Ingressor', 'Test Cases/QM_Unimatrix-Staging/Negative_Binary_Ingressor',  null)])
