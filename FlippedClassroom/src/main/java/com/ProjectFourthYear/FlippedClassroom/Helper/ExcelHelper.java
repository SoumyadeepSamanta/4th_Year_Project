
package com.ProjectFourthYear.FlippedClassroom.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// import org.apache.poi.ss.usermodel.Sheet.Iterator();
import java.time.ZoneId;

// import org.apache.poi.hpsf.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.ProjectFourthYear.FlippedClassroom.student.Student;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "sid","birthdate","department","email", "name", "password",  "phone_number", "semester","year" };
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Student> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Student> students = new ArrayList<Student>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Student student = new Student();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            student.setSid(currentCell.getStringCellValue());
                            break;
                        
                        case 1:
                            LocalDate dateStr = currentCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            // LocalDate birthdate = LocalDate.parse(dateStr); // Parse the string to LocalDate
                            // java.util.Date dateValue = currentCell.getDateCellValue();
                            student.setBirthdate(dateStr);
                            break;

                        case 2:
                            student.setDepartment(currentCell.getStringCellValue());
                            break;

                        case 3:
                            student.setEmail(currentCell.getStringCellValue());
                            break;

                        case 4:
                            student.setName(currentCell.getStringCellValue());
                            break;
                        
                            case 5:
                            long phoneNumber = (long)(currentCell.getNumericCellValue());
                            student.setPhone_number(phoneNumber);
                            break;

                        case 6:
                            student.setPassword(currentCell.getStringCellValue());
                            break;
    
                        case 7:
                            int Semester = (int)currentCell.getNumericCellValue();
                            student.setSemester(Semester);
                            break;

                        case 8:
                            int Year = (int)currentCell.getNumericCellValue();
                            student.setYear(Year);
                            break;                        
                            
                        default:
                            break;
                    }

                    cellIdx++;
                }

                students.add(student);
            }

            workbook.close();

            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
