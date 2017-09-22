import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.TEST_DATA_PATH
import static com.techolution.algorithm.tests.utils.Utils.getTestStatus
import static com.techolution.algorithm.tests.utils.Utils.printTestResults

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 22:07
 */
return jobOffers_Test()

public List<TestResult> jobOffers_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    File folder = new File(TEST_DATA_PATH + Puzzle.PSYCHOMETRIC_TESTING.getDescription());
    for (File file : folder.listFiles()) {
        if (file.getAbsolutePath().contains("output")) {
            continue;
        }
        TestResult testResult;
        String filePath = file.getAbsolutePath();
        String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
        Scanner scanner = new Scanner(file);
        int[] scores = new int[scanner.nextInt()];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scanner.nextInt();
        }
        int[] lowerLimits = new int[scanner.nextInt()];
        for (int i = 0; i < lowerLimits.length; i++) {
            lowerLimits[i] = scanner.nextInt();
        }
        int[] upperLimits = new int[scanner.nextInt()];
        for (int i = 0; i < upperLimits.length; i++) {
            upperLimits[i] = scanner.nextInt();
        }
        int[] expectedOutput = new int[lowerLimits.length];
        for (int i = 0; i < expectedOutput.length; i++) {
            expectedOutput[i] = outputScanner.nextInt();
        }
        long startTime = System.currentTimeMillis();
        int[] output = jobOffers(scores, lowerLimits, upperLimits);
        long executionTime = System.currentTimeMillis() - startTime;
        if (Arrays.equals(output, expectedOutput)) {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.PSYCHOMETRIC_TESTING.getDescription());
    getTestStatus(testResults);
    return testResults;
}