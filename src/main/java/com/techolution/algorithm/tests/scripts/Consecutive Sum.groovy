import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 21:21
 */

return consecutive_Test()

public List<TestResult> consecutive_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    File folder = new File(TEST_DATA_PATH + Puzzle.CONSECUTIVE_SUM.getDescription());
    for (File file : folder.listFiles()) {
        if (file.getAbsolutePath().contains("output")) {
            continue;
        }
        TestResult testResult;
        String filePath = file.getAbsolutePath();
        String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
        int expectedOutput = outputScanner.nextInt();
        Scanner scanner = new Scanner(file);
        long num = scanner.nextLong();
        long startTime = System.currentTimeMillis();
        int output = consecutive(num);
        long executionTime = System.currentTimeMillis() - startTime;
        if (output == expectedOutput) {
            testResult = new TestResult(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.CONSECUTIVE_SUM.getDescription());
    getTestStatus(testResults);
    return testResults
}

