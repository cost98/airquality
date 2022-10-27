package it.unipv.airqualityretrospective;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.convert.format.Format;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.types.files.SystemFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unipv.airqualityretrospective.domain.Country;
import it.unipv.airqualityretrospective.dto.CountryDto;
import it.unipv.airqualityretrospective.dto.MeasurementOnPostcodeOfStationDto;
import it.unipv.airqualityretrospective.dto.StationOnPostcodeDto;
import it.unipv.airqualityretrospective.excel.MeasurementOnPostcodeOfStationExcelService;
import it.unipv.airqualityretrospective.mapper.CountryMapper;
import it.unipv.airqualityretrospective.mapper.MeasurementOnPostcodeOfStationMapper;
import it.unipv.airqualityretrospective.mapper.StationOnPostcodeMapper;
import it.unipv.airqualityretrospective.repository.CountryRepository;
import it.unipv.airqualityretrospective.repository.MeasurementOnPostcodeOfStationRepository;
import it.unipv.airqualityretrospective.repository.StationOnPostcodeRepository;
import it.unipv.airqualityretrospective.specifications.CountrySpecifications;
import it.unipv.airqualityretrospective.specifications.MeasurementOnPostcodeOfStationSpecifications;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import javax.validation.constraints.Pattern;


import java.util.Date;
import java.util.List;



import java.util.Optional;
import java.util.stream.Collectors;

import static io.micronaut.http.HttpResponse.ok;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
@Tag(name = "Airquality", description = "Everything about airquality retrospective")
@Controller
public class AirqualityController {

    private final CountryRepository countryRepository;
    private final MeasurementOnPostcodeOfStationRepository measurementOnPostcodeOfStationRepository;

    private final StationOnPostcodeRepository stationOnPostcodeRepository;

    private final CountryMapper countryMapper;

    private final MeasurementOnPostcodeOfStationMapper measurementOnPostcodeOfStationMapper;

    private final MeasurementOnPostcodeOfStationExcelService measurementOnPostcodeOfStationExcelService;


    private final StationOnPostcodeMapper stationOnPostcodeMapper;

    @Get(uri="/api/country",produces = MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "200", description = "Clients found")
    @ApiResponse(responseCode = "400", description = "Error in input")
    @Operation(operationId = "getCountry", summary = "Get country", description = "Country")
    @Transactional
    public HttpResponse<Page<CountryDto>> getCountry(@Nullable @QueryValue() String q,
                                                     @Nullable @QueryValue(defaultValue = "0") Integer page,
                                                     @Nullable @QueryValue(defaultValue = "10") Integer size,
                                                     @Nullable @Pattern(regexp = "name|description") @QueryValue(defaultValue = "name") String sort,
                                                     @Nullable @Pattern(regexp = "asc|desc|ASC|DESC") @QueryValue(defaultValue = "asc") String order) {
        var pageable = Pageable.from(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(size).orElse(10),
                Sort.of(
                        new Sort.Order(
                                Optional.ofNullable(sort).orElse("name"),
                                Sort.Order.Direction.valueOf(Optional.ofNullable(order).orElse("asc").toUpperCase()),
                                true)));
        var data = countryRepository.findAll(CountrySpecifications.filterByKeyword(q),pageable);
        var body = data.map(countryMapper::toDto);
        return ok(body);
    }




    @Get(uri="/api/measurement",produces = MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "200", description = "Clients found")
    @ApiResponse(responseCode = "400", description = "Error in input")
    @Operation(operationId = "getMeasurement", summary = "Get measurements", description = "Measurement")
    @Transactional
    public HttpResponse<Page<MeasurementOnPostcodeOfStationDto>> getMeasurement(@Nullable @QueryValue(defaultValue = "0") Integer page,
                                                                                @Nullable @QueryValue(defaultValue = "10") Integer size,
                                                                                @Nullable @Pattern(regexp = "timestamp") @QueryValue(defaultValue = "timestamp") String sort,
                                                                                @Nullable @Pattern(regexp = "asc|desc|ASC|DESC") @QueryValue(defaultValue = "asc") String order) {
        var pageable = Pageable.from(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(size).orElse(10)
        );
        var data = measurementOnPostcodeOfStationRepository.findAll(pageable);
        var body = data.map(measurementOnPostcodeOfStationMapper::toDto);
        return ok(body);
    }

    @Get(uri="/api/station",produces = MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "200", description = "Stations found")
    @ApiResponse(responseCode = "400", description = "Error in input")
    @Operation(operationId = "getStations", summary = "Get stations", description = "Station")
    @Transactional
    public HttpResponse<List<StationOnPostcodeDto>> getStation(){
        return HttpResponse.ok(stationOnPostcodeRepository.findAll().stream().map(stationOnPostcodeMapper::toDto).collect(Collectors.toList()));
    }


    @Produces(value = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @Get("/api/excel")
    @Transactional
    public SystemFile excel(@Format("yyyy-MM-dd") Date dateSx, @Format("yyyy-MM-dd") Date dateDx, String country) {
        return measurementOnPostcodeOfStationExcelService.excelFileFromBooks(dateSx, dateDx, country);
    }
}