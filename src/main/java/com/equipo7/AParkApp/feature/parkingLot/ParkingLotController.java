package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/ParkingLot")
public class ParkingLotController {

    @Autowired
   private IParkingLotService parkingLotService;


    @PostMapping
  public ResponseEntity<ParkingLotResponse> create(@RequestBody ParkingLotRequest request) {

        ParkingLotResponse parkingLotResponse = parkingLotService.create(request);


        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingLotResponse);
    }

    @GetMapping
    public ResponseEntity<List<ParkingLotResponse>> getAll() {



        return ResponseEntity.ok(parkingLotService.getAllParkingLots());
    }


    @GetMapping
    public ResponseEntity<List<ParkingLotResponse>> getAllActiveTrue() {


        return ResponseEntity.ok(parkingLotService.getAllActiveParkingLots());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLotResponse> update(@PathVariable UUID id,@RequestBody ParkingLotRequest request) {


        return ResponseEntity.ok(parkingLotService.update(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

    parkingLotService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        parkingLotService.restore(id);

        return  ResponseEntity.ok().build();
    }





}
