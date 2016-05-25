package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validators.AgeValidator")
public class AgeValidator implements Validator{

    private static final int MAX_AGE = 100;


    public AgeValidator(){
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        int age = (Integer)value;
        if(age >= MAX_AGE){

            FacesMessage msg =
                    new FacesMessage("Age validation failed.",
                            "age must be < 100");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }
}

