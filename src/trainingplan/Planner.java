package trainingplan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Planner {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEEE d['st']['nd']['rd']['th'] 'of' MMMM yyyy");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
    private final String[] startWeeks = {"Test", "Test"};
    private final String[] fillerWeeks = {"Filler"};
    private final String[] mainWeeks = {"Recovery", "Build 1", "Build 2", "Key"};
    private final String[] endWeeks = {"Taper", "Race"};

    private LocalDate planStartDate;

    private int noOfWeeks;

    private String errorMsg;

    /**
     * - Copies source array into destination array bounded by start and end indexes in the destination array.
     *
     * @param destArr   destination array.
     * @param destStart start index of destination array.
     * @param destEnd   end index of destination array.
     * @param srcArr    source array.
     */
    private void copyRange(String[] destArr, int destStart, int destEnd, String[] srcArr) {
        for (int i = destStart, j = 0; i < destEnd && j < srcArr.length; i++, j++) {
            destArr[i] = srcArr[j];
        }
    }

    /**
     * - Copies source array multiple times in a cycle starting from a cycleStart index in the source array
     * into destination array bounded by start and end indexes in the destination array.
     * - How many times source array will be copied into destination array depends on the available size between
     * destination array bounds.
     *
     * @param destArr    destination array.
     * @param destStart  start index of destination array.
     * @param destEnd    end index of destination array.
     * @param srcArr     source array.
     * @param cycleStart start index to begin cycle with in source array.
     */
    private void copyRangeWithCycle(String[] destArr, int destStart, int destEnd, String[] srcArr, int cycleStart) {
        int j = cycleStart;
        for (int i = destStart; i < destEnd; i++) {
            destArr[i] = srcArr[j];
            j = (j + 1) % srcArr.length;
        }
    }

    /**
     * Parses input raw dates into Date instances.
     *
     * @param rawStartDate raw start date entered by user.
     * @param rawEndDate   raw end date entered by user.
     * @return true if the input is valid false otherwise.
     */
    private boolean parseInput(String rawStartDate, String rawEndDate) {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(rawStartDate, inputFormatter);
            endDate = LocalDate.parse(rawEndDate, inputFormatter);
        } catch (DateTimeParseException e) {
            errorMsg = "Invalid input date";
            return false;
        }
        LocalDate endDateExclusive = endDate.plusDays(1);
        int numOfDays = (int) ChronoUnit.DAYS.between(startDate, endDateExclusive);
        int daysInAWeek = 7;
        if (numOfDays % daysInAWeek != 0) {
            errorMsg = "Invalid number of weeks (not full weeks)";
            return false;
        }
        int numOfWeeks = numOfDays / daysInAWeek;
        if (numOfWeeks < startWeeks.length + mainWeeks.length + endWeeks.length) {
            errorMsg = "Insufficient number of weeks for the plan";
            return false;
        }
        planStartDate = startDate;
        noOfWeeks = numOfWeeks;
        return true;
    }

    /**
     * Parses the weeks array into the required output format using week names and start date.
     *
     * @param weeks the weeks array which holds the name for each week.
     * @return String which is the required format of the output
     */
    private String parseOutput(String[] weeks) {
        LocalDate startDate = planStartDate;
        StringBuilder plan = new StringBuilder();
        for (int i = 0; i < weeks.length; i++) {
            plan.append("Week #");
            plan.append(i + 1);
            plan.append(" - ");
            plan.append(weeks[i]);
            plan.append(" - ");
            plan.append("from ");
            plan.append(startDate.format(outputFormatter));
            startDate = startDate.plusWeeks(1);
            plan.append(" to ");
            plan.append(startDate.minusDays(1).format(outputFormatter));
            plan.append('\n');
        }
        return plan.toString();
    }

    /**
     * The main method which contains the required logic to generate the plan.
     * It uses all other methods along with generation logic to turn input into the required output.
     *
     * @param rawStartDate raw start date entered by user.
     * @param rawEndDate   raw end date entered by user.
     * @return String which is the required plan.
     */
    public String plan(String rawStartDate, String rawEndDate) {
        boolean parseSuccess = parseInput(rawStartDate, rawEndDate);
        if (!parseSuccess) {
            return errorMsg;
        }
        String[] weeks = new String[noOfWeeks];
        copyRange(weeks, 0, startWeeks.length, startWeeks);
        copyRange(weeks, weeks.length - endWeeks.length, weeks.length, endWeeks);
        int availableMainWeeks = weeks.length - startWeeks.length - endWeeks.length;
        int extraWeeks = availableMainWeeks % mainWeeks.length;
        int filler = 0;
        int mainCycleStart;
        if (extraWeeks <= fillerWeeks.length) {
            copyRange(weeks, startWeeks.length, startWeeks.length + extraWeeks, fillerWeeks);
            filler = extraWeeks;
            extraWeeks = 0;
        }
        mainCycleStart = (extraWeeks == 0) ? (0) : (mainWeeks.length - extraWeeks);
        copyRangeWithCycle(weeks, startWeeks.length + filler, weeks.length - endWeeks.length, mainWeeks, mainCycleStart);
        return parseOutput(weeks);

    }

}
