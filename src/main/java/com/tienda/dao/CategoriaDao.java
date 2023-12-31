/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.dao;

import com.tienda.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 *
 * @author Me
 */
public interface CategoriaDao extends JpaRepositoryImplementation<Categoria, Long>{
    List<Categoria> findByDescripcionContainingIgnoreCase(String descripcion);
}
