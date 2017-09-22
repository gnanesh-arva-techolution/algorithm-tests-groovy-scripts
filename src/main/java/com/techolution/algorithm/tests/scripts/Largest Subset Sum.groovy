import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 17:12
 */

maxSubsetSum_Test()

public List<TestResult> maxSubsetSum_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    File folder = new File(TEST_DATA_PATH + Puzzle.LARGEST_SUBSET_SUM.getDescription())
    for (File file : folder.listFiles()) {
        if (file.getAbsolutePath().contains("output")) {
            continue;
        }
        TestResult testResult;
        String filePath = file.getAbsolutePath();
        String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
        Scanner scanner = new Scanner(file);
        int[] k = new int[scanner.nextInt()];
        for (int i = 0; i < k.length; i++) {
            k[i] = scanner.nextInt();
        }
        long[] expectedOutput = new long[k.length];
        for (int i = 0; i < expectedOutput.length; i++) {
            expectedOutput[i] = outputScanner.nextLong();
        }
        long startTime = System.currentTimeMillis();
        long[] output = maxSubsetSum(k);
        long executionTime = System.currentTimeMillis() - startTime;
        if (Arrays.equals(output, expectedOutput)) {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.LARGEST_SUBSET_SUM.getDescription());
    getTestStatus(testResults);
    return testResults;
}