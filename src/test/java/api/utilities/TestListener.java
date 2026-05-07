package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import api.utilities.ExtentManager;

public class TestListener implements ITestListener, IAnnotationTransformer {

	
	// Triggered when a test starts
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.startTest(testName);
		ExtentManager.logStep("Test Started :" + testName);
	}

	// Triggered when a test success
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		if (!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtentManager.logStep( "Test End: " + testName + " - Test Passed");
		} else
			ExtentManager.logStepValidationForAPI("Test End: " + testName + " - Test Passed");
	}

	// Triggered when a test Failure
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String failureMessage = result.getThrowable().getMessage();
		ExtentManager.logStep(failureMessage);
		if (!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtentManager.logStep("Test End: " + testName + " -X Test Failed");
		} else
			ExtentManager.logFaliureAPI("Test End: " + testName + " -X Test Failed");
	}

	// Triggered when a test skip
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentManager.logSkip("Test Skipped :" + testName);
	}

	// Triggered when a suite starts
	@Override
	public void onStart(ITestContext context) {
		// Initialize Extent reports
		ExtentManager.getReporter();
	}

	// Triggered when a suite ends
	@Override
	public void onFinish(ITestContext context) {
		// Flush Extent reports
		ExtentManager.endTest();
//		File reportfile = new File(
//				System.getProperty("user.dir") + "/src/test/resources/extentreport/ExtentReport.html");
//		try {
//			Desktop.getDesktop().browse(reportfile.toURI());
//		} catch (IOException e) {
//			System.out.println("File No Found" + e.getMessage());
//		}

	}

}
