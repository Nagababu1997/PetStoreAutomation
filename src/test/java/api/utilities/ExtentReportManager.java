package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testcontext) {
        /*
         * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
         * new Date(); String currenttimestamp = df.format(dt);
         */

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp

        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify the location of the report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // title od the report
        sparkReporter.config().setReportName("StudentDetails API"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "StudentDetails API");
        extent.setSystemInfo("Module", "StudentDetails");
        extent.setSystemInfo("Sub Module", "API Testing");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testcontext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includeGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if (!includeGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includeGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getClass().getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getAfterGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();

        String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathofExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
