import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { PatientService } from '../patient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-form',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './patient-form.component.html',
  styleUrl: './patient-form.component.css'
})
export class PatientFormComponent implements OnInit {
  patientForm: FormGroup;
  isEditMode: boolean = false;
  patientId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private router: Router
  ) {
    this.patientForm = this.fb.group({
      name: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(0)]],
      diagnosis: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if (this.patientId) {
      this.patientService.getPatientById(this.patientId).subscribe(patient => {
        this.patientForm.patchValue(patient);
      });
    }
  }

  onSubmit() {
    if (this.isEditMode) {
      this.patientService.updatePatient(this.patientId!, this.patientForm.value).subscribe(() => {
        this.router.navigate(['/patients']);
      });
    } else {
      this.patientService.addPatient(this.patientForm.value).subscribe(() => {
        this.router.navigate(['/patients']);
      });
    }
  }
}
