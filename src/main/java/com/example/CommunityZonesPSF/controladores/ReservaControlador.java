package com.example.CommunityZonesPSF.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CommunityZonesPSF.dtos.ReservaDTO;
import com.example.CommunityZonesPSF.modelos.Reserva;
import com.example.CommunityZonesPSF.servicios.IReservaServicio;

@RestController
@RequestMapping("/paseoapi/v1/reservas")
public class ReservaControlador {

    private final IReservaServicio servicio;

    public ReservaControlador(IReservaServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> controlarGuardado(@RequestBody Reserva datos) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarReservaEnBD(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> controlarModificado(@RequestBody Reserva datos, @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificarReservaEnBD(datos, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> controlarBorrado(@PathVariable UUID id) {
        this.servicio.eliminarReservaEnBD(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> controlarListado() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservasEnBD());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> controlarBusquedaPorId(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservaPorId(id));
    }

    // Reservas de un usuario ("mis reservas")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservaDTO>> controlarListadoPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservasPorUsuario(usuarioId));
    }

    // Reservas de un espacio (para ver qué horas están ocupadas)
    @GetMapping("/espacio/{espacioId}")
    public ResponseEntity<List<ReservaDTO>> controlarListadoPorEspacio(@PathVariable UUID espacioId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservasPorEspacio(espacioId));
    }
}
