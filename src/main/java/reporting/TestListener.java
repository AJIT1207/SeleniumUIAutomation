package reporting;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        // Suite start
    }

    @Override
    public void onFinish(ISuite suite) {
        // Flush report when suite ends
        extent.flush();
        System.out.println("=== Extent Report Flushed ===");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
        test.log(Status.INFO, "Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testThread.get();
        test.log(Status.PASS, "Test passed");

        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest baseTest) {
            String path = baseTest.takeScreenshot(result.getMethod().getMethodName() + "_PASS");
            if (path != null) {
                try {
                    test.addScreenCaptureFromPath(path);
                } catch (Exception e) {
                    test.log(Status.WARNING, "Failed to attach pass screenshot: " + e.getMessage());
                }
            }
        }
    }


    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();
        test.log(Status.FAIL, "Test failed: " + result.getThrowable());

        // Try to attach screenshot if BaseTest has driver
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest baseTest) {
            String path = baseTest.takeScreenshot(result.getMethod().getMethodName());
            if (path != null) {
                try {
                    test.addScreenCaptureFromPath(path);
                } catch (Exception e) {
                    test.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onTestFailedWithTimeout(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
