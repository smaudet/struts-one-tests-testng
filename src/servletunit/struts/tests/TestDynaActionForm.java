//  StrutsTestCase - a JUnit extension for testing Struts actions
//  within the context of the ActionServlet.
//  Copyright (C) 2002 Deryl Seale
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the Apache Software License as
//  published by the Apache Software Foundation; either version 1.1
//  of the License, or (at your option) any later version.
//
//  This library is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  Apache Software Foundation Licens for more details.
//
//  You may view the full text here: http://www.apache.org/LICENSE.txt

package servletunit.struts.tests;

import servletunit.struts.MockStrutsTestCase;

public class TestDynaActionForm extends MockStrutsTestCase {

  public void setUp() throws Exception {
    super.setUp();
    setServletConfigFile("/WEB-INF/web.xml");
  }

  public void testForm() {
    addRequestParameter("username", "deryl");
    addRequestParameter("password", "radar");
    setRequestPathInfo("test", "/testDynamicAction");
    actionPerform();
    verifyNoActionErrors();
  }

}


