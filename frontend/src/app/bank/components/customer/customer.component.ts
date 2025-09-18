import { Component, OnInit } from '@angular/core';
// import { Customer } from '../../types/Customer'; 
import { CustomerTS } from '../../types/tstypes/Customerts';
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ValidationErrors, Validators } from '@angular/forms';
import { of } from 'rxjs';

@Component({
  selector: 'app-customers',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss'],
})
export class CustomersComponent implements OnInit {
  isFormSubmitted: boolean = false;
  customerSuccess: string = '';
  customerError: string = '';
  customerForm!: FormGroup;
  customers: CustomerTS[] = [];

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      name: ['', [Validators.required, this.noSpecialCharacters]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  private noSpecialCharacters(control: AbstractControl): ValidationErrors | null {
    const regex = /^[a-zA-Z0-9 ]*$/;
    if (control.value && !regex.test(control.value)) {
      return { specialCharacters: true };
    }
    return null;
  }

  onSubmit(): void {
    this.isFormSubmitted = true;
  
    if (this.customerForm.invalid) {
      this.customerError = 'Please correct the errors in the form.';
      this.customerSuccess = '';
      return;
    }
  
    // Simulate success (replace with actual logic later)
    this.customerSuccess = 'Customer created successfully!';
    this.customerError = '';
  }
  

    // this.customerService.createCustomer(this.customerForm.value).subscribe({
    //   next: (response: any) => {
    //     this.customerSuccess = 'Customer created successfully!';
    //     this.customerError = '';
    //   },
    //   error: (error: { error: { message: string; }; }) => {
    //     this.customerError = error.error.message || 'An error occurred while creating the customer.';
    //     this.customerSuccess = '';
    //   }
    // });
  }

