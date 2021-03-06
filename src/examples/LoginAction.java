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

import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends Action {

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {

    String username = ((LoginForm) form).getUsername();
    String password = ((LoginForm) form).getPassword();

    ActionErrors errors = new ActionErrors();

    if ((!username.equals("deryl")) || (!password.equals("radar")))
      errors.add("password", new ActionMessage("error.password.mismatch"));

    if (!errors.isEmpty()) {
      saveErrors(request, errors);
      return new ActionForward(mapping.getInput());
    }

    HttpSession session = request.getSession();
    session.setAttribute("authentication", username);

    // Forward control to the specified success URI

    return mapping.findForward("success");

  }

}







