import base.BaseTest;
import main.java.pages.AddPersonalLeavePage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteLeaveTests extends BaseTest {
    private static final String LEAVE_FROM_DATE = "3/10/2021";
    private static final String LEAVE_TO_DATE = "3/12/2021";

    @BeforeEach
    void testSetup() {
        AddPersonalLeavePage addPersonalLeavePage = homePage.openAddPersonalLeavePopUp();
        addPersonalLeavePage.enterFromDate(LEAVE_FROM_DATE);
        addPersonalLeavePage.enterToDate(LEAVE_TO_DATE);
        addPersonalLeavePage.clickAddButton();
        addPersonalLeavePage.waitUntilAddPersonalLeaveIsClosed();

        homePage.waitUntilLeaveIsAdded();
    }

    @Test
    void deleteLeave_leaveIsDeletedSuccessfully() {
        homePage.deleteLeaveRecord(0);
        homePage.confirmDeletion();

        assertTrue(homePage.isLeavesListEmpty(), "Leaves list is not empty.");
    }
}
