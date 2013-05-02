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
package org.bonitasoft.tools.gwt;

import org.apache.tools.ant.taskdefs.Javac;
import org.eclipse.jdt.core.JDTCompilerAdapter;

public class JDTCompiler extends JDTCompilerAdapter {

    @Override
    public void setJavac(Javac attributes) {
        System.out.println(attributes);
        if (attributes.getTarget() == null) {
            // set compiler target level to 1.6
            attributes.setTarget("1.6");
        }
        if (attributes.getSource() == null) {
            // set compiler source level to 1.6
            attributes.setSource("1.6");
        }
        super.setJavac(attributes);
    }
}
