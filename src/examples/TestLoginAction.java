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

package examples;

import servletunit.struts.MockStrutsTestCase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TestLoginAction extends MockStrutsTestCase {

  public void setUp() throws Exception {
    super.setUp();
    setInitParameter("validating", "false");
  }

  public void testSuccessfulLogin() throws Exception {
    addRequestParameter("username", "deryl");
    addRequestParameter("password", "radar");
    setRequestPathInfo("/login");
    actionPerform();
    verifyForward("success");
    verifyForwardPath("/main/success.jsp");
    assertEquals("deryl", getSession().getAttribute("authentication"));
    verifyNoActionErrors();
  }

  public void testFailedLogin() throws Exception {
    addRequestParameter("username", "deryl");
    addRequestParameter("password", "express");
    setRequestPathInfo("/login");
    actionPerform();
    verifyForward("login");
    verifyForwardPath("/login/login.jsp");
    verifyInputForward();
    verifyActionErrors(new String[]{"error.password.mismatch"});
    assertNull(getSession().getAttribute("authentication"));
  }


}

