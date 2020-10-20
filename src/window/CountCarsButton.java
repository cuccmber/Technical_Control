package window;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import search.CarSearch;
import search.SearchResultTable;

import java.sql.SQLException;

public class CountCarsButton {

    private Shell shell;

    public CountCarsButton(Shell shell){
        this.shell = shell;
    }

    public void overrideButton (Text fromText, Text tillText){

        String fromDate = fromText.getText();
        String tillDate = tillText.getText();

        SearchResultTable checkupInfo = new SearchResultTable(shell);
        CarSearch carSearch = new CarSearch(checkupInfo.getTable());
        checkupInfo.createTable(checkupInfo.checkupBetweenInfoTitles);

        try {
            carSearch.searchCheckup(fromDate, tillDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        checkupInfo.showTable();
    }
}
