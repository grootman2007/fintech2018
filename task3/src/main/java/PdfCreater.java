import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.stream.Stream;


public class PdfCreater {
    public static void main(String[] args) throws IOException {

        String fileName;
        fileName = "out/sss.pdf";

        File file = new File(fileName);
        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directory is created!");
            }
        }

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            PdfPTable table = new PdfPTable(14);
            addTableHeader(table);

            int randomCount = 0;
            Random randomGenerator = new Random();
            randomCount = randomGenerator.nextInt(29) + 1;

            for (int i = 0; i < randomCount; i++) {
                PersonInfo person = new PersonInfo ();
                addRows(table, person);

            }

            document.add(table);
            document.close();

            System.out.println("Файл создан. Путь: " + file.getAbsolutePath());
        } catch (com.itextpdf.text.DocumentException e) {
            System.out.println("Error " + e.getMessage());
        }
    }


    private static void addTableHeader(PdfPTable table) {
        try{
            Font rusFont;
            BaseFont bf = BaseFont.createFont("fonts/PTS55F.ttf", BaseFont.IDENTITY_H , BaseFont.EMBEDDED);
            rusFont = new Font(bf, 14 );

            Stream.of("Имя", "Фамилия", "Отчество", "Возраст",
                        "Пол", "Дата рождения", "Инн", "Почтовый индекс",
                        "Страна", "область", "город", "улица", "дом", "квартира")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(columnTitle, rusFont));
                        table.addCell(header);
                    });

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void addRows(PdfPTable table, PersonInfo person) {
        try{
            Font rusFont;
            BaseFont bf = BaseFont.createFont("fonts/PTS55F.ttf", BaseFont.IDENTITY_H , BaseFont.EMBEDDED);
            rusFont = new Font(bf, 14 );
            table.addCell(new Phrase(person.name, rusFont));

            table.addCell(new Phrase(person.fname, rusFont));

            table.addCell(new Phrase(person.patronymic, rusFont));

            LocalDate nowDate = LocalDate.now();

            Period diff = Period.between(LocalDate.of(person.date.getYear()+1900,person.date.getMonth()+1,person.date.getDay()+1), nowDate);

            table.addCell(new Phrase(Integer.toString (diff.getYears()) , rusFont));

            table.addCell(new Phrase(person.sex, rusFont));

            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            table.addCell(new Phrase(formatter.format(person.date), rusFont));

            table.addCell(new Phrase(person.inn, rusFont));

            table.addCell(new Phrase(Integer.toString(person.index), rusFont));

            table.addCell(new Phrase(person.country, rusFont));

            table.addCell(new Phrase(person.region, rusFont));

            table.addCell(new Phrase(person.city, rusFont));

            table.addCell(new Phrase(person.street, rusFont));

            table.addCell(new Phrase(Integer.toString(person.house), rusFont));

            table.addCell(new Phrase(Integer.toString(person.apartment), rusFont));
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}
