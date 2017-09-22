/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 21:52
 */



import com.techolution.algorithm.tests.enums.Puzzle
import com.techolution.algorithm.tests.utils.TestResult
import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED
import static com.techolution.algorithm.tests.utils.TestResult.Status.PASSED
import static com.techolution.algorithm.tests.utils.Utils.*

return balancedOrNot_Test()

public List<TestResult> balancedOrNot_Test() throws Exception {
    List<TestResult> testResults = new ArrayList<>();
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource(TEST_DATA_PATH + Puzzle.BALANCED_OR_NOT.getDescription());
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
        String[] expressions = new String[scanner.nextInt()];
        for (int i = 0; i < expressions.length; i++) {
            expressions[i] = scanner.next();
        }
        int[] maxReplacements = new int[scanner.nextInt()];
        for (int i = 0; i < maxReplacements.length; i++) {
            maxReplacements[i] = scanner.nextInt();
        }
        int[] expectedOutput = new int[expressions.length];
        for (int i = 0; i < expectedOutput.length; i++) {
            expectedOutput[i] = outputScanner.nextInt();
        }
        long startTime = System.currentTimeMillis();
        int[] output = balancedOrNot(expressions, maxReplacements);
        long executionTime = System.currentTimeMillis() - startTime;
        if (Arrays.equals(output, expectedOutput)) {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        } else {
            testResult = new TestResult(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
            testResults.add(testResult);
        }
    }
    printTestResults(testResults, Puzzle.BALANCED_OR_NOT.getDescription());
    println getTestStatus(testResults);
    return testResults
}
