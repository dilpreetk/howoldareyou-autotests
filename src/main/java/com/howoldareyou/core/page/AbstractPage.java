package com.howoldareyou.core.page;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class AbstractPage {
    public static final String BASE_URL = "https://howoldru.m-messiah.cc/";

    public AbstractPage() {
        page(this);
    }

    public void open() {
        Selenide.open(BASE_URL);
        getWebDriver().manage().window().maximize();
    }

}
