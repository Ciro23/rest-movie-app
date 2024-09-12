import {NgForm} from "@angular/forms";

export class FormUtil {

  static getFormValidationErrors(form: NgForm) {
    let messages: string[] = [];

    for (const controlName in form.controls) {
      const control = form.controls[controlName];

      if (control.errors) {
        if (control.errors['required']) {
          messages.push(`"${this.capitalizeFirst(controlName)}" is required.`);
        }

        if (control.errors['email']) {
          messages.push(`"${this.capitalizeFirst(controlName)}" must be a valid email address.`);
        }

        if (control.errors && control.errors['minlength']) {
          const minlengthError = control.errors['minlength'] as { requiredLength: number };
          messages.push(`"${this.capitalizeFirst(controlName)}" must be at least
           ${minlengthError.requiredLength} characters long.`);
        }
      }
    }

    // Todo: password match validation should be done using Angular validation.
    if ((form.value['password'] || '') !== (form.value['repeat password'] || '')) {
      messages.push("Passwords do not match");
    }

    return messages;
  }

  private static capitalizeFirst(string: string) {
    return string[0].toUpperCase() + string.slice(1);
  }
}
