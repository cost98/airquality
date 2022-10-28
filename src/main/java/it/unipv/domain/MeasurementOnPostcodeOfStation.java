package it.unipv.domain;



import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "data.measurement_on_postcode_of_station")
public class MeasurementOnPostcodeOfStation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "measurement", nullable = false)
    private Double measurement;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postcode", nullable = false)
    private PostcodeOfStation postcode;

    @NotNull
    @Column(name = "\"timestamp\"", nullable = false)
    private Date timestamp;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "param", nullable = false)
    private Param param;

}
