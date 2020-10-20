package window;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import search.CheckupSearch;
import search.SearchResultTable;

import java.sql.SQLException;

public class ShowHistoryButton {

    private Shell shell;

    public ShowHistoryButton(Shell shell){
        this.shell = shell;
    }

    public void overrideButton (Text engineIDText){

        String engineID = engineIDText.getText();

        SearchResultTable historyInfo = new SearchResultTable(shell);
        CheckupSearch checkupSearch = new CheckupSearch(historyInfo.getTable());
        historyInfo.createTable(historyInfo.checkupHistoryInfo);

        try {
            checkupSearch.searchHistory(engineID);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        historyInfo.showTable();
    }
}
