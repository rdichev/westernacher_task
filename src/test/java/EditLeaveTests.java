import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import base.BaseTest;
import main.java.pages.AddPersonalLeavePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditLeaveTests extends BaseTest {
    private static final String LEAVE_FROM_DATE = "3/10/2021";
    private static final String LEAVE_TO_DATE = "3/12/2021";
    private static final String NEW_LEAVE_FROM_DATE = "3/20/2021";
    private static final String NEW_LEAVE_TO_DATE = "3/25/2021";
    private static final String STATUS = "Draft";
    private static final String DAYS = "6";
    private static final String DAYS_OFF = "4";
    private static final String EXPECTED_LEAVE_FROM_DATE = "Mar 20, 2021";
    private static final String EXPECTED_LEAVE_TO_DATE = "Mar 25, 2021";

    @BeforeEach
    void testSetup() {
        AddPersonalLeavePage addPersonalLeavePage = homePage.openAddPersonalLeavePopUp();
        addPersonalLeavePage.enterFromDate(LEAVE_FROM_DATE);
        addPersonalLeavePage.enterToDate(LEAVE_TO_DATE);
        addPersonalLeavePage.clickAddButton();
        addPersonalLeavePage.waitUntilAddPersonalLeaveIsClosed();

        homePage.waitUntilLeaveIsAdded();
    }

    @AfterEach
    void testCleanup() {
        homePage.deleteLeaveRecord(0);
        homePage.confirmDeletion();
    }

    @Test
    void editLeave_leaveIsSuccessfullyEdited() {
        AddPersonalLeavePage addPersonalLeavePage = homePage.editLeaveRecord(0);
        addPersonalLeavePage.enterFromDate(NEW_LEAVE_FROM_DATE);
        addPersonalLeavePage.enterToDate(NEW_LEAVE_TO_DATE);
        addPersonalLeavePage.clickAddButton();
        addPersonalLeavePage.waitUntilAddPersonalLeaveIsClosed();

        homePage.waitUntilLeaveIsAdded();

        assertAll(
                () -> assertEquals(EXPECTED_LEAVE_FROM_DATE, homePage.getFromDateByIndex(0), "From date is not correct."),
                () -> assertEquals(EXPECTED_LEAVE_TO_DATE, homePage.getToDateByIndex(0), "To date is not correct."),
                () -> assertEquals(DAYS, homePage.getDaysByIndex(0), "Days value is not correct."),
                () -> assertEquals(DAYS_OFF, homePage.getDaysOffByIndex(0), "Days Off value is not correct."),
                () -> assertEquals(STATUS, homePage.getStatusByIndex(0), "The status is not correct")
        );
    }
}
