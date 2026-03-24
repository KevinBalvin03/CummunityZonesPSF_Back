package com.example.CommunityZonesPSF.servicios;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CommunityZonesPSF.modelos.Usuario;
import com.example.CommunityZonesPSF.repositories.IRepositorioUsuario;

@Service
public class UsuarioServicio {

    //Inyectando una dependencia
    @Autowired
    private IRepositorioUsuario repositorioUsuario; 

    public boolean guardarUsuarioBD(Usuario datos){
        //Validar que datos me envian y si estos cumplen las reglas del negocio
        //Guardar los datos en BD con ayuda del repositorio
    }

    public boolean modificarUsuarioBD(Usuario datos, UUID id){
        //Validar los datos que me envian y si estos cumplen las reglas del negocio
        //Modificar los datos en BD con ayuda del repositorio
        
        return false;
    }

    public boolean eliminarUsuarioBD(UUID id){
        //Buscar y vlaidar si el ID que envian existe
        //Elimino el registro en BD
    }

    public boolean buscarUsuariosBD(){
        //***Dependiendo del parametro de busqueda debo implementar validaciones
        // Devuelvo os usuarios o usuario que se encuentren en la BD */

        return false; 
    }

}
