import base.BaseTest;
import java.util.stream.Stream;
import main.java.pages.AddPersonalLeavePage;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PersonalLeaveTests extends BaseTest {

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
        String fromDate = "3/01/2021";
        String toDate = "3/02/2021";
        AddPersonalLeavePage addPersonalLeavePage = homePage.openAddPersonalLeavePopUp();
        addPersonalLeavePage.enterFromDate(fromDate);
        addPersonalLeavePage.enterToDate(toDate);
        addPersonalLeavePage.clickAddButton();
        addPersonalLeavePage.waitUntilAddPersonalLeaveIsClosed();

        assertAll(
                () -> assertEquals("Mar 1, 2021", homePage.getFromDateByIndex(0), "From date is not correct."),
                () -> assertEquals("Mar 2, 2021", homePage.getToDateByIndex(0), "To date is not correct."),
                () -> assertEquals("2", homePage.getDaysByIndex(0), "Days value is not correct."),
                () -> assertEquals("2", homePage.getDaysOffByIndex(0), "Days Off value is not correct."),
                () -> assertEquals("Draft", homePage.getStatusByIndex(0), "The status is not correct")
        );
    }

    @Test
    void requestLeave_leaveIsRequestedSuccessfully() {

    }

    @Test
    void deleteLeave_leaveIsDeletedSuccessfully() {
        String fromDate = "3/01/2021";
        String toDate = "3/02/2021";
        AddPersonalLeavePage addPersonalLeavePage = homePage.openAddPersonalLeavePopUp();
        addPersonalLeavePage.enterFromDate(fromDate);
        addPersonalLeavePage.enterToDate(toDate);
        addPersonalLeavePage.clickAddButton();
        addPersonalLeavePage.waitUntilAddPersonalLeaveIsClosed();

        assertAll(
                () -> assertEquals("Mar 1, 2021", homePage.getFromDateByIndex(0), "From date is not correct."),
                () -> assertEquals("Mar 2, 2021", homePage.getToDateByIndex(0), "To date is not correct."),
                () -> assertEquals("2", homePage.getDaysByIndex(0), "Days value is not correct."),
                () -> assertEquals("2", homePage.getDaysOffByIndex(0), "Days Off value is not correct."),
                () -> assertEquals("Draft", homePage.getStatusByIndex(0), "The status is not correct")
        );

        homePage.deleteLeaveRecord(0);
        homePage.confirmAlert();

        assertTrue(homePage.isLeavesListEmpty(), "Leaves list is not empty.");

    }

    @Test
    void exportLeave_leaveIsExportedSuccessfully() {

    }
}
