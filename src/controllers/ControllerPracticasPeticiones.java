package controllers;

import Models.*;
import dto.PacienteSucursalDto;
import dto.PracticaPeticionDto;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.time.Period;
import java.util.ArrayList;

public class ControllerPracticasPeticiones {
    private static ArrayList<Practica> listPracticas;
    private static ArrayList<Peticiones> listPeticiones;
    private static ControllerPracticasPeticiones CONTROLLER = null;
    private ControllerPracticasPeticiones(){};

    public static synchronized ControllerPracticasPeticiones getInstance() throws Exception {
        if (CONTROLLER == null) {
            CONTROLLER = new ControllerPracticasPeticiones();
            listPracticas = getPracticasList();
            listPeticiones = getPeticionesList();
        }

        return CONTROLLER;
    }

    public static ArrayList<Peticiones> getPeticionesList () {
        ArrayList peticiones = new ArrayList<Peticiones>();
        return peticiones;
    }

    public static ArrayList<Practica> getPracticasList() {
        ArrayList practicas = new ArrayList();
        Practica practica1 = new Practica(1, "colesterol", 1, 1.00f, "critico", 0.00f,200.00f, true);
        Practica practica2 = new Practica(2, "hdl", 1, 1.00f, "critico", 0.00f,200.00f, true);
        Practica practica3 = new Practica(3, "ldl", 1, 1.00f, "critico", 0.00f,200.00f, true);

        practicas.add(practica1);
        practicas.add(practica2);
        practicas.add(practica3);

        return practicas;
    }

    public static Practica dtoToPractica(PracticaPeticionDto dto){
        Practica practica = new Practica(dto.getCodigoPractica(), dto.getNombrePractica(), dto.getGrupoPractica(), dto.getHorasDeDemora(), dto.getTipoDePracica(), dto.getValorReferenciaMinimo(), dto.getValorReferenciaMaximo(), dto.getTieneResultadosNumericos());
        return practica;
    }

    public static Peticiones dtoToPeticion (PracticaPeticionDto dto) throws Exception {
        ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
        Peticiones peticion = new Peticiones();
        peticion.setNumeroPeticion(dto.getNumeroPeticion());
        peticion.setPaciente(controller.dtoToPaciente(dto.getPaciente()));
        peticion.setFechaCarga(dto.getFechaCarga());
        peticion.setFechaEntrega(dto.getFechaEntrega());
        peticion.setSucursal(controller.dtoToSucursal(dto.getSucursal()));
        return peticion;
    }

    public boolean addPeticionExitosamente (PracticaPeticionDto dto) throws Exception {
        if(peticionExistente(dto.getNumeroPeticion()).equals(false)) {
            Peticiones newPeticion = dtoToPeticion(dto);
            listPeticiones.add(newPeticion);
            return true;
        }

        return false;
    }

    public boolean addPracticaExitosamente (PracticaPeticionDto dto) throws Exception {
        if(practicaExistente(dto.getCodigoPractica()).equals(false)) {
            Practica newPractica = dtoToPractica(dto);
            listPracticas.add(newPractica);
            return true;
        }

        return false;
    }

