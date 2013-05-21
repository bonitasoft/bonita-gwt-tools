/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.tools.gwt.jetty;

import java.io.File;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.xml.XmlConfiguration;

import com.google.gwt.core.ext.TreeLogger;

/**
 * Custom jetty launcher for Bonita GWT debug mode
 * 
 * - Enable JNDI ENC
 * - Load jetty.xml
 * 
 * @author Colin PUY
 */
public class BonitaJettyLauncher extends JettyLauncher {

    /** get the JNDI ENC (ie: JNDI URLs starting with java:comp/) configured in Jetty */
    private final static String[] WEBAPPCONTEXT_CONFIGURATION_CLASSES = {
            "org.mortbay.jetty.webapp.WebInfConfiguration",
            "org.mortbay.jetty.webapp.JettyWebXmlConfiguration",
            "org.mortbay.jetty.plus.webapp.EnvConfiguration",
            "org.mortbay.jetty.plus.webapp.Configuration",
    };

    @Override
    protected WebAppContext createWebAppContext(TreeLogger logger, File appRootDir) {
        WebAppContext webAppContext = super.createWebAppContext(logger, appRootDir);
        webAppContext.setConfigurationClasses(WEBAPPCONTEXT_CONFIGURATION_CLASSES);
        return webAppContext;
    }

    /**
     * Load jetty.xml to configure server
     */
    @Override
    protected void configureServer(Server server, File appRootDir) throws Exception {
        File jettyXml = new File(appRootDir, "WEB-INF/jetty.xml");
        if (jettyXml.exists()) {
            System.out.println("loading " + jettyXml.getAbsolutePath());
            XmlConfiguration configuration = new XmlConfiguration(jettyXml.toURI().toURL());
            configuration.configure(server);
        }
    }
}
