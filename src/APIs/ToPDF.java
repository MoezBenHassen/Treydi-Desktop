package APIs;

import java.io.File;
import java.io.FileOutputStream;

import Entities.Categorie_Items;
import Entities.Item;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import javafx.scene.control.TableView;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ToPDF {

    public ToPDF() {
    }

    public void ToPDFItem(TableView<Item> table) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items PDF");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {


            try {
                Document document = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                PdfPTable pdfTable = new PdfPTable(table.getColumns().size() - 1);
                pdfTable.setWidthPercentage(100);

                // add table headers
                for (TableColumn<Item, ?> column : table.getColumns()) {
                    if (column.getId().equals("imageurlColumn")) {


                    } else {
                        PdfPCell cell = new PdfPCell();
                        cell.setPhrase(new com.itextpdf.text.Paragraph(column.getText()));
                        pdfTable.addCell(cell);
                    }
                }

                // add table rows
                ObservableList<Item> items = table.getItems();
                for (Item item : items) {
                    for (TableColumn<Item, ?> column : table.getColumns()) {
                        Object cellValue = column.getCellData(item);
                        PdfPCell cell = new PdfPCell();
                        if (column.getId().equals("imageurlColumn")) {
                            Image image = Image.getInstance(cellValue.toString());
                            cell.setImage(image);
                            pdfTable.addCell(cell);
                        } else if (column.getId().equals("imageColumn")) {
                        } else {
                            cell.setPhrase(new com.itextpdf.text.Paragraph(cellValue.toString()));
                            pdfTable.addCell(cell);
                        }
                    }
                }

                document.addTitle("Liste des Items");
                Paragraph paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_CENTER);

                Font font = new Font(BaseFont.createFont(), 20);
                paragraph.setFont(font);
                ;
                paragraph.add("Liste des Items");
                Image imagea = Image.getInstance("src/GUI/Assets/images/logo.png");
                imagea.scaleAbsolute(325, 80);
                imagea.setAlignment(1);
                document.add(imagea);
                Paragraph paragraphb = new Paragraph();
                paragraphb.add(" ");
                document.add(paragraphb);
                document.add(paragraph);


                document.add(paragraphb);
                document.add(paragraphb);
                document.add(pdfTable);
                document.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ToPDFCategorie_Items(TableView<Categorie_Items> table) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items PDF");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            try {


                Document document = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
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
                paragraph.setFont(font);
                ;
                paragraph.add("Liste des Categories d'Items");
                Image imagea = Image.getInstance("src/GUI/Assets/images/logo.png");
                imagea.scaleAbsolute(325, 80);
                imagea.setAlignment(1);
                document.add(imagea);
                Paragraph paragraphb = new Paragraph();
                paragraphb.add(" ");
                document.add(paragraphb);
                document.add(paragraph);


                document.add(paragraphb);
                document.add(paragraphb);
                document.add(pdfTable);
                document.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}