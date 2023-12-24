package listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.utilities.DriverUtility;
import com.utilities.ReportUtility;

public class Listnrs implements ITestListener {

	public void onTestStart(ITestResult result) {
		ReportUtility.test = ReportUtility.report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		ReportUtility.test.log(Status.PASS, "testcase passed with name " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		ReportUtility.test.log(Status.FAIL, "testcase failed with name " + result.getName());
		String path = DriverUtility.getScreenshot(result.getName());
		ReportUtility.test.addScreenCaptureFromPath(path);
	}

	public void onTestSkipped(ITestResult result) {
		ReportUtility.test.log(Status.SKIP, "testcase skipped with name " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		ReportUtility.reportInit();
	}

	public void onFinish(ITestContext context) {
		ReportUtility.report.flush();// to save the report we use flush() method
	}
}
