
import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 18:13
 */

return circles_Test()

public List<TestResult> circles_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource(TEST_DATA_PATH + Puzzle.TWO_CIRCLES.getDescription());
    File folder = new File(url.toURI())
    for (File file : folder.listFiles()) {
        if (file.getAbsolutePath().contains("output")) {
            continue;
        }
        TestResult testResult;
        String filePath = file.getAbsolutePath();
        String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
        Scanner scanner = new Scanner(file);
        String[] info = new String[scanner.nextInt()];
        scanner.nextLine();
        for (int i = 0; i < info.length; i++) {
            info[i] = scanner.nextLine();
        }
        String[] expectedOutput = new String[info.length];
        for (int i = 0; i < expectedOutput.length; i++) {
            expectedOutput[i] = outputScanner.nextLine();
        }
        long startTime = System.currentTimeMillis();
        String[] output = circles(info);
        long executionTime = System.currentTimeMillis() - startTime;
        if (Arrays.equals(output, expectedOutput)) {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.TWO_CIRCLES.getDescription());
    getTestStatus(testResults);
    return testResults;
}