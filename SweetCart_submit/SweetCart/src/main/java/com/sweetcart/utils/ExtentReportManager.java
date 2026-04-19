package com.sweetcart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sweetcart.config.ConfigReader;

public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> testLocal = new ThreadLocal<>();

    private ExtentReportManager() {}

    public static synchronized ExtentReports getExtentReports() {
        if (extentReports == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(
                    ConfigReader.getInstance().getReportPath());
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("SweetCart – Test Report");
            spark.config().setReportName("Sweet Shop Automation Suite");
            extentReports = new ExtentReports();
            extentReports.attachReporter(spark);
            extentReports.setSystemInfo("AUT", "sweetshop.netlify.app");
            extentReports.setSystemInfo("Framework", "Selenium + TestNG + POM");
        }
        return extentReports;
    }

    public static ExtentTest getTest()          { return testLocal.get(); }
    public static void setTest(ExtentTest test) { testLocal.set(test); }
    public static void flush()                  { if (extentReports != null) extentReports.flush(); }
}
