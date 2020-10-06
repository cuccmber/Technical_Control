package DataBase;

import View.InfoTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class SearchResultTable {

    private Shell subShell;
    private Table table;

    public static  String[] checkupBetweenInfoTitles = {"Check up Date", "Number of cars"};
    public static  String[] inspectorBetweenInfoTitles = {"Full Name", "Rank", "Car ID"};
    public static  String[] checkupHistoryInfo = {"Check up Date", "Result"};


    public SearchResultTable(Shell parent) {
        subShell = new Shell(parent);
        subShell.setLayout(new FillLayout());
        table = new Table(subShell, SWT.FULL_SELECTION);

    }

    public Table getTable() {
        return table;
    }

    public void createTable(String[] titles){

        subShell.setText("Search Result");

        table.setHeaderVisible(true);

        for (int i = 0; i < titles.length; i++) {

            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).setWidth(130);
        }
    }

    public void showTable(){
        subShell.setBounds(700, 280, 410, 300);
        subShell.open();
    }

}
