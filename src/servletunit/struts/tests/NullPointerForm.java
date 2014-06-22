package servletunit.struts.tests;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * An Action Form that always throws a NullPointerAction during validation.
 * Used in unit testing
 *
 * @author Sean Pritchard
 * @version 1.0
 */
public class NullPointerForm extends ActionForm {

  public NullPointerForm() {
  }

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    throw new NullPointerException("NullPointer during validation");
  }
}