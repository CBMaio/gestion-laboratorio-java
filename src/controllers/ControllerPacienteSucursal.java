package controllers;

import Models.Paciente;
import Models.Peticiones;
import Models.Sucursal;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.swing.*;
import java.util.ArrayList;

public class ControllerPacienteSucursal {
    private static ControllerPacienteSucursal CONTROLLER = null;
    private static ArrayList<Paciente> pacientesArrayList;

    private static ArrayList<Sucursal> sucursalesArrayList;
    private ControllerPacienteSucursal(){};

    public static synchronized ControllerPacienteSucursal getInstance() throws Exception  {
        if (CONTROLLER == null) {
            CONTROLLER = new ControllerPacienteSucursal();
            sucursalesArrayList = getSucursalesList();
            pacientesArrayList = getPacientesList();
        }

        return CONTROLLER;
    }

    public static ArrayList<Sucursal> getSucursalesList() {
        ArrayList sucursales = new ArrayList();
        return sucursales;
    }

    public static ArrayList<Paciente> getPacientesList() {
        ArrayList pacientes = new ArrayList();
        Paciente paciente1 = new Paciente(1, "F", 25, "caro", "maio", "domicilio de caro", "caro@hotamil.com");
        pacientes.add(paciente1);
        return pacientes;
    }

    public ArrayList<Paciente> mostrarPaciente () {
        if (pacientesArrayList.isEmpty()) {
            return new ArrayList<>();
        }

        return pacientesArrayList;
    }

    public ArrayList<Sucursal> mostrarSucursal () {
        if (sucursalesArrayList.isEmpty()) {
            return new ArrayList<>();
        }

        return sucursalesArrayList;
    }


    public static PacienteSucursalDto modelsToDto(Paciente model, Sucursal modelSucursal){
        return new PacienteSucursalDto(model.getId(), modelSucursal.getNumeroSucursal(), model.getNombreCompleto(), modelSucursal.getNombre());
    }

    public PacienteSucursalDto getDtoByIdPacienteIdSucursal (Integer idPaciente, Integer idSucursal) throws Exception {
        Paciente paciente = null;
        Sucursal sucursal = null;
        for (Paciente model: pacientesArrayList) {
            if (model.getId().equals(idPaciente)){
                paciente = model;
            }
        }

        for (Sucursal model: sucursalesArrayList) {
            if (model.getNumeroSucursal().equals(idSucursal)){
                sucursal = model;
            }
        }

        if ((paciente == null) || (sucursal == null)) {
            return  null;
        }

        return modelsToDto(paciente, sucursal);
    }

    public static Paciente dtoToPaciente(PacienteSucursalDto dto){
        Paciente paciente = new Paciente();
        paciente.setDni(dto.getIdPaciente());
        paciente.setNombre(dto.getNombrePaciente());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setMail(dto.getMail());
        paciente.setEdad(dto.getEdad());
        paciente.setApellido(dto.getApellido());
        return paciente;
    }

    public static Sucursal dtoToSucursal (PacienteSucursalDto dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNumeroSucursal(dto.getNumeroSucursal());
        sucursal.setNombreSucursal(dto.getNombreSucursal());
        sucursal.setDireccion(dto.getDireccionSucursal());
        return sucursal;
    }

    public boolean addPacienteExitosamente(PacienteSucursalDto dto) throws Exception {
        if(pacienteExistente(dto.getIdPaciente()).equals(false)){
            pacientesArrayList.add(dtoToPaciente(dto));
            return true;
        }

        return false;
    }

    public boolean addSucursalExitosamente (PacienteSucursalDto dto) throws Exception {
        if(sucursalExistente(dto.getNumeroSucursal()).equals(false)) {
            sucursalesArrayList.add(dtoToSucursal(dto));
            return true;
        }

        return false;
    }

