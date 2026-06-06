package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponseDTO> findAll(){
        return reservationService.getAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO findById(UUID id){
        return reservationService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO create(@Valid @RequestBody ReservationRequestDTO request){
        return reservationService.save(request);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO modify(@PathVariable UUID id, @Valid @RequestBody ReservationRequestDTO request){
        return reservationService.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        reservationService.delete(id);
    }
    @GetMapping("/vehicle")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponseDTO> findByPlate(@RequestParam String plate){
        return reservationService.findByPlate(plate);
    }

}
