
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
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

public class ExcelHelperStudent {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "sid","birthdate","department","email", "name", "password",  "phone_number", "semester","year","sub1","sub2" };
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
            String sub1 = "",sub2="",sub3="",sub4="",sub5="",sub6="",sub7="",sub8="",sub9="",sub10="",dept="";
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
                List<Subject> subjects = new ArrayList<>();

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
                            student.setDepartment(dept=currentCell.getStringCellValue());
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
                        case 9:
                            sub1 = currentCell.getStringCellValue();
                            if(!sub1.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub1, dept),null,cellIdx,null));
                            }
                            break;
                        case 10:
                            sub2 = currentCell.getStringCellValue();
                            if(!sub2.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub2, dept),null,cellIdx,null));
                            }
                            break;
                        case 11:    
                            sub3 = currentCell.getStringCellValue();
                            if(!sub3.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub3, dept),null,cellIdx,null));
                            }
                            break;
                        case 12:        
                            sub4 = currentCell.getStringCellValue();
                            if(!sub4.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub4, dept),null,cellIdx,null));
                            }
                            break;
                        case 13:
                            sub5 = currentCell.getStringCellValue();
                            if(!sub5.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub5, dept),null,cellIdx,null));
                            }
                            break;
                        case 14:    
                            sub6 = currentCell.getStringCellValue();
                            if(!sub6.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub6, dept),null,cellIdx,null));
                            }
                            break;
                        case 15:    
                            sub7 = currentCell.getStringCellValue();
                            if(!sub7.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub7, dept),null,cellIdx,null));
                            }
                            break;
                        case 16:    
                            sub8 = currentCell.getStringCellValue();
                            if(!sub8.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub8, dept),null,cellIdx,null));
                            }
                            break;
                        case 17:    
                            sub9 = currentCell.getStringCellValue();
                            if(!sub9.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub9, dept),null,cellIdx,null));
                            }
                            break;
                        case 18:    
                            sub10 = currentCell.getStringCellValue();
                            if(!sub10.isEmpty()){
                                subjects.add(new Subject(new Subject_ID(sub10, dept),null,cellIdx,null));
                            };
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                student.setSubjects(subjects);
                students.add(student);

                students.add(student);
            }

            workbook.close();

            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
