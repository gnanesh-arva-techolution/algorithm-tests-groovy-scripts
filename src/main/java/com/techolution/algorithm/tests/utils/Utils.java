package com.techolution.algorithm.tests.utils;

import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilationFailedException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.techolution.algorithm.tests.utils.TestResult.Status.FAILED;
import static com.techolution.algorithm.tests.utils.TestResult.Status.TIMED_OUT;


/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 07:47
 */
public class Utils {

    // Strings.
    public static final String UPLOAD_WARNING_MESSAGE = "Unable to upload the file.";
    public static final String PROCESSING_WARNING_MESSAGE = "Something went wrong.";
    public static final String TEST_DATA_PATH = "C:\\Gnanesh Arva Backup\\Algorithm Tests\\algorithm-tests-groovy-scripts\\src\\main\\resources\\data\\";
    public static final String TEST_SCRIPTS_PATH = "C:\\Gnanesh Arva Backup\\Algorithm Tests\\algorithm-tests-groovy-scripts\\src\\main\\resources\\scripts\\";

    // Numbers
    public static final Integer TIME_OUT = 5; // 5 seconds.

    public static void printTestResults(List<TestResult> testResults, String puzzleName) {
        System.out.println(puzzleName);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("%-18.18s  %-18.18s  %-18.18s  %-18.18s %-18.18s%n", "Input File", "  Status", "Expected Output", "       Output    ", "Exe Time(millis)");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for (TestResult testResult : testResults) {
            String inputFileName = testResult.getInputFileName();
            String status = testResult.getStatus();
            String expectedOutput = testResult.getExpectedOutput().length() > 10 ? "It's Huge" : testResult.getExpectedOutput();
            String output = testResult.getOutput().length() > 10 ? "It's Huge" : testResult.getOutput();
            String executionTime = testResult.getExecutionTime();
            System.out.printf("%-20.20s  %-20.20s  %-20.20s  %-20.20s %-20.20s%n", inputFileName, status, expectedOutput, output, executionTime);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static boolean getTestStatus(List<TestResult> testResults) {
        boolean testStatus = true;
        for (TestResult testResult : testResults) {
            if (TIMED_OUT.getDescription().equals(testResult.getStatus()) || FAILED.getDescription().equals(testResult.getStatus())) {
                testStatus = false;
                break;
            }
        }
        return testStatus;
    }

    public static List<TestResult> executeTestCases(MultipartFile multipartFile, String puzzleName) {
        List<TestResult> testResults = new ArrayList<>();
        try {
            File candidateFile = File.createTempFile(generateRandomFileName(), ".groovy");
            multipartFile.transferTo(candidateFile);
            File fileToRun = File.createTempFile(generateRandomFileName(), ".groovy");
            BufferedReader br = new BufferedReader(new FileReader(candidateFile));
            BufferedWriter bW = new BufferedWriter(new FileWriter(fileToRun));
            List<String> list = new ArrayList<>();
            String line = br.readLine();
            // candidate file to ArrayList.
            while (line != null) {
                if (line.contains("package ") || line.contains("class ") || line.trim().isEmpty()) {
                    // Do not add it to process.
                } else {
                    list.add(line);
                }
                line = br.readLine();
            }
            // To remove the } of candidate file. (Which is the closing brace of class).
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).trim().equals("}")) {
                    list.set(i, "\n");
                    break;
                }
            }
            // ArrayList to fileToRun.
            for (String lineTemp : list) {
                bW.write(lineTemp);
                bW.write("\n");
            }
            bW.flush();
            File testScript = new File(TEST_SCRIPTS_PATH + puzzleName + ".groovy");
            br = new BufferedReader(new FileReader(testScript));
            line = br.readLine();
            bW.append("\n");
            // testScript to fileToRun
            while (line != null) {
                bW.append(line);
                bW.append("\n");
                line = br.readLine();
            }
            bW.flush();
            // Run candidate file + testScript
            GroovyShell groovyShell = new GroovyShell();
            testResults = (List<TestResult>) groovyShell.run(fileToRun, new ArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (CompilationFailedException e) {
            e.printStackTrace();
        }
        return testResults;
    }

    public static String generateRandomFileName() {
        Random random = new Random();
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            stringBuilder.append(alphabets.charAt(random.nextInt(26)));
        }
        return stringBuilder.toString();
    }

}
