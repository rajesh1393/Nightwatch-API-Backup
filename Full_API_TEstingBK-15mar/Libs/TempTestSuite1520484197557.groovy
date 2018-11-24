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


suiteProperties.put('id', 'Test Suites/QM_Unimatrix_Acceptance')

suiteProperties.put('name', 'QM_Unimatrix_Acceptance')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("/Users/Shared/Jenkins/Documents/Bedrocket_API_Testing/Reports/QM_Unimatrix_Acceptance/20180307_234317/execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/QM_Unimatrix_Acceptance', suiteProperties, [new TestCaseBinding('Test Cases/QM_Unimatrix_Acceptance/Create_Binary', 'Test Cases/QM_Unimatrix_Acceptance/Create_Binary',  null), new TestCaseBinding('Test Cases/QM_Unimatrix_Acceptance/Create_Binary_Ingressor', 'Test Cases/QM_Unimatrix_Acceptance/Create_Binary_Ingressor',  null), new TestCaseBinding('Test Cases/QM_Unimatrix_Acceptance/Read_Check_Binary-Ingressor', 'Test Cases/QM_Unimatrix_Acceptance/Read_Check_Binary-Ingressor',  null), new TestCaseBinding('Test Cases/QM_Unimatrix_Acceptance/Read_Check_Binary', 'Test Cases/QM_Unimatrix_Acceptance/Read_Check_Binary',  null), new TestCaseBinding('Test Cases/QM_Unimatrix_Acceptance/Negative_Binary_Ingressor', 'Test Cases/QM_Unimatrix_Acceptance/Negative_Binary_Ingressor',  null)])
