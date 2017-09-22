
import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*


/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 17:00
 */

return winner_Test()

public List<TestResult> winner_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    File folder = new File(TEST_DATA_PATH + Puzzle.FIND_THE_WINNER.getDescription());
    for (File file : folder.listFiles()) {
        if (file.getAbsolutePath().contains("output")) {
            continue;
        }
        TestResult testResult;
        String filePath = file.getAbsolutePath();
        String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
        Scanner scanner = new Scanner(file);
        int[] andrea = new int[scanner.nextInt()];
        for (int i = 0; i < andrea.length; i++) {
            andrea[i] = scanner.nextInt();
        }
        int[] maria = new int[scanner.nextInt()];
        for (int i = 0; i < maria.length; i++) {
            maria[i] = scanner.nextInt();
        }
        String gameType = scanner.next(); // Even or Odd.
        String expectedOutput = outputScanner.next();
        long startTime = System.currentTimeMillis();
        String output = winner(andrea, maria, gameType);
        long executionTime = System.currentTimeMillis() - startTime;
        if (output.equals(expectedOutput)) {
            testResult = new TestResult(inputFileName, output, expectedOutput, PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, output, expectedOutput, FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.FIND_THE_WINNER.getDescription());
    getTestStatus(testResults);
    return testResults;
}
