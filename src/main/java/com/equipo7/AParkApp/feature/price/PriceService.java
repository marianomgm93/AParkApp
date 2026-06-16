package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeRepository;
import com.equipo7.AParkApp.feature.price.domain.PriceDTO;
import com.equipo7.AParkApp.feature.reservation.ReservationEntity;
import com.equipo7.AParkApp.feature.stay.StayType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PriceService {


    private final PriceRepository priceRepository;
    private final VehicleTypeRepository vehicleTypeRepository;


    public List<PriceDTO> findAll() {

        return priceRepository.findAll().stream().map(this::toDTO).toList();
    }


    public PriceDTO findById(UUID id) {

        PriceEntity price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));

        return toDTO(price);
    }


    public PriceDTO create(PriceDTO dto) {


        PriceEntity price = PriceEntity.builder()
                .price(dto.getPrice())
                .vehicleType(dto.getVehicleType())
                .stayType(dto.getStayType())
                .build();


        return toDTO(priceRepository.save(price));
    }


    public PriceDTO update(UUID id, PriceDTO dto) {


        PriceEntity price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));



        price.setPrice(dto.getPrice());
        price.setVehicleType(dto.getVehicleType());
        price.setStayType(dto.getStayType());


        return toDTO(priceRepository.save(price));
    }


    public void delete(UUID id) {

        PriceEntity price = priceRepository.
                findById(id).orElseThrow(() -> new EntityNotFoundException("Price not found"));


        priceRepository.delete(price);
    }


    private PriceDTO toDTO(PriceEntity price) {

        return PriceDTO.builder()
                .id(price.getId())
                .price(price.getPrice())
                .vehicleType(price.getVehicleType())
                .stayType(price.getStayType())
                .build();
    }

    public BigDecimal calculateReservationPrice(ReservationEntity reservation) {

        if (reservation.getStayType() == StayType.Hour) {
            return calculateHourlyPrice(reservation);
        }

        return calculateFixedPrice(reservation);
    }

    private BigDecimal calculateHourlyPrice(ReservationEntity reservation) {

        PriceEntity price = priceRepository
                .findByVehicleTypeAndStayType(
                        reservation.getVehicle().getVehicleType(),
                        StayType.Hour)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Price configuration not found"));

        BigDecimal hourlyRate =
                BigDecimal.valueOf(price.getPrice());

        long totalMinutes = Duration.between(
                reservation.getCheckInTime(),
                reservation.getCheckOutTime()
        ).toMinutes();

        if (totalMinutes <= 60) {
            return hourlyRate;
        }

        long extraMinutes = totalMinutes - 60;

        long blocks30 = (long) Math.ceil(
                extraMinutes / 30.0);

        return hourlyRate.add(
                hourlyRate
                        .multiply(BigDecimal.valueOf(0.5))
                        .multiply(BigDecimal.valueOf(blocks30))
        ).setScale(2, RoundingMode.HALF_UP);
    }
    private BigDecimal calculateFixedPrice(
            ReservationEntity reservation) {

        PriceEntity price = priceRepository
                .findByVehicleTypeAndStayType(
                        reservation.getVehicle().getVehicleType(),
                        reservation.getStayType())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Price configuration not found"));

        BigDecimal basePrice =
                BigDecimal.valueOf(price.getPrice());

        long totalDays = Math.max(
                1,
                Duration.between(
                        reservation.getStartTime(),
                        reservation.getEndTime()
                ).toDays()
        );

        BigDecimal multiplier;

        switch (reservation.getStayType()) {

            case Day -> multiplier =
                    BigDecimal.valueOf(totalDays);

            case Week -> multiplier =
                    BigDecimal.valueOf(totalDays)
                            .divide(
                                    BigDecimal.valueOf(7),
                                    4,
                                    RoundingMode.HALF_UP);

            case Month -> multiplier =
                    BigDecimal.valueOf(totalDays)
                            .divide(
                                    BigDecimal.valueOf(30),
                                    4,
                                    RoundingMode.HALF_UP);

            default -> throw new IllegalStateException(
                    "Invalid stay type for fixed price calculation");
        }

        return basePrice
                .multiply(multiplier)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
