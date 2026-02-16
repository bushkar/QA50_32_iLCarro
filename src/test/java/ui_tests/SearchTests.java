package ui_tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import utils.RetryAnalyser;

import java.lang.reflect.Method;

public class SearchTests extends ApplicationManager {

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void searchPositiveTest(Method method) {
        logger.info("start test" + method.getName());
    }
}
