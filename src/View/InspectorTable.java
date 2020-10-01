package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class InspectorTable {

    public void drawTable(Table table){
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
    }

}
