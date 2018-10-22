
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
        name.setCellValue(person.getFName());

        Cell fname = row.createCell(cell_i);
        cell_i++;
        fname.setCellValue(person.getLName());

        Cell patronymic = row.createCell(cell_i);
        cell_i++;
        patronymic.setCellValue(person.getPatronymic());


        LocalDate nowDate = LocalDate.now();

        Period diff = Period.between(LocalDate.of(person.getDate().getYear()+1900,
                person.getDate().getMonth()+1,person.getDate().getDay()+1), nowDate);


        Cell age = row.createCell(cell_i);
        cell_i++;
        age.setCellValue(diff.getYears());


        Cell sex = row.createCell(cell_i);
        cell_i++;
        sex.setCellValue(person.getSex());

        Cell birthdate = row.createCell(cell_i);
        cell_i++;
        DataFormat format = __book.createDataFormat();
        CellStyle dateStyle = __book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd-mm-yyyy"));
        birthdate.setCellStyle(dateStyle);
        birthdate.setCellValue(person.getDate());

        Cell inn = row.createCell(cell_i);
        cell_i++;
        inn.setCellValue(person.getInn());

        Cell index = row.createCell(cell_i);
        cell_i++;
        index.setCellValue(person.getIndex());

        Cell country = row.createCell(cell_i);
        cell_i++;
        country.setCellValue(person.getCountry());

        Cell region = row.createCell(cell_i);
        cell_i++;
        region.setCellValue(person.getRegion());

        Cell city = row.createCell(cell_i);
        cell_i++;
        city.setCellValue(person.getCity());

        Cell street = row.createCell(cell_i);
        cell_i++;
        street.setCellValue(person.getStreet());

        Cell house = row.createCell(cell_i);
        cell_i++;
        house.setCellValue(person.getHouse());

        Cell apartment = row.createCell(cell_i);
        cell_i++;
        apartment.setCellValue(person.getApartment());



    }
}
