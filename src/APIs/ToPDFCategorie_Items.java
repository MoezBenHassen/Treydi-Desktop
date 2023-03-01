package APIs;

import Entities.Categorie_Items;
import Entities.Item;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileOutputStream;

public class ToPDFCategorie_Items {

    public void ToPDFCategorie_Items(TableView<Categorie_Items> table, String filename) {
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumns().size());
            pdfTable.setWidthPercentage(100);

            // add table headers
            for (TableColumn<Categorie_Items, ?> column : table.getColumns()) {
                PdfPCell cell = new PdfPCell();
                cell.setPhrase(new com.itextpdf.text.Paragraph(column.getText()));
                pdfTable.addCell(cell);
            }

            // add table rows
            ObservableList<Categorie_Items> categories = table.getItems();
            for (Categorie_Items categorie : categories) {
                for (TableColumn<Categorie_Items, ?> column : table.getColumns()) {
                    Object cellValue = column.getCellData(categorie);
                    PdfPCell cell = new PdfPCell();
                    cell.setPhrase(new com.itextpdf.text.Paragraph(cellValue.toString()));
                    pdfTable.addCell(cell);
                }
            }
            document.addTitle("Liste des Categories d'Items");
            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);

            Font font = new Font(BaseFont.createFont(), 20);
            paragraph.setFont(font); ;
            paragraph.add("Liste des Categories d'Items");
            Image imagea = Image.getInstance("src/GUI/Assets/images/logo.png");
            imagea.scaleAbsolute(325, 80);
            imagea.setAlignment(1);
            document.add(imagea) ;
            Paragraph paragraphb = new Paragraph();
            paragraphb.add(" ");
            document.add(paragraphb);
            document.add(paragraph) ;


            document.add(paragraphb);
            document.add(paragraphb);
            document.add(pdfTable);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}