import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PatientService } from '../patient.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {
  patientForm: FormGroup;
  isEditMode: boolean = false;
  patientId: number | null = null;
  submittedPatient: any = null;

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.patientForm = this.fb.group({
      name: ['', Validators.required],
      disease: ['', Validators.required],
      doctor: ['', Validators.required],
      appointments: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.patientId = +params['id'];
      this.isEditMode = !!this.patientId;
      if (this.isEditMode) {
        this.patientService.getPatientById(this.patientId).subscribe(patient => {
          this.patientForm.patchValue(patient);
        });
      }
    });
  }

  onSubmit() {
    if (this.isEditMode) {
      this.patientService.updatePatient(this.patientId!, this.patientForm.value).subscribe(() => {
        this.router.navigate(['/patients']);
      });
    } else {
      this.patientService.addPatient(this.patientForm.value).subscribe((newPatient) => {
        this.submittedPatient = newPatient;
        this.router.navigate(['/patients']);
      });
    }
  }
}
