package com.example.CommunityZonesPSF.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CommunityZonesPSF.modelos.Usuario;

@Repository
public interface IRepositorioUsuario extends JpaRepository<Usuario, UUID> {
}