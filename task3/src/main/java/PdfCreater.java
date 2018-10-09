import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Stream;


public class PdfCreater {
    private String __fileName;
    private Document __document;
    private PdfPTable __table;
    private Font __rusFont;

    public PdfCreater (String fileName) throws IOException, com.itextpdf.text.DocumentException {
        __fileName = fileName;
        __document = new Document();

        File file = new File(__fileName);
        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directory is created!");
            }
        }

        PdfWriter.getInstance(__document, new FileOutputStream(__fileName));

        __document.open();

        BaseFont bf = BaseFont.createFont("fonts/PTS55F.ttf", BaseFont.IDENTITY_H , BaseFont.EMBEDDED);
        __rusFont = new Font(bf, 5 );

    }

    public void createTable (){
        __table = new PdfPTable(14);

    }

    public void addTable ()throws com.itextpdf.text.DocumentException{
        __document.add(__table);
    }

    public void savePdf() {
        __document.close();
        File file = new File(__fileName);

        System.out.println("Файл создан. Путь: " + file.getAbsolutePath());

    }


    public void addTableHeader() {
        Stream.of("Имя", "Фамилия", "Отчество", "Возраст",
                    "Пол", "Дата рождения", "Инн", "Почтовый индекс",
                    "Страна", "область", "город", "улица", "дом", "квартира")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, __rusFont));
                    __table.addCell(header);
                });

    }

    public void addRow (PersonInfo person) {
        __table.addCell(new Phrase(person.name, __rusFont));

        __table.addCell(new Phrase(person.fname, __rusFont));

        __table.addCell(new Phrase(person.patronymic, __rusFont));

        LocalDate nowDate = LocalDate.now();

        Period diff = Period.between(LocalDate.of(person.date.getYear()+1900,
                person.date.getMonth()+1,person.date.getDay()+1), nowDate);

        __table.addCell(new Phrase(Integer.toString (diff.getYears()) , __rusFont));

        __table.addCell(new Phrase(person.sex, __rusFont));

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        __table.addCell(new Phrase(formatter.format(person.date), __rusFont));

        __table.addCell(new Phrase(person.inn, __rusFont));

        __table.addCell(new Phrase(Integer.toString(person.index), __rusFont));

        __table.addCell(new Phrase(person.country, __rusFont));

        __table.addCell(new Phrase(person.region, __rusFont));

        __table.addCell(new Phrase(person.city, __rusFont));

        __table.addCell(new Phrase(person.street, __rusFont));

        __table.addCell(new Phrase(Integer.toString(person.house), __rusFont));

        __table.addCell(new Phrase(Integer.toString(person.apartment), __rusFont));

    }

}
