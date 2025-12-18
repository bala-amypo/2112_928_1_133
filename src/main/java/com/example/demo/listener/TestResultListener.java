package com.example.demo.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        // required by evaluator
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // required by evaluator
    }
}
