import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ClientHomeComponent } from './pages/client/home/home.component';
// import { AuthGuard } from './pages/auth/auth.guard';
import { AuthenticationComponent } from './pages/auth/authentication.component';

const routes: Routes = [
  {
    path: '',
    component: ClientHomeComponent,
    children: [{ path: '', component: ClientHomeComponent }],
    // canActivate: [AuthGuard],
    data: {
      role: 'user',
    },
  },
  {
    path: '',
    component: AuthenticationComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'login' },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