    public Boolean practicaExistente (Integer codigoPractica) {
        if (listPracticas.size() >= 1) {
            for (Practica model : listPracticas) {
                if (model.getCodigo() != null) {
                    if (model.getCodigo().equals(codigoPractica)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Boolean peticionExistente (Integer numeroPeticion) {
        if (listPeticiones.size() >= 1) {
            for (Peticiones model : listPeticiones) {
                if (model.getNumeroPeticion() != null) {
                    if (model.getNumeroPeticion().equals(numeroPeticion)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ArrayList<Practica> mostrarPracitcas () {
        if (listPracticas.isEmpty()) {
            return new ArrayList<>();
        }

        return listPracticas;
    }

    public ArrayList<Peticiones> mostrarPeticiones () throws Exception {
        return listPeticiones;
    }

    public void deleteByCodigoPractica(Integer cod){
        int index = getPracticaIndex(cod);
        if(index != -1){
            listPracticas.remove(index);
        }
    }

    public void deleteByCodigoPeticion(Integer cod){
        int index = getPeticionIndex(cod);
        if(index != -1){
            try {
                deletePeticionFromPacienteExitosamente(cod);
                deletePeticionFromSucursalExitosamente(cod);
                listPeticiones.remove(index);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean deletePeticionFromPacienteExitosamente (Integer codigoPeticion) throws Exception {
        Peticiones peticion = listPeticiones.get(getPeticionIndex(codigoPeticion));
        ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
        PacienteSucursalDto pacienteDto = controller.getPacienteDTO(peticion.getPaciente().getDni());
        return ControllerPacienteSucursal.getInstance().eliminarPeticionDePaciente(codigoPeticion, pacienteDto);
    }

    public boolean deletePeticionFromSucursalExitosamente (Integer codigoPeticion) throws Exception {
        Peticiones peticion = listPeticiones.get(getPeticionIndex(codigoPeticion));
        ControllerPacienteSucursal controller = ControllerPacienteSucursal.getInstance();
        PacienteSucursalDto sucursalDto = controller.getSucursalDTO(peticion.getSucursal().getNumeroSucursal());
        return ControllerPacienteSucursal.getInstance().eliminarPeticionDeSucursal(codigoPeticion, sucursalDto);
    }
    private int getPracticaIndex(Integer cod){
        for (int i=0;i<listPracticas.size();i++){
            if(listPracticas.get(i).getCodigo() != null && listPracticas.get(i).getCodigo().equals(cod)){
                return i;
            }
        }
        return -1;
    }

    private int getPeticionIndex(Integer cod){
        for (int i=0;i<listPeticiones.size();i++){
            if(listPeticiones.get(i).getNumeroPeticion() != null && listPeticiones.get(i).getNumeroPeticion().equals(cod)){
                return i;
            }
        }
        return -1;
    }

    public PracticaPeticionDto getPracticaDTO (Integer codigo) {
        for (int i=0;i<listPracticas.size();i++){
            if(listPracticas.get(i).getCodigo() != null && listPracticas.get(i).getCodigo().equals(codigo)){
                Practica selected = listPracticas.get(i);
                return new PracticaPeticionDto(selected.getCodigo(), selected.getNombre(), selected.getGrupo(), selected.getTiempoDeDemora(), selected.getTipoDePracica(), selected.getValorReferenciaMinimo(), selected.getValorReferenciaMaximo(), selected.getTieneResultadosNumericos());
            }
        }

        return null;
    }

    public void modificarPractica (PracticaPeticionDto dto) {
        Integer indexPractica = getPracticaIndex(dto.getCodigoPractica());
        listPracticas.get(indexPractica).setNombre(dto.getNombrePractica());
        listPracticas.get(indexPractica).setGrupo(dto.getGrupoPractica());
        listPracticas.get(indexPractica).setTiempoDeDemora(dto.getHorasDeDemora());
    }

    public void modificarPeticion (PracticaPeticionDto dto) {
        ControllerPacienteSucursal controllerPacienteSucursal = null;
        try {
            controllerPacienteSucursal = ControllerPacienteSucursal.getInstance();
            PacienteSucursalDto pacientDto = controllerPacienteSucursal.getPacienteDTO(dto.getPaciente().getIdPaciente());
            PacienteSucursalDto sucursalDto = controllerPacienteSucursal.getSucursalDTO(dto.getSucursal().getNumeroSucursal());
            Paciente paciente = controllerPacienteSucursal.getPacienteByDto(pacientDto);
            Sucursal sucursal = controllerPacienteSucursal.getSucursalByDto(sucursalDto);
            Integer indexPeticion = getPeticionIndex(dto.getNumeroPeticion());
            listPeticiones.get(indexPeticion).setFechaCarga(dto.getFechaCarga());
            listPeticiones.get(indexPeticion).setFechaEntrega(dto.getFechaEntrega());
            listPeticiones.get(indexPeticion).setPaciente(paciente);
            listPeticiones.get(indexPeticion).setSucursal(sucursal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addPracticaToPeticion (PracticaPeticionDto peticionDto, Integer codigoPractica) {
        Integer practicaIndex = getPracticaIndex(codigoPractica);
        Integer peticionIndex = getPeticionIndex(peticionDto.getNumeroPeticion());
        if (practicaIndex.equals(-1) || peticionIndex.equals(-1)) {
            JOptionPane.showMessageDialog(null, "Práctica o petición inexistente");
            return;
        }

        Practica practica = listPracticas.get(practicaIndex);
        Peticiones peticion = listPeticiones.get(peticionIndex);
        peticion.addPractica(practica);
    }

    public boolean addPeticionToPacienteExitosamente (PracticaPeticionDto peticionDto, PacienteSucursalDto pacienteDto) throws Exception {
        Peticiones peticion = listPeticiones.get(getPeticionIndex(peticionDto.getNumeroPeticion()));
        return ControllerPacienteSucursal.getInstance().asociarPeticionAPaciente(peticion, pacienteDto);
    }

    public boolean addPeticionToSucursalExitosamente (PracticaPeticionDto peticionDto, PacienteSucursalDto sucursalDto) throws Exception {
        Peticiones peticion = listPeticiones.get(getPeticionIndex(peticionDto.getNumeroPeticion()));
        return ControllerPacienteSucursal.getInstance().asociarPeticionASucursal(peticion, sucursalDto);
    }

    public PracticaPeticionDto getPeticionDto (Integer peticionId) {
        ControllerPacienteSucursal controllerSucursal = null;
        try {
            controllerSucursal = ControllerPacienteSucursal.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i=0;i<listPeticiones.size();i++){
            if(listPeticiones.get(i).getNumeroPeticion().equals(peticionId)){
                Peticiones selected = listPeticiones.get(i);
                PacienteSucursalDto paciente = null;
                try {
                    paciente = ControllerPacienteSucursal.getInstance().getPacienteDTO(selected.getPaciente().getDni());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                PacienteSucursalDto sucursalDto = controllerSucursal.getSucursalDTO(selected.getSucursal().getNumeroSucursal());
                PracticaPeticionDto peticion = new PracticaPeticionDto(selected.getNumeroPeticion(), paciente, selected.getFechaCarga(), selected.getFechaEntrega(), selected.getPracticas(), selected.getResultados(), sucursalDto );
                return peticion;
            }
        }

        return null;
    }

    public void setResultadoToPractica (PracticaPeticionDto practicaDto, PracticaPeticionDto peticionDto, Integer resultado) {
        Peticiones peticion = listPeticiones.get(getPeticionIndex(peticionDto.getNumeroPeticion()));
        peticion.setResultadoToPractica(practicaDto.getCodigoPractica(), resultado);
    }

    public void setResultadoToPractica (PracticaPeticionDto practicaDto, PracticaPeticionDto peticionDto, Boolean resultado) {
        Peticiones peticion = listPeticiones.get(getPeticionIndex(peticionDto.getNumeroPeticion()));
        peticion.setResultadoToPractica(practicaDto.getCodigoPractica(), resultado);
    }

    public ArrayList<Resultado> getResultados (Integer numeroPeticion) {
        for (Peticiones item: listPeticiones) {
            if (item.getNumeroPeticion().equals(numeroPeticion)) {
                return item.getResultados();
            }
        }

        return null;
    }

}
