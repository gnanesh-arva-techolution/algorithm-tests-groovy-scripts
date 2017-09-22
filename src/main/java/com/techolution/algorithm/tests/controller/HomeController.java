package com.techolution.algorithm.tests.controller;

import com.techolution.algorithm.tests.enums.Puzzle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Gnanesh Arva
 * @since 21 Sep 2017 at 17:12
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("home", "puzzleNames", Puzzle.getAllPuzzleNames());
    }

}
