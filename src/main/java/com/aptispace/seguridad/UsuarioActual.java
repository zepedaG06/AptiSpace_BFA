package com.aptispace.seguridad;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Users;
import com.aptispace.modelo.Rol;
import com.aptispace.modelo.Usuario;

public final class UsuarioActual {
    public static final String SESSION_USUARIO = "aptispace.usuario";
    public static final String SESSION_ROL = "aptispace.rol";

    private UsuarioActual() { }

    public static Usuario buscar(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) return null;
        EntityManager em = XPersistence.getManager();
        try {
            return em.createQuery(
                "select distinct u from Usuario u left join fetch u.roles where u.nombreUsuario = :usuario",
                Usuario.class)
                .setParameter("usuario", nombreUsuario)
                .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public static Usuario desdeSesion(HttpServletRequest request) {
        if (request != null) {
            Object usuario = request.getSession(true).getAttribute(SESSION_USUARIO);
            if (usuario instanceof Usuario) return (Usuario) usuario;
        }
        String nombreUsuario = Users.getCurrent();
        return buscar(nombreUsuario);
    }

    public static boolean tieneRol(Usuario usuario, String rol) {
        if (usuario == null || rol == null || usuario.getRoles() == null) return false;
        for (Rol actual : usuario.getRoles()) {
            if (actual != null && rol.equalsIgnoreCase(actual.getNombreRol())) return true;
        }
        return false;
    }

    public static String rolPrincipal(Usuario usuario) {
        if (tieneRol(usuario, RolesAptiSpace.ADMINISTRADOR)) return RolesAptiSpace.PSICOLOGO;
        if (tieneRol(usuario, RolesAptiSpace.PSICOLOGO)) return RolesAptiSpace.PSICOLOGO;
        if (tieneRol(usuario, RolesAptiSpace.EVALUADO)) return RolesAptiSpace.EVALUADO;
        return null;
    }

    public static String rolPrincipal(HttpServletRequest request) {
        if (request != null) {
            Object rol = request.getSession(true).getAttribute(SESSION_ROL);
            if (rol != null) return rol.toString();
        }
        return rolPrincipal(desdeSesion(request));
    }

    public static boolean esPsicologo(HttpServletRequest request) {
        return RolesAptiSpace.PSICOLOGO.equals(rolPrincipal(request));
    }

    public static boolean esEvaluado(HttpServletRequest request) {
        return RolesAptiSpace.EVALUADO.equals(rolPrincipal(request));
    }

    public static void guardarEnSesion(HttpServletRequest request, Usuario usuario) {
        if (request == null || usuario == null) return;
        String rol = rolPrincipal(usuario);
        request.getSession(true).setAttribute(SESSION_USUARIO, usuario);
        request.getSession(true).setAttribute(SESSION_ROL, rol);
    }

    public static String entornoPara(Usuario usuario) {
        String rol = rolPrincipal(usuario);
        if (RolesAptiSpace.PSICOLOGO.equals(rol)) return "PanelPsicologo";
        if (RolesAptiSpace.EVALUADO.equals(rol)) return "PanelEvaluado";
        return "SignIn";
    }
}
