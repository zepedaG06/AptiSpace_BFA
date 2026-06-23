<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.openxava.naviox.Modules" %>
<%@ page import="com.openxava.naviox.util.NaviOXPreferences" %>
<%
Modules modules = (Modules) session.getAttribute("modules");
if (modules == null) {
    modules = new Modules();
    session.setAttribute("modules", modules);
}
boolean isFirstSteps = Modules.FIRST_STEPS.equals(modules.getCurrentModuleName());
String display = isFirstSteps ? "class='ox-display-block-important'" : "";
%>

<div id="modules_list" <%= display %>>
    <div id="modules_list_top">
        <div id="application_title">
            <div id="application_name">
                <%= modules.getApplicationLabel(request) %>
            </div>
            <div id="organization_name">
                <%= modules.getOrganizationName(request) %>
            </div>
        </div>
    </div>

    <div id="modules_list_outbox">
        <table id="modules_list_box">
            <tr id="modules_list_content">
                <td>
                    <jsp:include page="<%= NaviOXPreferences.getInstance().getModulesMenuJSP() %>" />
                </td>
            </tr>
        </table>
    </div>
</div>

<% if (!isFirstSteps) { %>
    <a id="modules_list_hide">
        <i class="mdi mdi-chevron-left"></i>
    </a>
    <a id="modules_list_show">
        <i class="mdi mdi-chevron-right"></i>
    </a>
<% } %>
