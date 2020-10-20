package window;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import search.InspectorSearch;
import search.SearchResultTable;

import java.sql.SQLException;

public class ShowInspectorButton {

    private Shell shell;

    public ShowInspectorButton(Shell shell) {
        this.shell = shell;
    }

    public void overrideButton(Text fromText, Text tillText){
        String fromDate = fromText.getText();
        String tillDate = tillText.getText();

        SearchResultTable inspectorInfo = new SearchResultTable(shell);
        InspectorSearch inspectorSearch = new InspectorSearch(inspectorInfo.getTable());
        inspectorInfo.createTable(inspectorInfo.inspectorBetweenInfoTitles);

        try {
            inspectorSearch.searchInspector(fromDate, tillDate);
        } catch (SQLException  ex) {
            ex.printStackTrace();
        }

        inspectorInfo.showTable();
    }
}
