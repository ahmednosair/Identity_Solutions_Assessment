package trainingplan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlannerTest {
    /**
     * Test the generation logic with 8 weeks plan.
     */
    @Test
    void testEightWeeks() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Saturday 31th of July 2021";
        String controlOutput = """
                Week #1 - Test - from 6 June 2021 to 12 June 2021
                Week #2 - Test - from 13 June 2021 to 19 June 2021
                Week #3 - Recovery - from 20 June 2021 to 26 June 2021
                Week #4 - Build 1 - from 27 June 2021 to 3 July 2021
                Week #5 - Build 2 - from 4 July 2021 to 10 July 2021
                Week #6 - Key - from 11 July 2021 to 17 July 2021
                Week #7 - Taper - from 18 July 2021 to 24 July 2021
                Week #8 - Race - from 25 July 2021 to 31 July 2021
                """;
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }

    /**
     * Tests the generation logic with 9 weeks plan.
     */
    @Test
    void testNineWeeks() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Saturday 7th of August 2021";
        String controlOutput = """
                Week #1 - Test - from 6 June 2021 to 12 June 2021
                Week #2 - Test - from 13 June 2021 to 19 June 2021
                Week #3 - Filler - from 20 June 2021 to 26 June 2021
                Week #4 - Recovery - from 27 June 2021 to 3 July 2021
                Week #5 - Build 1 - from 4 July 2021 to 10 July 2021
                Week #6 - Build 2 - from 11 July 2021 to 17 July 2021
                Week #7 - Key - from 18 July 2021 to 24 July 2021
                Week #8 - Taper - from 25 July 2021 to 31 July 2021
                Week #9 - Race - from 1 August 2021 to 7 August 2021
                """;
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }

    /**
     * Tests the generation logic with 10 weeks plan.
     */
    @Test
    void testTenWeeks() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Saturday 14th of August 2021";
        String controlOutput = """
                Week #1 - Test - from 6 June 2021 to 12 June 2021
                Week #2 - Test - from 13 June 2021 to 19 June 2021
                Week #3 - Build 2 - from 20 June 2021 to 26 June 2021
                Week #4 - Key - from 27 June 2021 to 3 July 2021
                Week #5 - Recovery - from 4 July 2021 to 10 July 2021
                Week #6 - Build 1 - from 11 July 2021 to 17 July 2021
                Week #7 - Build 2 - from 18 July 2021 to 24 July 2021
                Week #8 - Key - from 25 July 2021 to 31 July 2021
                Week #9 - Taper - from 1 August 2021 to 7 August 2021
                Week #10 - Race - from 8 August 2021 to 14 August 2021
                """;
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }

    /**
     * Tests the generation logic with 11 weeks plan.
     */
    @Test
    void testElevenWeeks() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Saturday 21th of August 2021";
        String controlOutput = """
                Week #1 - Test - from 6 June 2021 to 12 June 2021
                Week #2 - Test - from 13 June 2021 to 19 June 2021
                Week #3 - Build 1 - from 20 June 2021 to 26 June 2021
                Week #4 - Build 2 - from 27 June 2021 to 3 July 2021
                Week #5 - Key - from 4 July 2021 to 10 July 2021
                Week #6 - Recovery - from 11 July 2021 to 17 July 2021
                Week #7 - Build 1 - from 18 July 2021 to 24 July 2021
                Week #8 - Build 2 - from 25 July 2021 to 31 July 2021
                Week #9 - Key - from 1 August 2021 to 7 August 2021
                Week #10 - Taper - from 8 August 2021 to 14 August 2021
                Week #11 - Race - from 15 August 2021 to 21 August 2021
                """;
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }

    /**
     * Tests the generation logic with an invalid plan having less than 8 weeks as input.
     */
    @Test
    void testLessThanEightWeeks() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Saturday 12th of June 2021";
        String controlOutput = "Insufficient number of weeks for the plan";
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }

    /**
     * Tests the generation logic with an invalid plan having invalid dates as input.
     */
    @Test
    void testInvalidDates() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Friday 18th of September 2021";
        String controlOutput = "Invalid input date";
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }

    /**
     * Tests the generation logic with an invalid plan having weeks which are not full as input.
     */
    @Test
    void testPartialWeeks() {
        String startDate = "Sunday 6th of June 2021";
        String endDate = "Friday 17th of September 2021";
        String controlOutput = "Invalid number of weeks (not full weeks)";
        Planner planner = new Planner();
        String plan = planner.plan(startDate, endDate);
        Assertions.assertEquals(plan, controlOutput);
    }


}