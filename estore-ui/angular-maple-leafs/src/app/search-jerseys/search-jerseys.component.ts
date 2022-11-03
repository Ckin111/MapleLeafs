import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

@Component({
  selector: 'app-search-jerseys',
  templateUrl: './search-jerseys.component.html',
  styleUrls: ['./search-jerseys.component.css']
})
export class SearchJerseysComponent implements OnInit {

  jerseys$!: Observable<Jersey[]>;
  private searchTerms = new Subject<string>();


  /**
   * when instantiate browse-jersey
   * @param jerseyService the service to talk to jersey API
   */
  constructor(private jerseyService: JerseyService) {   }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.jerseys$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.jerseyService.searchJerseys(term)),
    );
  }
}
