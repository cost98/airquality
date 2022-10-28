package it.unipv.excel;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import it.unipv.domain.Country;
import it.unipv.domain.PostcodeOfStation;
import it.unipv.dto.MeasurementOnPostcodeOfStationDto;
import it.unipv.mapper.MeasurementOnPostcodeOfStationMapper;
import it.unipv.repository.CountryRepository;
import it.unipv.repository.MeasurementOnPostcodeOfStationRepository;
import it.unipv.repository.PostcodeOfStationRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.http.server.types.files.SystemFile;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class MeasurementOnPostcodeOfStationExcelServiceImpl implements MeasurementOnPostcodeOfStationExcelService {
    private static final Logger LOG = LoggerFactory.getLogger(MeasurementOnPostcodeOfStationExcelServiceImpl.class);

    private final MeasurementOnPostcodeOfStationRepository measurementOnPostcodeOfStationRepository;

    private final CountryRepository countryRepository;

    private final PostcodeOfStationRepository postcodeOfStationRepository;


    private final MeasurementOnPostcodeOfStationMapper measurementOnPostcodeOfStationMapper;

    @NonNull
    public SystemFile excelFileFromBooks(Date dateSx, Date dateDx, String country) {
        try {
            File file = File.createTempFile(HEADER_EXCEL_FILE_PREFIX, ".txt");
            Country country1 = countryRepository.findByNameLike(country);
            List<PostcodeOfStation> postcodeOfStations = postcodeOfStationRepository.findByCountryIdEqual(country1.getId());
            try(OutputStream outputStream = new FileOutputStream(file)) {
                Page<MeasurementOnPostcodeOfStationDto> measuresPage = extractPage(1,dateSx,dateDx,postcodeOfStations);
                for (int i = 1; i < measuresPage.getTotalPages(); i++) {
                    measuresPage = extractPage(1,dateSx,dateDx,postcodeOfStations);
                    for (MeasurementOnPostcodeOfStationDto measure : measuresPage.getContent()) {
                        try {
                            outputStream.write((measure.getMeasurement() + "\t" + measure.getPostcode() + "\n").getBytes());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            return new SystemFile(file).attach(HEADER_EXCEL_FILENAME);
        } catch (IOException e) {
            LOG.error("File not found exception raised when generating excel file");
        }
        throw new HttpStatusException(HttpStatus.SERVICE_UNAVAILABLE, "error generating excel file");
    }

    public Page<MeasurementOnPostcodeOfStationDto> extractPage(Integer page, Date dateSx, Date dateDx, List<PostcodeOfStation> postcodeOfStations ){
        var pageable = Pageable.from(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(50000).orElse(10));
        var data = measurementOnPostcodeOfStationRepository.findByTimestampAfterAndTimestampBeforeAndPostcodeInList(dateSx, dateDx, postcodeOfStations, pageable);
        var body = data.map(measurementOnPostcodeOfStationMapper::toDto);
        return body;
    }

}
