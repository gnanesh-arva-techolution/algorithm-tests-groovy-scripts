
import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*


/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 21:41
 */

return minNum_Test()

public List<TestResult> minNum_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    File folder = new File(TEST_DATA_PATH + Puzzle.IN_THE_FUTURE.getDescription());
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
        int A = scanner.nextInt();
        int K = scanner.nextInt();
        int P = scanner.nextInt();
        long startTime = System.currentTimeMillis();
        int output = minNum(A, K, P);
        long executionTime = System.currentTimeMillis() - startTime;
        if (output == expectedOutput) {
            testResult = new TestResult(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.IN_THE_FUTURE.getDescription());
    getTestStatus(testResults);
    return testResults;
}
