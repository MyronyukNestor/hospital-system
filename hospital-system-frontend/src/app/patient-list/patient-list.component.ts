import { Component, OnInit  } from '@angular/core';
import { PatientService } from '../patient.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  patients: any[] = [];

  constructor(private patientService: PatientService) {
  }

  ngOnInit(): void {
    this.patientService.getPatients().subscribe((data) => {
      this.patients = data;
      console.log('Пацієнти завантажені: ', this.patients);
    });
  }
}
