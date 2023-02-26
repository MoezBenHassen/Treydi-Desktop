package APIs;

import java.io.FileOutputStream;

import Entities.Item;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ToPDFItem {

    public void ToPDFItem(TableView<Item> table, String filename) {
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumns().size());
            pdfTable.setWidthPercentage(100);

            // add table headers
            for (TableColumn<Item, ?> column : table.getColumns()) {
                PdfPCell cell = new PdfPCell();
                cell.setPhrase(new com.itextpdf.text.Paragraph(column.getText()));
                pdfTable.addCell(cell);
            }

            // add table rows
            ObservableList<Item> items = table.getItems();
            for (Item item : items) {
                for (TableColumn<Item, ?> column : table.getColumns()) {
                    Object cellValue = column.getCellData(item);
                    PdfPCell cell = new PdfPCell();
                    cell.setPhrase(new com.itextpdf.text.Paragraph(cellValue.toString()));
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}