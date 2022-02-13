package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.DataBasePage;

public class DataBaseTest extends DataBasePage{

    @Test
    @Description("Assertion witch country population density less than 50000 50 p/sq.km")
    public void check_population_density() {
        open();
        assert_density();
        close();
    }

    @Test
    @Description("Assertion that summary population of all countries less than 2 billions")
    public void check_population_of_all_four_countries() {
        open();
        assert_population();
        close();
    }
}
