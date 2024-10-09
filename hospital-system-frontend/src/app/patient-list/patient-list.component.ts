import { Component, OnInit  } from '@angular/core';
import { PatientService } from '../patient.service';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  patients: any[] = [];

  constructor(private patientService: PatientService, private router: Router) {}

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients() {
    this.patientService.getPatients().subscribe((data) => {
      this.patients = data;
    });
  }

  deletePatient(id: number) {
    this.patientService.deletePatient(id).subscribe(() => {
      this.loadPatients();
    });
  }

  editPatient(id: number) {
    this.router.navigate(['/patients/edit', id]);
  }
}
