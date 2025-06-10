package com.ProjectFourthYear.FlippedClassroom.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ProjectFourthYear.FlippedClassroom.teacher.Teacher;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject;
import com.ProjectFourthYear.FlippedClassroom.subjects.Subject_ID;

public class ExcelHelperTeacher {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "tid", "birthdate", "department", "email", "name", "phone_number", "password", "sub1", "dep1", "sub2", "dep2" };
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Teacher> excelToTeachers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Teacher> teachers = new ArrayList<>();
            int rowNumber = 0;
            String sub1 = "", dep1 = "", sub2 = "", dep2 = "";
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Teacher teacher = new Teacher();
                List<Subject> subjects = new ArrayList<>();
                

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            teacher.setTid(currentCell.getStringCellValue());
                            break;
                        case 1:
                            LocalDate dateStr = currentCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            teacher.setBirthdate(dateStr);
                            break;
                        case 2:
                            teacher.setDepartment(currentCell.getStringCellValue());
                            break;
                        case 3:
                            teacher.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            teacher.setName(currentCell.getStringCellValue());
                            break;
                        case 5:
                            long phoneNumber = (long) currentCell.getNumericCellValue();
                            teacher.setPhone_number(phoneNumber);
                            break;
                        case 6:
                            teacher.setPassword(currentCell.getStringCellValue());
                            break;
                        case 7:
                            sub1 = currentCell.getStringCellValue();
                            break;
                        case 8:
                            dep1 = currentCell.getStringCellValue();
                            if (!sub1.isEmpty() && !dep1.isEmpty()) {
                                subjects.add(new Subject(new Subject_ID(sub1, dep1), null, cellIdx, null)); // Correct way to add a Subject
                            }
                            break;
                        case 9:
                            sub2 = currentCell.getStringCellValue();
                            break;
                        case 10:
                            dep2 = currentCell.getStringCellValue();
                            if (!sub2.isEmpty() && !dep2.isEmpty()) {
                                subjects.add(new Subject(new Subject_ID(sub2, dep2), null, cellIdx, null)); // Correct way to add a Subject
                            }
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }

                // Assign subjects list to the teacher
                teacher.setSubjects(subjects);
                teachers.add(teacher);
            }

            workbook.close();
            return teachers;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}
