package window;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import search.HistorySearch;
import search.SearchResultTable;

import java.sql.SQLException;

public class ShowHistory {

    private Shell shell;

    public ShowHistory(Shell shell){
        this.shell = shell;
    }

    public void overrideButton (Text engineIDText){

        String engineID = engineIDText.getText();

        SearchResultTable historyInfo = new SearchResultTable(shell);
        HistorySearch historySearch = new HistorySearch();
        historyInfo.createTable(historyInfo.checkupHistoryInfo);

        try {
            historySearch.searchHistory(historyInfo.getTable(), engineID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        historyInfo.showTable();
    }
}
