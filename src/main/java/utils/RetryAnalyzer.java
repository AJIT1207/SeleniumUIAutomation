package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int attempt = 0;
    private final int maxRetry = 1; // retry once

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < maxRetry) {
            attempt++;
            System.out.println("Retrying test: " + result.getMethod().getMethodName() +
                    " | Attempt: " + attempt);
            return true;
        }
        return false;
    }
}
