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

import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;
import org.mortbay.component.AbstractLifeCycle;

/**
 * H2 jetty lifecycle to start/stop h2 database
 * 
 * @author arezi
 * https://github.com/arezi/jetty-h2/tree/master/com.arezi.jetty.h2
 */
public class H2LifeCycle extends AbstractLifeCycle {

    private Server h2;

    private String baseDir;

    private boolean ifExists;

    private boolean tcpAllowOthers;

    private Integer tcpPort;

    private boolean web;

    private Integer webPort;

    public H2LifeCycle() {
    }

    @Override
    protected void doStart() throws Exception {
        if (h2 != null) {
            return;
        }

        List<String> lstArgs = new ArrayList<String>();
        lstArgs.add("-tcp");

        if (tcpPort != null) {
            lstArgs.add("-tcpPort");
            lstArgs.add(tcpPort.toString());
        }

        if (tcpAllowOthers) {
            lstArgs.add("-tcpAllowOthers");
        }

        if (baseDir != null) {
            lstArgs.add("-baseDir");
            lstArgs.add(baseDir);
        }

        if (ifExists) {
            lstArgs.add("-ifExists");
        }

        if (web) {
            lstArgs.add("-web");
            if (webPort != null) {
                lstArgs.add("-webPort");
                lstArgs.add(webPort.toString());
            }
        }

        System.out.println("Starting H2 database.");

        h2 = new Server();

        h2.runTool(lstArgs.toArray(new String[lstArgs.size()]));

        System.out.println("H2 database started.");

    }

    @Override
    protected void doStop() throws Exception {
        System.out.println("Stopping H2 database.");
        h2.shutdown();
        System.out.println("H2 database stopped.");
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public boolean isIfExists() {
        return ifExists;
    }

    public void setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    public boolean isTcpAllowOthers() {
        return tcpAllowOthers;
    }

    public void setTcpAllowOthers(boolean tcpAllowOthers) {
        this.tcpAllowOthers = tcpAllowOthers;
    }

    public Integer getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(Integer tcpPort) {
        this.tcpPort = tcpPort;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public Integer getWebPort() {
        return webPort;
    }

    public void setWebPort(Integer webPort) {
        this.webPort = webPort;
    }

}
