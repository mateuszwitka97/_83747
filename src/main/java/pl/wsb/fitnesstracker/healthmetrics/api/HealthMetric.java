package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "user")
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    // FK -> users.id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    // waga w kg (np. 72.3); BigDecimal dla dokładności
    @Column(name = "weight", nullable = false, precision = 6, scale = 2)
    private BigDecimal weight;

    // wzrost w cm (np. 180)
    @Column(name = "height", nullable = false)
    private Integer height;

    // tętno spoczynkowe / z pomiaru (bpm)
    @Column(name = "heartRate", nullable = false)
    private Integer heartRate;

    public HealthMetric(
            final User user,
            final LocalDate date,
            final BigDecimal weight,
            final Integer height,
            final Integer heartRate
    ) {
        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }
}