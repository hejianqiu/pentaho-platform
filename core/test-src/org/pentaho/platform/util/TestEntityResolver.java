/*
 * This program is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software 
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this 
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html 
 * or from the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright 2005 - 2008 Pentaho Corporation.  All rights reserved.
 *
 * @created Jul 27, 2005 
 * @author James Dixon
 */

package org.pentaho.platform.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.pentaho.platform.api.engine.IDocumentResourceLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TestEntityResolver implements IDocumentResourceLoader {

  private static TestEntityResolver instance;

  public static TestEntityResolver getInstance() {
    if (TestEntityResolver.instance == null) {
    	TestEntityResolver.instance = new TestEntityResolver();
    }
    return TestEntityResolver.instance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String,
   *      java.lang.String)
   */
  public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {

    int idx = systemId.lastIndexOf('/');
    String dtdName = systemId.substring(idx + 1);

    try {
      InputStream xslIS = getClass().getClassLoader().getResourceAsStream("system/dtd/" + dtdName); //$NON-NLS-1$
      if (xslIS != null) {
        InputSource source = new InputSource(xslIS);
        return source;
      }
    } catch (Exception e) {
    }
    return null;
  }

  public Source resolve(final String href, final String base) {
    try {
      InputStream xslIS = new FileInputStream("test-res/solution/" + href);
      StreamSource xslSrc = new StreamSource(xslIS);
      return xslSrc;
    } catch (Exception e) {
    }
    return null;

  }

  public InputStream loadXsl(final String name) {

    try {
      InputStream xslIS = new FileInputStream(name);
      return xslIS;
    } catch (Exception e) {
    }
    return null;
  }

}
