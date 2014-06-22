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
//  You may view the full text here: http://www.apache.org/LICENSE.txt

package servletunit.struts.tests;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import servletunit.struts.MockStrutsTestCase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TestMessageAction extends MockStrutsTestCase {

  public void setUp() throws Exception {
    super.setUp();
    setServletConfigFile("/WEB-INF/web.xml");
  }

  public void testNoMessages() throws Exception {
    addRequestParameter("username", "deryl");
    addRequestParameter("password", "radar");
    setRequestPathInfo("/login");
    actionPerform();
    verifyForward("success");
    verifyForwardPath("/main/success.jsp");
    assertEquals("deryl", getSession().getAttribute("authentication"));
    verifyNoActionMessages();
  }

  public void testMessageExists() throws Exception {
    setRequestPathInfo("test", "/testActionMessages");
    actionPerform();
    verifyForward("success");
    verifyActionMessages(new String[]{"test.message"});
  }

  public void testMessageExistsExpectedNone() throws Exception {
    setRequestPathInfo("test", "/testActionMessages");
    actionPerform();
    verifyForward("success");
    try {
      verifyNoActionMessages();
    } catch (Exception afe) {
      return;
    }
    fail("Expected an Exception!");
  }

  public void testMessageMismatch() throws Exception {
    setRequestPathInfo("test", "/testActionMessages");
    actionPerform();
    verifyForward("success");
    try {
      verifyActionMessages(new String[]{"error.password.mismatch"});
    } catch (Exception afe) {
      return;
    }
    fail("Expected an Exception!");
  }

  public void testExpectedMessagesNoneExist() throws Exception {
    addRequestParameter("username", "deryl");
    addRequestParameter("password", "radar");
    setRequestPathInfo("/login");
    actionPerform();
    verifyForward("success");
    verifyForwardPath("/main/success.jsp");
    assertEquals("deryl", getSession().getAttribute("authentication"));
    try {
      verifyActionMessages(new String[]{"test.message"});
    } catch (Exception afe) {
      return;
    }
    fail("Expected Exception!");
  }

  public void testVerifiesComplexErrorMessageScenario() {
    ActionErrors errors = new ActionErrors();
    errors.add("error1", new ActionMessage("error1"));
    errors.add("error2", new ActionMessage("error2"));
    errors.add("error1", new ActionMessage("error1"));
    getRequest().setAttribute(Globals.ERROR_KEY, errors);
    try {
      verifyActionErrors(new String[]{"error1", "error2", "error2"});
    } catch (Exception ex) {
      return;
    }
    fail("should not have passed!");
  }

}