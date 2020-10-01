package View;

import Model.DataBase;
import Model.Query;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import java.sql.SQLException;

public class InspectorTable {

    Shell subShell;
    Table table;

    public InspectorTable(Shell shell){
        subShell = new Shell(shell);
        subShell.setLayout(new FillLayout());
        table = new Table(subShell, SWT.NONE);
    }

    public Table getTable() {
        return table;
    }

    public void drawTable(){
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        gridLayout.marginTop = 10;
        gridLayout.marginBottom = 10;
        gridLayout.marginLeft = 5;
        gridLayout.marginRight = 5;
        table.setLayout(gridLayout);

        table.setHeaderVisible(true);
        String[] titles = {"Full Name", "Inspector ID", "Post", "Inspector Rank"};

        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }

        subShell.open();
    }

    public void updateTable() throws SQLException {
        DataBase db = new DataBase();
        Query.printAllInspectors(db.createQuery(Query.showAllInspectors), table);

    }

}
