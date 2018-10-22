import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
        System.out.println(randomCount);

        ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();
        try {
            personInfos = new ApiTestInfo().GetPersonInfo(randomCount);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            personInfos = new Mysql().GetPersonInfo(randomCount);
        }
        int count = 0;
        for (Iterator<PersonInfo> i = personInfos.iterator(); i.hasNext();){
            PersonInfo person = i.next();
            xlsxCreater.writeLineIntoExcel (person, count);
            pdfCreater.addRow(person);
            count++;
        }
        /*for (int i = 0; i < randomCount; i++) {
            PersonInfo person = new PersonInfo ();
            xlsxCreater.writeLineIntoExcel (person, i);
            pdfCreater.addRow(person);
        }*/

        xlsxCreater.saveBook();
        pdfCreater.addTable();
        pdfCreater.savePdf();
    }
}
