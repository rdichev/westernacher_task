import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import base.BaseTest;
import java.util.stream.Stream;
import main.java.pages.AddPersonalLeavePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AddLeaveTests extends BaseTest {
    private static final String LEAVE_FROM_DATE = "3/10/2021";
    private static final String LEAVE_TO_DATE = "3/12/2021";
    private static final String STATUS = "Draft";
    private static final String DAYS_OFF = "3";
    private static final String EXPECTED_LEAVE_FROM_DATE = "Mar 10, 2021";
    private static final String EXPECTED_LEAVE_TO_DATE = "Mar 12, 2021";
    private static final String SUCCESSFULLY_ADDED_LEAVE_MESSAGE = "leave successfully added.";

    @AfterEach
    void testCleanup() {
        if(!homePage.isLeavesListEmpty()) {
            homePage.deleteLeaveRecord(0);
        }
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("3/3", "3/5/2021"),
                Arguments.arguments("3/02/21", "05/2021"),
                Arguments.arguments(" ", "3/05/20"),
                Arguments.arguments("3/05/20", " ")
        );
    }

    @ParameterizedTest(name = "#{index} - Test with fromDate={0}, toData={1}")
    @MethodSource("dataProvider")
    void addInvalidPersonalLeave_addButtonIsDisabled(String fromDate, String toDate) {
        AddPersonalLeavePage addPersonalLeavePage = homePage.openAddPersonalLeavePopUp();
        addPersonalLeavePage.enterFromDate(fromDate);
        addPersonalLeavePage.enterToDate(toDate);
        addPersonalLeavePage.clickAddButton();

        assertTrue(addPersonalLeavePage.isAddButtonDisabled(), "Add button is not disabled.");
    }

    @Test
    void addNewPersonalLeave_successfullyAddedToTheList() {
        AddPersonalLeavePage addPersonalLeavePage = homePage.openAddPersonalLeavePopUp();
        addPersonalLeavePage.enterFromDate(LEAVE_FROM_DATE);
        addPersonalLeavePage.enterToDate(LEAVE_TO_DATE);
        addPersonalLeavePage.clickAddButton();
        addPersonalLeavePage.waitUntilAddPersonalLeaveIsClosed();

        homePage.waitForSuccessAlertDisplayed();
        assertTrue(homePage.getSuccessAlertText().contains(SUCCESSFULLY_ADDED_LEAVE_MESSAGE));

        homePage.waitUntilLeaveIsAdded();

        assertAll(
                () -> assertEquals(EXPECTED_LEAVE_FROM_DATE, homePage.getFromDateByIndex(0), "From date is not correct."),
                () -> assertEquals(EXPECTED_LEAVE_TO_DATE, homePage.getToDateByIndex(0), "To date is not correct."),
                () -> assertEquals(DAYS_OFF, homePage.getDaysByIndex(0), "Days value is not correct."),
                () -> assertEquals(DAYS_OFF, homePage.getDaysOffByIndex(0), "Days Off value is not correct."),
                () -> assertEquals(STATUS, homePage.getStatusByIndex(0), "The status is not correct")
        );
    }

}
