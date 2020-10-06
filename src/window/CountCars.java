package window;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import search.CheckupSearch;
import search.SearchResultTable;

import java.sql.SQLException;

public class CountCars {

    private Shell shell;

    public CountCars(Shell shell){
        this.shell = shell;
    }

    public void overrideButton (Text fromText, Text tillText){

        String fromDate = fromText.getText();
        String tillDate = tillText.getText();

        SearchResultTable checkupInfo = new SearchResultTable(shell);
        CheckupSearch checkupSearch = new CheckupSearch();
        checkupInfo.createTable(checkupInfo.checkupBetweenInfoTitles);

        try {
            checkupSearch.searchCheckup(checkupInfo.getTable(), fromDate, tillDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        checkupInfo.showTable();
    }
}
