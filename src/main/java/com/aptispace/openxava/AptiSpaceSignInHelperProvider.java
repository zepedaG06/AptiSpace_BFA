package com.aptispace.openxava;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.openxava.util.Messages;
import org.openxava.util.Users;
import org.openxava.view.View;
import com.aptispace.modelo.Usuario;
import com.aptispace.seguridad.UsuarioActual;
import com.openxava.naviox.Modules;
import com.openxava.naviox.impl.ISignInHelperProvider;

public class AptiSpaceSignInHelperProvider implements ISignInHelperProvider {
    @Override
    public String[] init(HttpServletRequest request, View view) {
        return null;
    }

    @Override
    public void initRequest(HttpServletRequest request, View view) { }

    @Override
    public String refineForwardURI(HttpServletRequest request, String forwardURI) {
        Usuario usuario = UsuarioActual.desdeSesion(request);
        return "/m/" + UsuarioActual.entornoPara(usuario);
    }

    @Override
    public void signIn(HttpServletRequest request, String nombreUsuario) {
        Usuario usuario = UsuarioActual.buscar(nombreUsuario);
        HttpSession session = request.getSession(true);
        session.setAttribute("naviox.user", nombreUsuario);
        UsuarioActual.guardarEnSesion(request, usuario);
        Users.setCurrent(nombreUsuario);

        Object modules = session.getAttribute("modules");
        if (modules instanceof Modules) ((Modules) modules).reset();
    }

    @Override
    public boolean isAuthorized(ServletRequest request, String nombreUsuario, String password, Messages errors, String errorKey) {
        Usuario usuario = UsuarioActual.buscar(nombreUsuario);
        boolean autorizado = usuario != null
            && Usuario.EstadoUsuario.ACTIVO.equals(usuario.getEstado())
            && usuario.getContrasena() != null
            && usuario.getContrasena().equals(password)
            && UsuarioActual.rolPrincipal(usuario) != null;

        if (!autorizado) {
            errors.add(errorKey);
            return false;
        }

        if (request instanceof HttpServletRequest) {
            UsuarioActual.guardarEnSesion((HttpServletRequest) request, usuario);
        }
        return true;
    }

    @Override
    public String getSignInURL() {
        return null;
    }
}
