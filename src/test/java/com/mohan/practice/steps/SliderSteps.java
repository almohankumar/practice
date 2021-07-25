package com.mohan.practice.steps;

import com.mohan.practice.pages.SliderPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SliderSteps {

    @Autowired
    SliderPage sliderPage;

    List<Integer> percentList = new ArrayList<>();

    @When("I move the sliders to {int} percent")
    public void iMoveTheSlidersToPercent(int percent) {

        Map<Integer,Integer> sliderPercentMap = sliderPage.moveSlidersTo(percent);

        percentList.addAll(sliderPercentMap.values());


    }

    @Then("I verify that all the sliders are at {int} percent")
    public void iVerifyThatAllTheSlidersAreAtPercent(int percent) {

        long actual = percentList.stream().filter(p->p.equals(percent)).count();
        long expected = percentList.size();

        Assert.assertEquals(actual,expected);
    }
}
