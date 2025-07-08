package com.theconnectedshop.tests;

import com.theconnectedshop.config.BaseTest;
import com.theconnectedshop.pages.SearchPage;

import org.junit.jupiter.api.*;

public class SearchTest extends BaseTest {

    @Test
    @DisplayName("Поиск возвращает хотя бы один результат")
    void searchReturnsResults() {
        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.searchFor("smart");

        Assertions.assertFalse(searchPage.getResults().isEmpty(), "Результаты поиска отсутствуют.");
    }
}