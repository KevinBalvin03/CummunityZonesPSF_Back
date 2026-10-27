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

import com.example.CommunityZonesPSF.dtos.EspacioDTO;
import com.example.CommunityZonesPSF.modelos.Espacio;
import com.example.CommunityZonesPSF.servicios.IEspacioServicio;

@RestController
@RequestMapping("/paseoapi/v1/espacios")
public class EspacioControlador {

    private final IEspacioServicio servicio;

    public EspacioControlador(IEspacioServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<EspacioDTO> controlarGuardado(@RequestBody Espacio datos) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarEspacioEnBD(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspacioDTO> controlarModificado(@RequestBody Espacio datos, @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificarEspacioEnBD(datos, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> controlarBorrado(@PathVariable UUID id) {
        this.servicio.eliminarEspacioEnBD(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping
    public ResponseEntity<List<EspacioDTO>> controlarListado() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarEspaciosEnBD());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacioDTO> controlarBusquedaPorId(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarEspacioPorId(id));
    }
}
