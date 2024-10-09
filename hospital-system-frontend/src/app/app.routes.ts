import { Routes } from '@angular/router';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientFormComponent } from './patient-form/patient-form.component';

export const routes: Routes = [
  { path: 'patients', component: PatientListComponent },
  { path: 'patients/new', component: PatientFormComponent },
  { path: 'patients/edit/:id', component: PatientFormComponent }, 
  { path: '', redirectTo: '/patients', pathMatch: 'full' },
];
