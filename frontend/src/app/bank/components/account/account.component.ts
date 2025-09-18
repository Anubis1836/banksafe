import { Component, OnInit } from '@angular/core';
import { AccountTS } from '../../types/tstypes/Accountts';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-accounts',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
    accountForm!: FormGroup;
    account?: AccountTS | undefined;
    accountError: string = '';
    accountSuccess: string = '';
  
    constructor(private fb: FormBuilder) {
      
    }
  
    ngOnInit(): void {
      this.accountForm = this.fb.group({
        accountId: ['', [Validators.required,Validators.min(1)]],
        customerId: ['', Validators.required],
        balance: ['', [Validators.required, Validators.min(0)]]
      });
    }
    get f(){
      return this.accountForm.controls
    }
    onSubmit(): void {
        if (this.accountForm.invalid) {
          this.accountError = 'Please correct the errors in the form.';
          this.accountSuccess = '';
          this.accountForm.markAllAsTouched()
          return;
        }
      
        // Simulate success (replace with actual logic later)
        this.accountSuccess = 'Account created successfully!';
        this.accountError = '';
        console.log('Form Submitted:',this.accountForm.value);
        
      }
      
  
    //   this.accountService.createAccount(this.accountForm.value).subscribe({
    //     next: (response) => {
    //       this.accountSuccess = 'Account created successfully!';
    //       this.accountError = '';
    //     },
    //     error: (error) => {
    //       this.accountError = error.error.message || 'An error occurred while creating the account.';
    //       this.accountSuccess = '';
    //     }
    //   });
    }

  