package controllers;

public class ControllerRolesYAuth {
    private static ControllerRolesYAuth CONTROLLER = null;
    private ControllerRolesYAuth(){};

    public static synchronized ControllerRolesYAuth getInstance() throws Exception {
        if (CONTROLLER == null) {
            CONTROLLER = new ControllerRolesYAuth();
        }

        return CONTROLLER;
    }
}
