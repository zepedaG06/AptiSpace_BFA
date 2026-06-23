package com.aptispace.openxava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.openxava.application.meta.MetaModule;
import com.aptispace.seguridad.UsuarioActual;
import com.openxava.naviox.Modules;
import com.openxava.naviox.impl.IModulesHelperProvider;
import com.openxava.naviox.impl.MetaModuleFactory;

public class AptiSpaceModulesHelperProvider implements IModulesHelperProvider {
    private static final List<String> PSICOLOGO_MODULES = Arrays.asList(
        "PanelPsicologo",
        "Usuario",
        "Evaluado",
        "GrupoEvaluacion",
        "PlantillaCorreccion",
        "AplicacionPrueba",
        "ResultadoPrueba",
        "ObservacionPsicologica"
    );

    private static final List<String> EVALUADO_MODULES = Arrays.asList(
        "PanelEvaluado",
        "MisDatos",
        "MisGrupos",
        "ResponderPrueba",
        "MisResultados"
    );

    private static final List<String> ALL_MODULES = new ArrayList<>();

    static {
        ALL_MODULES.addAll(PSICOLOGO_MODULES);
        ALL_MODULES.addAll(EVALUADO_MODULES);
    }

    @Override
    public void init(String application) {
        MetaModuleFactory.setApplication(application);
    }

    @Override
    public String getCurrent(HttpServletRequest request) {
        String module = moduleFrom(request);
        if (moduleNamesFor(request).contains(module)) return module;
        return getUserAccessModule(request);
    }

    @Override
    public String getUserAccessModule(ServletRequest request) {
        if (request instanceof HttpServletRequest && UsuarioActual.esPsicologo((HttpServletRequest) request)) {
            return "PanelPsicologo";
        }
        return "PanelEvaluado";
    }

    @Override
    public List<MetaModule> getAll(HttpServletRequest request) {
        return createModules(moduleNamesFor(request));
    }

    private List<MetaModule> createModules(List<String> moduleNames) {
        List<MetaModule> modules = new ArrayList<>();
        for (String moduleName : moduleNames) {
            modules.add(MetaModuleFactory.create(moduleName));
        }
        return modules;
    }

    private List<String> moduleNamesFor(HttpServletRequest request) {
        if (UsuarioActual.esPsicologo(request)) return PSICOLOGO_MODULES;
        if (UsuarioActual.esEvaluado(request)) return EVALUADO_MODULES;
        return Collections.emptyList();
    }

    private String moduleFrom(HttpServletRequest request) {
        if (request == null) return "";
        String uri = request.getRequestURI();
        String marker = "/m/";
        int index = uri.indexOf(marker);
        if (index < 0) return "";
        String module = uri.substring(index + marker.length());
        int slash = module.indexOf('/');
        if (slash >= 0) module = module.substring(0, slash);
        int question = module.indexOf('?');
        if (question >= 0) module = module.substring(0, question);
        return module;
    }

    @Override
    public List<MetaModule> getNotInMenu() {
        return Collections.emptyList();
    }

    @Override
    public Collection<MetaModule> getInMenu(HttpServletRequest request, Modules modules) {
        return getAll(request);
    }

    @Override
    public boolean isPublic(HttpServletRequest request, String module) {
        if (!ALL_MODULES.contains(module)) return false;
        if (UsuarioActual.esPsicologo(request)) return PSICOLOGO_MODULES.contains(module);
        if (UsuarioActual.esEvaluado(request)) return EVALUADO_MODULES.contains(module);
        return false;
    }

    @Override
    public boolean showsIndexLink() {
        return false;
    }

    @Override
    public boolean showsSearchModules(HttpServletRequest request) {
        return getAll(request).size() > 30;
    }
}
