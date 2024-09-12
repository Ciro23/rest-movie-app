package it.tino.restmovieapp.export;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tino.restmovieapp.error.MovieAppException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;

public class XlsxGenerator {

    /**
     * Converts some objects to a ".xlsx" file.<br>
     * To exclude a field from being exported, for example because of security
     * reasons, it must be annotated with
     * <code>@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)</code>
     * @param beans The objects which are converted to a list of rows, where
     *              the columns are the object attributes, using reflection.
     * @param sheetTitle The title of the only sheet in the generated xlsx file.
     * @return The xlsx file converted to an array of bytes.
     */
    public static <T> byte[] generateXlsx(Collection<T> beans, String sheetTitle) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetTitle);

        if (beans == null || beans.isEmpty()) {
            throw new IllegalArgumentException("Cannot generate a xlsx file without any data.");
        }

        Class<?> classType = beans.iterator().next().getClass();
        Field[] fields = classType.getDeclaredFields();
        createHeader(sheet, fields);
        createRows(sheet, beans, fields);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return out.toByteArray();
        } catch (IOException e) {
            throw new MovieAppException(e);
        }
    }

    private static void createHeader(Sheet sheet, Field[] fields) {
        Row header = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (isWriteOnly(field)) {
                continue;
            }

            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(field.getName());
        }
    }

    private static <T> void createRows(Sheet sheet, Collection<T> beans, Field[] fields) {
        int i = 0;
        for (T bean : beans) {
            Row dataRow = sheet.createRow(i + 1);

            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                if (isWriteOnly(field)) {
                    continue;
                }

                field.setAccessible(true);

                Object value;
                try {
                    value = field.get(bean);
                } catch (IllegalAccessException e) {
                    throw new MovieAppException(e);
                }
                Cell cell = dataRow.createCell(j);
                setValueToCell(cell, value);
            }
            i++;
        }
    }

    private static boolean isWriteOnly(Field field) {
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
        return jsonProperty != null && jsonProperty.access() == JsonProperty.Access.WRITE_ONLY;
    }

    private static void setValueToCell(Cell cell, Object value) {
        if (value instanceof String) {
            cell.setCellValue((String) value);
            return;
        }

        if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
            return;
        }

        if (value != null) {
            cell.setCellValue(value.toString());
        }
    }
}
