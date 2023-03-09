package APIs;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import Entities.Categorie_Items;
import Entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import jxl.write.WritableSheet;

public class ToXLS {

    TableColumn<Item, ImageView> imageColumn;

    public void ToXLSItem(TableView<Item> tableView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items XLS");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            try {


                // Create a new workbook
                WorkbookSettings ws = new WorkbookSettings();
                ws.setLocale(new Locale("en", "EN"));
                WritableWorkbook workbook = Workbook.createWorkbook(selectedFile, ws);

                // Create a new sheet
                WritableSheet sheet = workbook.createSheet("UserItems", 0);

                // Get the table columns
                ObservableList<TableColumn<Item, ?>> columns = tableView.getColumns();

                // Write the column headers to the first row of the sheet
                int columnCount = columns.size() - 1;
                for (int i = 0; i < columnCount; i++) {
                    String columnHeader = columns.get(i).getText();
                    Label label = new Label(i, 0, columnHeader);
                    sheet.addCell(label);
                }

                // Write the data rows to the sheet
                ObservableList<Item> data = tableView.getItems();
                int rowCount = data.size();
                Item rowData = null;
                for (int i = 0; i < rowCount; i++) {
                    rowData = data.get(i);

                    for (int j = 0; j < columnCount; j++) {

                        TableColumn<Item, ?> column = columns.get(j);
                        Object cellValue = column.getCellData(rowData);
                        Label label = new Label(j, i + 1, cellValue.toString());
                        sheet.addCell(label);

                    }
                }

                sheet.removeColumn(1);

                // Write the workbook to disk
                workbook.write();
                workbook.close();

            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }
    }

    public void ToXLSCategorie_Items(TableView<Categorie_Items> tableView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items XLS");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        WritableWorkbook workbook;
        if (selectedFile != null) {
            try {


                // Create a new workbook
                WorkbookSettings ws = new WorkbookSettings();
                ws.setLocale(new Locale("en", "EN"));
                workbook = Workbook.createWorkbook(selectedFile, ws);

                // Create a new sheet
                WritableSheet sheet = workbook.createSheet("UserItems", 0);

                // Get the table columns
                ObservableList<TableColumn<Categorie_Items, ?>> columns = tableView.getColumns();

                // Write the column headers to the first row of the sheet
                int columnCount = columns.size();
                for (int i = 0; i < columnCount; i++) {
                    String columnHeader = columns.get(i).getText();
                    Label label = new Label(i, 0, columnHeader);
                    sheet.addCell(label);
                }

                // Write the data rows to the sheet
                ObservableList<Categorie_Items> data = tableView.getItems();
                int rowCount = data.size();
                Categorie_Items rowData = null;
                for (int i = 0; i < rowCount; i++) {
                    rowData = data.get(i);

                    for (int j = 0; j < columnCount; j++) {

                        TableColumn<Categorie_Items, ?> column = columns.get(j);
                        Object cellValue = column.getCellData(rowData);
                        Label label = new Label(j, i + 1, cellValue.toString());
                        sheet.addCell(label);

                    }
                }


                // Write the workbook to disk
                workbook.write();
                workbook.close();
            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }

    }


}