    public Boolean sucursalExistente (Integer numeroSucursal) {
        if (sucursalesArrayList.size() >= 1) {
            for (Sucursal model : sucursalesArrayList) {
                if (model.getNumeroSucursal() != null) {
                    if (model.getNumeroSucursal().equals(numeroSucursal)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public Boolean pacienteExistente (Integer id) {
        if (pacientesArrayList.size() >= 1) {
            for (Paciente model: pacientesArrayList) {
                if(model.getId() != null) {
                    if (model.getId().equals(id)){
                        return true;
                    }
                }
            }
        }

        return  false;
    }

    public void deleteByPacienteId(Integer id){
        int index = getPacienteIndex(id);
        if(index != -1){
            pacientesArrayList.remove(index);
        }
    }

    public void deleteBySucursalId(Integer id){
        int index = getSucursalIndex(id);
        if(index != -1){
            sucursalesArrayList.remove(index);
        }
    }

    private int getPacienteIndex(Integer id){
        for (int i=0;i<pacientesArrayList.size();i++){
            if(pacientesArrayList.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    private int getSucursalIndex(Integer id){
        for (int i=0;i<sucursalesArrayList.size();i++){
            if(sucursalesArrayList.get(i).getNumeroSucursal().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public PacienteSucursalDto getPacienteDTO (Integer dni) {
        for (int i=0;i<pacientesArrayList.size();i++){
            if(pacientesArrayList.get(i).getId().equals(dni)){
                Paciente selected = pacientesArrayList.get(i);
                return new PacienteSucursalDto(selected.getId(), selected.getNombre(), selected.getApellido(), selected.getEdad(), selected.getDomicilio(), selected.getMail());
            }
        }

        return null;
    }

    public PacienteSucursalDto getSucursalDTO (Integer id) {
        for (int i=0;i<sucursalesArrayList.size();i++){
            if(sucursalesArrayList.get(i).getNumeroSucursal().equals(id)){
                Sucursal selected = sucursalesArrayList.get(i);
                return new PacienteSucursalDto(selected.getNumeroSucursal(), selected.getDireccion(), selected.getNombre());
            }
        }

        return null;
    }

    public void modificarPaciente (PacienteSucursalDto dto) {
        Integer indexPaciente = getPacienteIndex(dto.getIdPaciente());
        pacientesArrayList.get(indexPaciente).setDni(dto.getIdPaciente());
        pacientesArrayList.get(indexPaciente).setNombre(dto.getNombrePaciente());
        pacientesArrayList.get(indexPaciente).setApellido(dto.getApellido());
        pacientesArrayList.get(indexPaciente).setDomicilio(dto.getDomicilio());
        pacientesArrayList.get(indexPaciente).setMail(dto.getMail());
    }

    public void modificarSucursal (PacienteSucursalDto dto) {
        Integer indexSucursal = getSucursalIndex(dto.getNumeroSucursal());
        sucursalesArrayList.get(indexSucursal).setDireccion(dto.getDireccionSucursal());
        sucursalesArrayList.get(indexSucursal).setNombreSucursal(dto.getNombreSucursal());
    }

    public boolean asociarPeticionAPaciente (Peticiones peticion, PacienteSucursalDto pacienteDto){
        Integer pacienteIndex = getPacienteIndex(pacienteDto.getIdPaciente());
        Paciente paciente = pacientesArrayList.get(pacienteIndex);
        paciente.agregarPeticion(peticion);
        return true;
    }

    public ArrayList<Peticiones> getPeticionesPorPaciente (PacienteSucursalDto pacienteDto) {
        Integer pacienteIndex = getPacienteIndex(pacienteDto.getIdPaciente());
        Paciente paciente = pacientesArrayList.get(pacienteIndex);
        return paciente.getPeticiones();
    }

    public Boolean tienePeticionesFinalizadas (PacienteSucursalDto pacienteDTO) {
        ArrayList<Peticiones> peticionesDePaciente = getPeticionesPorPaciente(pacienteDTO);
        for (Peticiones peticion: peticionesDePaciente) {
            if (peticion.resultadosCompletos()) {
                return true;
            }
        }

        return false;
    }
}
