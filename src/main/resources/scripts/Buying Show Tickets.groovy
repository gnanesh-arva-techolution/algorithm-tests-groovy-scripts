
import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 20:31
 */

return waitingTime_Test()

public List<TestResult> waitingTime_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource(TEST_DATA_PATH + Puzzle.BUYING_SHOW_TICKETS.getDescription());
    File folder = new File(url.toURI())
    for (File file : folder.listFiles()) {
        if (file.getAbsolutePath().contains("output")) {
            continue;
        }
        TestResult TestResult;
        String filePath = file.getAbsolutePath();
        String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
        long expectedOutput = outputScanner.nextLong();
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        int[] tickets = new int[n];
        for (int i = 0; i < n; i++) {
            tickets[i] = scanner.nextInt();
        }
        int p = scanner.nextInt();
        long startTime = System.currentTimeMillis();
        long output = waitingTime(tickets, p);
        long executionTime = System.currentTimeMillis() - startTime;
        if (output == expectedOutput) {
            TestResult = new TestResult(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(TestResult);
        } else {
            TestResult = new TestResult(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(TestResult);
        }
    }
    printTestResults(testResults, Puzzle.BUYING_SHOW_TICKETS.getDescription());
    getTestStatus(testResults);
    return testResults;
}
