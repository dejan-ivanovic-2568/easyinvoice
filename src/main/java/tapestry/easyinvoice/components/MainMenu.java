/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MainMenu {

    @Inject
    private ComponentResources componentResource;

    @Property
    private String pageName;
    @Property
    private String subPageName;

    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("Dashboard", "Invoices", "Clients"));
        return pageNames;
    }

    public String getClassForPage() {
        return componentResource.getPageName().equalsIgnoreCase(pageName) ? "active" : null;
    }

    public String getClassForSubPage() {
        return componentResource.getPageName().equalsIgnoreCase(subPageName) ? "active" : null;
    }

    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Dashboard", "Invoices", "Clients"};
        return pageLabels[pageNames.indexOf(pageName)];
    }

    public List<String> getSubPageNames() {
        List<String> subPageNames = new ArrayList<>(Arrays.asList("add/invoice", "add/client"));
        return subPageNames;
    }

    public String getSubPageLabel() {
        List<String> subPageNames = getSubPageNames();
        String[] subPageLabels = {"Add invoice", "New client"};
        return subPageLabels[subPageNames.indexOf(subPageName)];
    }
}
