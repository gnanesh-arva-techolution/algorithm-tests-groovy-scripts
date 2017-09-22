package com.techolution.algorithm.tests.controller;

import com.techolution.algorithm.tests.utils.TestResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.techolution.algorithm.tests.utils.Utils.*;

/**
 * @author Gnanesh Arva
 * @since 21 Sep 2017 at 16:02
 */
@Controller
class AlgorithmTestController {

    @RequestMapping("/api/upload")
    ModelAndView processFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("puzzleName") String puzzleName) {
        if (multipartFile.isEmpty() || puzzleName == null || puzzleName.isEmpty()) {
            return new ModelAndView("result", "warning", UPLOAD_WARNING_MESSAGE);
        }
        List<TestResult> testResults = executeTestCases(multipartFile, puzzleName);
        ModelAndView modelAndView = new ModelAndView("result");
        if (testResults.isEmpty()) {
            modelAndView.addObject("warning", PROCESSING_WARNING_MESSAGE);
        } else {
            modelAndView.addObject("testResults", testResults);
        }
        return modelAndView;
    }

}
