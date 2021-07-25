package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("cucumber-glue")
public class SliderPage {

    public SliderPage() {
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    public Map<Integer,Integer> moveSlidersTo(int percent){

        Map<Integer,Integer> sliderPercentMap = new HashMap<>();

        for(int i=1;i<7;i++){

            WebElement slider = DriverFactory.getDriver().findElement(By.id("slider"+i));
            WebElement sliderBar = slider.findElement(By.xpath("div/input"));

            Dimension sliderSize = sliderBar.getSize();
            int sliderWidth = sliderSize.getWidth();

            int pixelsToMove = getPixelsToMove(sliderWidth,percent,100,0);

            Actions builder = new Actions(DriverFactory.getDriver());

            builder.clickAndHold(sliderBar).moveByOffset((-(int)sliderWidth / 2),0)
                    .moveByOffset(pixelsToMove, 0).release().perform();


            String sliderOutput = slider.findElement(By.xpath("div/output")).getText();

            int sliderOutputPercent = Integer.parseInt(sliderOutput);

            sliderPercentMap.put(i,sliderOutputPercent);

        }

        return sliderPercentMap;
    }


    public static int getPixelsToMove(int sliderWidth, int Amount, int SliderMax, int SliderMin)
    {
        int pixels = 0;
        int tempPixels = sliderWidth;
        tempPixels = tempPixels / (SliderMax - SliderMin);
        tempPixels = tempPixels * (Amount - SliderMin);
        pixels = tempPixels;
        return pixels;
    }
}
