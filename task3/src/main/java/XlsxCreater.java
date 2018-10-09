
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;



public class XlsxCreater {
    private String __fileName;
    private Workbook __book;
    private Sheet __sheet;

    public XlsxCreater(String fileName){
        __fileName = fileName;
        __book = new HSSFWorkbook();
    }

    public void createSheet(String sheetName){
        __sheet = __book.createSheet(sheetName);
    }

    public void saveBook() throws IOException {
        File file = new File(__fileName);
        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directory is created!");
            }
        }
        for (int i = 0; i < 13; i++) {
            __sheet.autoSizeColumn(i);
        }

        __book.write(new FileOutputStream(__fileName));
        __book.close();

        System.out.println("Файл создан. Путь: " + file.getAbsolutePath());

    }


    public void writeLineIntoExcel(PersonInfo person, int rowIterator) throws FileNotFoundException, IOException{
        int cell_i;
        Row row = __sheet.createRow(rowIterator);

        cell_i = 0;

        Cell name = row.createCell(cell_i);
        cell_i++;
        name.setCellValue(person.name);

        Cell fname = row.createCell(cell_i);
        cell_i++;
        fname.setCellValue(person.fname);

        Cell patronymic = row.createCell(cell_i);
        cell_i++;
        patronymic.setCellValue(person.patronymic);


        LocalDate nowDate = LocalDate.now();

        Period diff = Period.between(LocalDate.of(person.date.getYear()+1900,
                person.date.getMonth()+1,person.date.getDay()+1), nowDate);


        Cell age = row.createCell(cell_i);
        cell_i++;
        age.setCellValue(diff.getYears());


        Cell sex = row.createCell(cell_i);
        cell_i++;
        sex.setCellValue(person.sex);

        Cell birthdate = row.createCell(cell_i);
        cell_i++;
        DataFormat format = __book.createDataFormat();
        CellStyle dateStyle = __book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd-mm-yyyy"));
        birthdate.setCellStyle(dateStyle);
        birthdate.setCellValue(person.date);

        Cell inn = row.createCell(cell_i);
        cell_i++;
        inn.setCellValue(person.inn);

        Cell index = row.createCell(cell_i);
        cell_i++;
        index.setCellValue(person.index);

        Cell country = row.createCell(cell_i);
        cell_i++;
        country.setCellValue(person.country);

        Cell region = row.createCell(cell_i);
        cell_i++;
        region.setCellValue(person.region);

        Cell city = row.createCell(cell_i);
        cell_i++;
        city.setCellValue(person.city);

        Cell street = row.createCell(cell_i);
        cell_i++;
        street.setCellValue(person.street);

        Cell house = row.createCell(cell_i);
        cell_i++;
        house.setCellValue(person.house);

        Cell apartment = row.createCell(cell_i);
        cell_i++;
        apartment.setCellValue(person.apartment);



    }
}
