import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewJerseyComponent } from './view-jersey/view-jersey.component';

const routes: Routes = [
  {path: 'view/:id', component: ViewJerseyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
