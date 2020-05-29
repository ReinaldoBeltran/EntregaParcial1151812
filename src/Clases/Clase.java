/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Dao.AlumnoJpaController;
import Dao.Conexion;
import Dto.Alumno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clase {
    
    private Conexion con;

    public Clase() {
        con = Conexion.getConexion();
    }   
    
    public boolean registrarAlumno(Alumno alumno) {
        //crear una conexion por metodo
        AlumnoJpaController cjc = new AlumnoJpaController(con.getBd());
        List<Alumno> listA=this.getAlumnos();
        for(Alumno al:listA){
            if(al.getEmail().equals(alumno.getEmail())){
                return false;
            }
        }
        try {
            cjc.create(alumno);
        } catch (Exception e) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE,null,e);
        }
        return true;

    }
    public List<Alumno> getAlumnos() {
        AlumnoJpaController cjc = new AlumnoJpaController(con.getBd());
        return cjc.findAlumnoEntities();

    }
    
    public boolean loginAlumno(Alumno alumno){
        
        List<Alumno> listC=this.getAlumnos();
        for(Alumno alumnos:listC){
            if(alumnos.getEmail().equals(alumno.getEmail())&&alumnos.getClass().equals(alumno.getClave())){
                return true;
            }
        }
        return false;
    }
    
}
