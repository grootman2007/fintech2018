import java.io.IOException;
import java.util.Random;

public class TestFileCreater {
    public static void main(String[] args) throws IOException, com.itextpdf.text.DocumentException{
        String fileNameXlsx;
        fileNameXlsx = "out/sss.xlsx";
        XlsxCreater xlsxCreater = new XlsxCreater (fileNameXlsx);
        xlsxCreater.createSheet("mySheet");

        String fileNamePdf;
        fileNamePdf = "out/sss.pdf";
        PdfCreater pdfCreater = new PdfCreater(fileNamePdf);
        pdfCreater.createTable();
        pdfCreater.addTableHeader();

        int randomCount = 0;
        Random randomGenerator = new Random();
        randomCount = randomGenerator.nextInt(29) + 1;



        for (int i = 0; i < randomCount; i++) {
            PersonInfo person = new PersonInfo ();
            xlsxCreater.writeLineIntoExcel (person, i);
            pdfCreater.addRow(person);
        }

        xlsxCreater.saveBook();
        pdfCreater.addTable();
        pdfCreater.savePdf();
    }
}
