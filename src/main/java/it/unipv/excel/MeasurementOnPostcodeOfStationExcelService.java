package it.unipv.excel;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.context.annotation.DefaultImplementation;
import io.micronaut.http.server.types.files.SystemFile;

import java.util.Date;

@DefaultImplementation(MeasurementOnPostcodeOfStationExcelServiceImpl.class)
public interface MeasurementOnPostcodeOfStationExcelService {
    String SHEET_NAME = "Books";
    String HEADER_ISBN = "Isbn";
    String HEADER_NAME = "Name";
    String HEADER_EXCEL_FILE_SUFIX = ".xlsx";
    String HEADER_EXCEL_FILE_PREFIX = "books";
    String HEADER_EXCEL_FILENAME = HEADER_EXCEL_FILE_PREFIX + HEADER_EXCEL_FILE_SUFIX;

    @NonNull
    SystemFile excelFileFromBooks(Date dateSx, Date dateDx, String country);
}
