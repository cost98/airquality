package it.unipv.airqualityretrospective.excel;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import it.unipv.airqualityretrospective.domain.MeasurementOnPostcodeOfStation;
import it.unipv.airqualityretrospective.dto.MeasurementOnPostcodeOfStationDto;
import it.unipv.airqualityretrospective.mapper.MeasurementOnPostcodeOfStationMapper;
import it.unipv.airqualityretrospective.repository.MeasurementOnPostcodeOfStationRepository;
import it.unipv.airqualityretrospective.specifications.CountrySpecifications;
import it.unipv.airqualityretrospective.specifications.MeasurementOnPostcodeOfStationSpecifications;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import builders.dsl.spreadsheet.builder.poi.PoiSpreadsheetBuilder;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.http.server.types.files.SystemFile;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class MeasurementOnPostcodeOfStationExcelServiceImpl implements MeasurementOnPostcodeOfStationExcelService {
    private static final Logger LOG = LoggerFactory.getLogger(MeasurementOnPostcodeOfStationExcelServiceImpl.class);

    private final MeasurementOnPostcodeOfStationRepository measurementOnPostcodeOfStationRepository;

    private final MeasurementOnPostcodeOfStationMapper measurementOnPostcodeOfStationMapper;

    @NonNull
    public SystemFile excelFileFromBooks(Date dateSx, Date dateDx, String country) {
        try {
            File file = File.createTempFile(HEADER_EXCEL_FILE_PREFIX, ".txt");
           try(OutputStream outputStream = new FileOutputStream(file)) {

               Page<MeasurementOnPostcodeOfStationDto> measuresPage = extractPage(1);
               for (int i = 1; i < measuresPage.getTotalPages(); i++) {
                   for (MeasurementOnPostcodeOfStationDto measure : measuresPage.getContent()) {
                       if (measure.getTimestamp().after(dateSx) && measure.getTimestamp().before(dateDx) && measure.getCountry().equals(country)) {
                           try {
                               outputStream.write((measure.getMeasurement() + "\t" + measure.getPostcode() + "\n").getBytes());
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           }
                       }
                   }
               }

               return new SystemFile(file).attach(HEADER_EXCEL_FILENAME);
           }
        } catch (IOException e) {
            LOG.error("File not found exception raised when generating excel file");
        }
        throw new HttpStatusException(HttpStatus.SERVICE_UNAVAILABLE, "error generating excel file");
    }

    public Page<MeasurementOnPostcodeOfStationDto> extractPage(Integer page){
        var pageable = Pageable.from(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(10).orElse(10),
                Sort.of(
                        new Sort.Order(
                                "timestamp",
                                Sort.Order.Direction.valueOf("ASC"),
                                true)));
        var data = measurementOnPostcodeOfStationRepository.findAll(MeasurementOnPostcodeOfStationSpecifications.filterByKeyword(""),pageable);
        var body = data.map(measurementOnPostcodeOfStationMapper::toDto);
        return body;
}

}
